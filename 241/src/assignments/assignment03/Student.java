package assignments.assignment03;

import javax.json.*;

/*
    CSC 241 Spring 2022
    Assignment 3
    Name: Eli Fereira
    ID: 806061464
 */

class Student {
	String name; // student's name
	private String id; // student's id

	private int[] quiz; // quiz scores (5 quizzes)
	private int mid; // midterm score
	private int finalExam; // final score
	private JsonObject jObject;

	public Student(JsonObject json) {
		this.name = json.getString("name");
		this.id = json.getString("id");
		this.jObject = json;

		JsonArray courseWorks = json.getJsonArray("course works");
		quiz = new int[3];
		for (int i = 0; i < courseWorks.size(); i++) {
			JsonObject test = courseWorks.get(i).asJsonObject();
			String name = test.getString("name");
			int nameIndex;

			switch (name) {
				case "q1":
					nameIndex = 0;
					break;
				case "q2":
					nameIndex = 1;
					break;
				case "q3":
					nameIndex = 2;
					break;
				case "midterm":
					nameIndex = 3;
					break;
				case "final":
					nameIndex = 4;
					break;
				default:
					nameIndex = -1;
					break;
			}

			if (0 <= nameIndex && nameIndex < 3) {
				quiz[nameIndex] = test.getInt("score");
			} else if (nameIndex == 3) {
				mid = test.getInt("score");
			} else if (nameIndex == 4) {
				finalExam = test.getInt("score");
			}
		}
	}

	public JsonObject asJsonObject() {
		return jObject;
	}

	public JsonObject updateInfo(String element, int score) {
		JsonArray courseWorks = jObject.getJsonArray("course works");
		JsonArrayBuilder jaBuilder = Json.createArrayBuilder();
		JsonObjectBuilder joBuilder = Json.createObjectBuilder();
		for (int i = 0; i < courseWorks.size(); i++) {
			JsonObject value = courseWorks.get(i).asJsonObject();

			if (value.getString("name").equalsIgnoreCase(element)) {
				joBuilder.add("name", value.getString("name"));
				joBuilder.add("score", score);
				jaBuilder.add(joBuilder.build());
			} else {
				jaBuilder.add(value);
			}
		}

		// Build Student Object again
		JsonObjectBuilder studentBuilder = Json.createObjectBuilder();
		studentBuilder.add("name", name);
		studentBuilder.add("id", id);
		studentBuilder.add("course works", jaBuilder.build());
		JsonObject newStudentObject = studentBuilder.build();
		return newStudentObject;
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
