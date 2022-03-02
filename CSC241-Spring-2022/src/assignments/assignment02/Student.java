package assignments.assignment02;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

/*
    CSC 241 Spring 2022
    Assignment 1
    Name: Eli Fereira
    ID: 806061464
 */

class Student {
	String name; // student's name
	private String id; // student's id

	private int[] quiz = new int[3]; // quiz scores (5 quizzes)
	private int mid; // midterm score
	private int finalExam; // final score
	Path path;
	File studentFile;

	// more variables may be added

	public Student(String courseName, String currentFileName) {
		path = Paths.get(System.getProperty("user.dir"), "src", "assignment02", "data", courseName,
				currentFileName);
		studentFile = new File(path.toString());

		try {
			Scanner sc = new Scanner(studentFile);
			String dataString = sc.nextLine();
			sc.close();
			String[] dataArr = dataString.split(",");

			name = dataArr[0];
			id = dataArr[1];
			quiz[0] = Integer.valueOf(dataArr[2]);
			quiz[1] = Integer.valueOf(dataArr[3]);
			quiz[2] = Integer.valueOf(dataArr[4]);
			mid = Integer.valueOf(dataArr[5]);
			finalExam = Integer.valueOf(dataArr[6]);

		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

	public void changeInfo(int index, int gradeChange) throws FileNotFoundException, IOException {
		File outputFile = new File(path.toString() + ".temp");
		FileOutputStream fileOutputStream = new FileOutputStream(outputFile);
		OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
		BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);

		if (index == 2)
			quiz[0] = gradeChange;
		else if (index == 3)
			quiz[1] = gradeChange;
		else if (index == 4)
			quiz[2] = gradeChange;
		else if (index == 5)
			mid = gradeChange;
		else if (index == 6)
			finalExam = gradeChange;

		String dataString = name + "," +
				id + "," +
				quiz[0] + "," +
				quiz[1] + "," +
				quiz[2] + "," +
				mid + "," +
				finalExam;

		bufferedWriter.write(dataString, 0, dataString.length());

		bufferedWriter.close();

		studentFile.delete();
		outputFile.renameTo(new File(path.toString()));
	}

	public String toString() {
		return "Name: " + name + " | " +
				"ID: " + id + " | " +
				"Q1: " + quiz[0] + " | " +
				"Q2: " + quiz[1] + " | " +
				"Q3: " + quiz[2] + " | " +
				"Midterm: " + mid + " | " +
				"Final: " + finalExam;
	}
}
