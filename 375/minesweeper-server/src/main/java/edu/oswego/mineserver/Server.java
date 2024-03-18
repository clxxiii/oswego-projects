package edu.oswego.mineserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import org.json.JSONObject;

public class Server {
    final static int portNumber = 26970;

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(portNumber);
            Socket clientSocket = serverSocket.accept();
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            String inputLine, outputLine;

            while ((inputLine = in.readLine()) != null) {
                JSONObject input = new JSONObject(inputLine);
                JSONObject output = Solver.solve(input);
                outputLine = output.toString();
                out.println(outputLine);
            }
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }

}
