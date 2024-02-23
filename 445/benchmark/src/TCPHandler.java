import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import enums.ConnectionType;
import enums.RunMode;
import message.Key;
import message.Message;
import message.MessageGenerator;
import object.Args;

public class TCPHandler {

  static Socket socket;
  static InputStream in;
  static OutputStream out;

  static MessageGenerator messages;
  static Key key;

  final static int LATENCY_ITERATIONS = 100;
  final static int THROUGHPUT_ITERATIONS = 30;
  final static int THROUGHPUT_FILESIZE = 1048576;

  static void setup(Args args) {

    messages = new MessageGenerator(82931029);
    key = new Key(819230912);

    try {
      if (args.type == ConnectionType.CLIENT) {
        makeClient(args);
        if (args.runMode == RunMode.LATENCY) {
          clientLatency(args);
        } else {
          clientThroughput(args);
        }
      } else {
        makeServer(args);
        if (args.runMode == RunMode.LATENCY) {
          serverLatency(args);
        } else {
          serverThroughput(args);
        }
      }
    } catch (IOException e) {
      System.out.println(e);
      System.exit(1);
    }
  }

  private static void makeClient(Args args) throws IOException {
    System.out.println(args.toString());
    socket = new Socket(args.ip, args.port);
    in = socket.getInputStream();
    out = socket.getOutputStream();
    System.out.println("Connection established, running tests.");
  }

  private static void clientLatency(Args args) throws IOException {
    Message globalMessage = messages.getSize(args.size);

    for (int i = 0; i < LATENCY_ITERATIONS; i++) {
      System.out.print("Iteration " + (i + 1) + ": ");
      Message msg = globalMessage.clone();
      msg.encrypt(key);

      // Start timer
      long start = System.nanoTime();

      // Send msg and wait for response
      byte[] input = msg.bytes();
      out.write(input);
      byte[] output = new byte[input.length];
      in.read(output, 0, input.length);

      // End timer
      long end = System.nanoTime();

      Message recieved = Message.fromEncrypted(output);
      recieved.decrypt(key);

      if (!globalMessage.equals(recieved)) {
        System.out.println("Message failed to validate, ending test.");
        socket.close();
        return;
      }

      System.out.println((end - start) + " ns");
    }
    socket.close();
  }

  private static void clientThroughput(Args args) throws IOException {
    Message globalMessage = messages.getSize(args.size);

    for (int i = 0; i < THROUGHPUT_ITERATIONS; i++) {
      System.out.print("Iteration " + (i + 1) + ": ");
      int MSG_ITERATIONS = THROUGHPUT_FILESIZE / args.size;

      long start = System.nanoTime();

      for (int pNum = 0; pNum < MSG_ITERATIONS; pNum++) {
        Message msg = globalMessage.clone();
        msg.encrypt(key);

        byte[] input = msg.bytes();
        out.write(input);

        byte[] ack = new byte[1];
        in.read(ack);
      }
      long end = System.nanoTime();
      long time = end - start;

      double throughput = THROUGHPUT_FILESIZE / ((double) time / 1000000000);
      System.out.println(throughput + " bps");
    }
  }

  private static void makeServer(Args args) throws IOException {
    ServerSocket server = new ServerSocket(args.port);
    System.out.println(args.toString());
    socket = server.accept();
    in = socket.getInputStream();
    out = socket.getOutputStream();
  }

  private static void serverLatency(Args args) throws IOException {
    Message globalMessage = messages.getSize(args.size);

    for (int i = 0; i < LATENCY_ITERATIONS; i++) {
      byte[] input = new byte[args.size];
      in.read(input, 0, args.size);
      Message recieved = Message.fromEncrypted(input);

      recieved.decrypt(key);
      if (!globalMessage.equals(recieved)) {
        System.out.println("Message failed to validate, ending test.");
        socket.close();
        return;
      }
      recieved.encrypt(key);
      out.write(recieved.bytes());
    }
  }

  private static void serverThroughput(Args args) throws IOException {
    Message globalMessage = messages.getSize(args.size);
    int MSG_ITERATIONS = THROUGHPUT_FILESIZE / args.size;

    while (!socket.isClosed()) {
      ArrayList<Message> messages = new ArrayList<>();

      int ack = 0;

      for (int i = 0; i < MSG_ITERATIONS; i++) {
        byte[] input = new byte[args.size];
        in.read(input, 0, args.size);
        out.write(ack);
        messages.add(Message.fromEncrypted(input));
      }

      for (int i = 0; i < messages.size(); i++) {
        Message m = messages.get(i);
        m.decrypt(key);
        if (!globalMessage.equals(m)) {
          System.out.println("Message " + i + " failed to validate, ending test.");
          socket.close();
          return;
        }
      }
    }
  }
}
