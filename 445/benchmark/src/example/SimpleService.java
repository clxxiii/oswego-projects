package example;

import java.net.*;
import java.io.*;

public class SimpleService {
  static final int PORT = 2688;

  public static void main(String[] args) {
    try {
      ServerSocket serverSocket = new ServerSocket(PORT);

      for (;;) {
        Socket client = serverSocket.accept();

        PrintWriter out = new PrintWriter(client.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));

        String cmd = in.readLine();

        String reply = "<html>\n" +
            "<head><title>Testing</title></head>\n" +
            "<body><h1>Hello World!</h1></body>\n" +
            "Got request:<br>\n " +
            cmd +
            "\n</html>\n";

        int len = reply.length();

        out.println("HTTP/1.0 200 OK");
        out.println("Content-Length: " + len);
        out.println("Content-Type: text/html\n");
        out.println(reply);

        out.close();
        in.close();
        client.close();
      }
    } catch (IOException ex) {
      ex.printStackTrace();
      System.exit(-1);
    }
  }
}
