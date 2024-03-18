package edu.oswego.minesweeper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.io.PrintWriter;

@SpringBootApplication
public class MinesweeperApplication {


	public static SolverServer server;
	PrintWriter in;
	public static void main(String[] args) throws IOException {
		SpringApplication.run(MinesweeperApplication.class, args);

		String hostname = "rho.cs.oswego.edu";
		int port = 26971;
		server = new SolverServer(hostname, port);
	}
}
