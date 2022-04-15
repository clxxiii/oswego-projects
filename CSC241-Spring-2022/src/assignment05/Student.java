package assignment05;

import javax.json.*;

/*
    CSC 241 Spring 2022
    Assignment 5
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

	public Student(String name, String id, int q1, int q2, int q3, int mid, int finalExam) {
		this.quiz = new int[3];

		this.name = name;
		this.id = id;
		this.quiz[0] = q1;
		this.quiz[1] = q2;
		this.quiz[2] = q3;
		this.mid = mid;
		this.finalExam = finalExam;

		// Create JSON Object
		JsonObjectBuilder joBuilder = Json.createObjectBuilder();
		joBuilder.add("name", name);
		joBuilder.add("id", id);

		JsonArrayBuilder courseWorksBuilder = Json.createArrayBuilder();
		JsonObjectBuilder workBuilder = Json.createObjectBuilder();
		workBuilder.add("name", "q1");
		workBuilder.add("score", q1);
		courseWorksBuilder.add(workBuilder.build());
		workBuilder.add("name", "q2");
		workBuilder.add("score", q2);
		courseWorksBuilder.add(workBuilder.build());
		workBuilder.add("name", "q3");
		workBuilder.add("score", q3);
		courseWorksBuilder.add(workBuilder.build());
		workBuilder.add("name", "midterm");
		workBuilder.add("score", mid);
		courseWorksBuilder.add(workBuilder.build());
		workBuilder.add("name", "final");
		workBuilder.add("score", finalExam);
		courseWorksBuilder.add(workBuilder.build());

		joBuilder.add("course works", courseWorksBuilder.build());

		jObject = joBuilder.build();
	}

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

		// Update score in class
		switch (element) {
			case "q1":
				quiz[0] = score;
				break;
			case "q2":
				quiz[1] = score;
				break;
			case "q3":
				quiz[2] = score;
				break;
			case "midterm":
				mid = score;
				break;
			case "final":
				finalExam = score;
				break;
			default:
				break;
		}

		// Update student in JSON
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
		jObject = newStudentObject;
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

	public String getName() {
		return this.name;
	}

	public String getId() {
		return this.id;
	}

	public int[] getQuiz() {
		return this.quiz;
	}

	public int getMid() {
		return this.mid;
	}

	public int getFinalExam() {
		return this.finalExam;
	}

}
