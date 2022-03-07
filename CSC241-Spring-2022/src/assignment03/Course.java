package assignment03;

/*
    CSC 241 Spring 2022
    Assignment 3
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

	public Section(JsonObject json) {
		// Get variables from JSON
		super.name = json.getString("name");
		super.crn = json.getString("crn");
		super.code = json.getString("code");
		this.capacity = json.getInt("capacity");

		// Make array of student objects from Json array
		JsonArray studentArray = json.getJsonArray("students");
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

}