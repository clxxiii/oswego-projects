package assignment02;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;

abstract class Course {
	String name; // course name (e.g., Programming Methodology)
	String crn; // course crn (e.g., 14607)
	String code; // course code (cs241)
	int numOfSections;
	Path courseFolder;

	// more variables may be added

	// complete the class as described in assignment2.pdf

}

class Section extends Course {
	int capacity; // maximum number of students
	int curEnroll; // current number of students who enrolled
	String time;
	Student[] students;

	public Section(String courseName) {

		// Making an array of Student objects in the {id} directory:
		super.courseFolder = Paths.get(System.getProperty("user.dir"), "src", "assignment02", "data", courseName);
		curEnroll = new File(courseFolder.toString()).list().length;
		students = new Student[curEnroll];

		for (int i = 0; i < curEnroll; i++) {
			String currentFileName = new File(courseFolder.toString()).list()[i];
			students[i] = new Student(courseName, currentFileName);
		}
		Arrays.toString(students);

		// Reading course data from {id}.txt, and assigning them to instance variables:
		try {
			File sectionDataFile = new File(courseFolder.toString() + ".txt");

			Scanner sc = new Scanner(sectionDataFile);
			String[] data = new String[6];
			for (int i = 0; i < data.length; i++) {
				String line = sc.nextLine();
				line = line.substring(line.indexOf(":") + 1);
				data[i] = line;
			}
			sc.close();

			super.name = data[0];
			super.crn = data[1];
			capacity = Integer.valueOf(data[2]);
			super.code = data[3];
			time = data[4];
			super.numOfSections = Integer.valueOf(data[5]);

		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

	public String toString() {
		return "Name: " + name + " | " +
				"CRN: " + crn + " | " +
				"Code: " + code + " | " +
				"Capacity: " + capacity + " | " +
				"Time: " + time;
	}

	public Student getStudent(String studentName) throws FileNotFoundException {
		Student selectedStudent = null;
		for (Student student : students) {
			if (studentName.equalsIgnoreCase(student.name)) {
				selectedStudent = student;
			}
			;
		}
		if (selectedStudent == null) {
			throw new FileNotFoundException();
		}

		System.out.println(selectedStudent.toString());
		return selectedStudent;
	}
}