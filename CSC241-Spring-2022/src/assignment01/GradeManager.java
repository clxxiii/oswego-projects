package assignment01;

import java.io.*;
import java.nio.file.*;
import java.util.Scanner;

/*
    CSC 241 Spring 2022
    Assignment 1
    Name: Eli Fereira
    ID: 806061464
 */

public class GradeManager {
	public static void main(String[] args) throws FileNotFoundException {

		// Prompt for file name
		String studentName = promptForInput("Enter a students name: ", false);
		Path studentPath = Paths.get(System.getProperty("user.dir"), "src", "assignment01", studentName + ".txt");
		File studentFile = new File(studentPath.toString());

		// Read selected file
		Scanner sc = new Scanner(studentFile);
		String[] info = new String(sc.nextLine()).split(",");
		sc.close();
		printInfoString(info);

		String editOrQuit = promptForInput("Do you want to edit or quit? (edit | quit)? ", false);

		if (editOrQuit.equalsIgnoreCase("edit")) {
			try {
				String input = promptForInput("Enter a course work you want to change (q1,q2,q3,mid,final): ", false);

				// Replace input with array index
				int index;
				if (input.equalsIgnoreCase("Q1"))
					index = 2;
				else if (input.equalsIgnoreCase("Q2"))
					index = 3;
				else if (input.equalsIgnoreCase("Q3"))
					index = 4;
				else if (input.equalsIgnoreCase("mid"))
					index = 5;
				else if (input.equalsIgnoreCase("final"))
					index = 6;
				else
					throw new Throwable("User input is outside of the supported values.");
				String newScore = promptForInput("\nEnter a new score: ", true);
				info[index] = newScore;

				String dataString = String.join(",", info);

				writeDataToFile(studentFile, studentPath, dataString);

			} catch (Throwable e) {
				System.out.println(e.toString());
			}
		}

	}

	private static void writeDataToFile(File studentFile, Path studentPath, String dataString)
			throws IOException {
		File outputFile = new File(studentPath.toString() + ".temp");
		FileOutputStream fileOutputStream = new FileOutputStream(outputFile);
		OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
		BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);

		bufferedWriter.write(dataString, 0, dataString.length());

		bufferedWriter.close();

		studentFile.delete();
		outputFile.renameTo(new File(studentPath.toString()));
	}

	/**
	 * Format info in readable format:
	 * Name: John | ID: 123456789 | Q1: 10 | Q2: 8 | Q3: 7 | Midterm: 87 | Final: 91
	 */
	private static void printInfoString(String[] info) {
		System.out.println(
				"Name: " + info[0] + " | " +
						"ID: " + info[1] + " | " +
						"Q1: " + info[2] + " | " +
						"Q2: " + info[3] + " | " +
						"Q3: " + info[4] + " | " +
						"Midterm: " + info[5] + " | " +
						"Final: " + info[6]);
	}

	/**
	 * Sends a message to the console and returns the user input
	 */
	private static String promptForInput(String msg, boolean closeScanner) {
		System.out.print(msg);
		Scanner sc = new Scanner(System.in);
		String input = sc.nextLine();
		if (closeScanner)
			sc.close();
		return input;
	}

}
