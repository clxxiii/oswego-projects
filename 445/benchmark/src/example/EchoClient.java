package example;

import java.io.*;
import java.net.*;

public class EchoClient {
  public static void main(String[] args) {
    String host = "gee.cs.oswego.edu";
    int echoServicePortNumber = 7;

    Socket echoSocket = null;
    PrintWriter out = null;
    BufferedReader in = null;

    try {
      echoSocket = new Socket(host, echoServicePortNumber);
      out = new PrintWriter(echoSocket.getOutputStream(), true);
      in = new BufferedReader(new InputStreamReader(
          echoSocket.getInputStream()));
    } catch (UnknownHostException e) {
      System.err.println("Don't know about host " + host);
      e.printStackTrace();
      System.exit(1);
    } catch (IOException e) {
      System.err.println("Couldn't get I/O for the connection.");
      e.printStackTrace();
      System.exit(1);
    }

    try {
      BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
      String userInput;

      while ((userInput = stdIn.readLine()) != null) {
        out.println(userInput);
        System.out.println("echo: " + in.readLine());
      }

      out.close();
      in.close();
      stdIn.close();
      echoSocket.close();
    } catch (IOException ex) {
      System.err.println("IO failure.");
      ex.printStackTrace();
    }
  }
}
