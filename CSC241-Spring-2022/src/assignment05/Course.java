package assignment05;

/*
    CSC 241 Spring 2022
    Assignment 5
    Name: Eli Fereira
    ID: 806061464
 */

import javax.json.*;

abstract class Course {
	String name; // course name (e.g., Programming Methodology)
	String crn; // course crn (e.g., 14607)
	String code; // course code (cs241)
}

class Section extends Course {
	int capacity; // maximum number of students
	int curEnrol; // current number of students who enrolled
	String time;
	Student[] students;
	int numOfSections;

	public Section(JsonObject json) {
		// Get variables from JSON
		super.name = json.getString("Name");
		super.crn = json.getString("CRN");
		super.code = json.getString("Code");
		this.capacity = json.getInt("Capacity");
		numOfSections = json.getInt("Number of Sections");

		// Make array of student objects from Json array
		JsonArray studentArray = json.getJsonArray("Students");
		curEnrol = studentArray.size();
		students = new Student[curEnrol];

		for (int i = 0; i < curEnrol; i++) {
			JsonValue student = studentArray.get(i);
			student = student.asJsonObject();
			students[i] = new Student(student.asJsonObject());
		}
	}

	public String toString() {
		return "Name: " + name + " | " +
				"CRN: " + crn + " | " +
				"Code: " + code + " | " +
				"Capacity: " + capacity + " | " +
				"Time: " + time;
	}

	public Student getStudent(int index) {
		return students[index];
	}

	public void addStudent(Student student) {
		int index = 0;
		for (int i = 0; i < students.length; i++) {
			String compStuName = students[i].name;
			int comparison = compStuName.compareTo(student.name);
			if (comparison <= 0) {
				index = i + 1;
			}
		}

		Student[] newArr = new Student[students.length + 1];
		for (int i = 0; i < newArr.length; i++) {
			if (i < index) {
				newArr[i] = students[i];
			} else if (i == index) {
				newArr[i] = student;
			} else if (i > index) {
				newArr[i] = students[i - 1];
			}
		}

		students = newArr;
	}

	public Student getStudent(String studentName) {
		for (Student student : students) {
			String studentTest = student.name;
			if (studentTest.equalsIgnoreCase(studentName)) {
				return student;
			}
		}
		return null;
	}

	public void removeStudent(Student student) {
		for (int i = 0; i < students.length; i++) {
			Student compStu = students[i];
			if (student.equals(compStu)) {
				Student[] newArr = new Student[students.length - 1];
				for (int v = 0; v < newArr.length; v++) {
					if (v < i) {
						newArr[v] = students[v];
					} else if (v >= i) {
						newArr[v] = students[v + 1];
					}
				}
				students = newArr;
				System.out.println(student + " has been removed.");
			}
		}
	}

}