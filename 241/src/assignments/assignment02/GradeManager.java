package assignments.assignment02;

import java.util.Scanner;

/*
    CSC 241 Spring 2022
    Assignment 1
    Name: Eli Fereira
    ID: 806061464
 */

public class GradeManager {
	public static void main(String[] args) throws Throwable {
		// Get course code and print
		System.out.print("Enter a course code: ");
		Scanner sc = new Scanner(System.in);
		Section section = new Section(sc.nextLine());
		while (true) {
			System.out.print("Select menu [edit | quit]? ");
			if (!(sc.nextLine().equalsIgnoreCase("edit"))) {
				sc.close();
				return;
			}

			System.out.print("Enter a student: ");
			Student selectedStudent = section.getStudent(sc.nextLine());

			System.out.print("Enter a coursework you want to edit (q1,q2,q3,mid,final): ");

			String input = sc.nextLine();
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
			else {
				System.out.println("User input is outside of the supported values.");
				sc.close();
				return;
			}

			System.out.print("Enter a new score: ");
			int gradeChange = Integer.valueOf(sc.nextLine());

			selectedStudent.changeInfo(index, gradeChange);
		}
	}
}
