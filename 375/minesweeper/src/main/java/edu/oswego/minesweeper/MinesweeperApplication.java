package edu.oswego.minesweeper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@SpringBootApplication
public class MinesweeperApplication {


	public static SolverServer server;
	PrintWriter in;
	public static void main(String[] args) throws IOException {
		SpringApplication.run(MinesweeperApplication.class, args);

		String hostname = "localhost";
		int port = 26970;
		server = new SolverServer(hostname, port);
	}
}
