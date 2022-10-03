package assignments.assignment06;

/*
    CSC 241 Spring 2022
    Assignment 5
    Name: Eli Fereira
    ID: 806061464
 */

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Scanner;
import javax.json.*;
import javax.json.stream.JsonGenerator;

import utility.Benchmark;

public class GradeManager {
	static Scanner sc = new Scanner(System.in);
	static Section section;
	static boolean quit = false;
	static JsonObject jObject;

	public static void main(String[] args) throws Exception {

		// Properties
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream("config.properties");
		prop.load(fis);

		// Prompt for course code

		System.out.print("Enter a course code: ");
		String fileName = sc.next();
		String packageName = GradeManager.class.getPackageName();

		// Read a json file
		String filePath = prop.getProperty("filepath") + File.separator + packageName + File.separator + "data"
				+ File.separator + fileName + ".json";

		InputStream is = new FileInputStream(filePath);
		JsonReader jsonReader = Json.createReader(is);
		jObject = jsonReader.readObject();
		section = new Section(jObject);
		System.out.println(section.toString());

		while (!quit) {
			// Ask for input each time
			System.out.print("Select menu [find | add | remove | edit | quit]? ");
			String actionString = sc.next();

			if (actionString.equalsIgnoreCase("find")) {
				findHandler();
			} else if (actionString.equalsIgnoreCase("add")) {
				addHandler();
			} else if (actionString.equalsIgnoreCase("remove")) {
				removeHandler();
			} else if (actionString.equalsIgnoreCase("edit")) {
				editHandler();
			} else if (actionString.equalsIgnoreCase("quit")) {
				quit = true;
			} else {
				System.out.println("Invalid input");
			}
		}

		// Build new Course Object
		JsonObjectBuilder courseBuilder = Json.createObjectBuilder();
		courseBuilder.add("Name", section.name);
		courseBuilder.add("CRN", section.crn);
		courseBuilder.add("Capacity", section.capacity);
		courseBuilder.add("Code", section.code);
		courseBuilder.add("Number of Sections", section.numOfSections);
		courseBuilder.add("Name", section.name);

		// Build Student Array
		JsonArrayBuilder studentArrayBuilder = Json.createArrayBuilder();

		for (Student curStudent : section.students) {
			studentArrayBuilder.add(curStudent.asJsonObject());
		}

		courseBuilder.add("Students", studentArrayBuilder.build());

		jObject = courseBuilder.build();

		// Prepare jWriter for writing
		Map<String, Boolean> config = new HashMap<String, Boolean>();
		config.put(JsonGenerator.PRETTY_PRINTING, true);
		OutputStream os = new FileOutputStream(filePath);
		JsonWriterFactory jwFactory = Json.createWriterFactory(config);
		JsonWriter jWriter = jwFactory.createWriter(os);

		jWriter.write(jObject);
		jWriter.close();
		sc.close();
		os.close();

	}

	private static void removeHandler() {
		System.out.print("Enter what you want to remove: ");

		String searchType = sc.next().replace(",", "");
		String searchKey = sc.next();
		for (int i = 0; i < section.students.length; i++) {

			Student student = section.students[i];

			if (searchType.equalsIgnoreCase("name")) {
				if (searchKey.equalsIgnoreCase(student.getName())) {
					section.removeStudent(student);
				}
			} else if (searchType.equalsIgnoreCase("id")) {
				if (searchKey.equalsIgnoreCase(student.getId())) {
					section.removeStudent(student);
				}
			} else if (searchType.equalsIgnoreCase("q1")) {
				if (searchKey.equalsIgnoreCase("" + student.getQuiz()[0])) {
					section.removeStudent(student);
				}
			} else if (searchType.equalsIgnoreCase("q2")) {
				if (searchKey.equalsIgnoreCase("" + student.getQuiz()[1])) {
					section.removeStudent(student);
				}
			} else if (searchType.equalsIgnoreCase("q3")) {
				if (searchKey.equalsIgnoreCase("" + student.getQuiz()[2])) {
					section.removeStudent(student);
				}
			} else if (searchType.equalsIgnoreCase("midterm")) {
				if (searchKey.equalsIgnoreCase("" + student.getMid())) {
					section.removeStudent(student);
				}
			} else if (searchType.equalsIgnoreCase("final")) {
				if (searchKey.equalsIgnoreCase("" + student.getFinalExam())) {
					section.removeStudent(student);
				}
			}
		}
	}

	private static void addHandler() {
		System.out.print("Enter what you want to add: ");

		String name = sc.next().replace(",", "");
		String id = sc.next().replace(",", "");
		int q1 = Integer.valueOf(sc.next().replace(",", ""));
		int q2 = Integer.valueOf(sc.next().replace(",", ""));
		int q3 = Integer.valueOf(sc.next().replace(",", ""));
		int mid = Integer.valueOf(sc.next().replace(",", ""));
		int finalExam = Integer.valueOf(sc.next().replace(",", ""));

		Student newStudent = new Student(name, id, q1, q2, q3, mid, finalExam);

		section.addStudent(newStudent);

		System.out.println(newStudent + " has been added.");
	}

	private static void findHandler() {
		// Searching Algorithms
		BinarySearch binarySearch = new BinarySearch();
		LinearSearch linearSearch = new LinearSearch();

		System.out.print("Enter what you want: ");

		String searchType = sc.next();
		String searchKey = sc.next();
		Benchmark benchmark = new Benchmark();
		searchType = searchType.replace(",", "");

		Student student = linearSearch.search(section.students, searchType, searchKey);
		if (searchType == "name") {
			binarySearch.search(section.students, searchType, searchKey);
		}

		System.out.println("[Benchmark] " + benchmark.getCounterLS() + " comparisons by Linear Search, "
				+ benchmark.getCounterBS() + " comparisons by Binary Search");
		if (!(student == null)) {
			studentHandler(student);
		} else {
			System.out.println("Student not found");
		}
	}

	private static void editHandler() {
		System.out.print("Enter a student: ");
		String studentName = sc.next();
		Student student = section.getStudent(studentName);
		if (!(student == null)) {
			studentHandler(student);
		} else {
			System.out.println("Student not found");
		}
	}

	private static void studentHandler(Student student) {
		System.out.println(student.toString());
		// Ask for course work
		System.out.print("Enter a coursework you want to edit: ");
		String courseWork = sc.next();

		// Ask for replacement score
		System.out.print("Enter a new score: ");
		int newScore = sc.nextInt();

		student.updateInfo(courseWork, newScore);
	}
}
