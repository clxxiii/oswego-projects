package assignments.assignment04;

/*
    CSC 241 Spring 2022
    Assignment 4
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
	public static void main(String[] args) throws Exception {

		// Properties
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream("config.properties");
		prop.load(fis);

		// Searching Algorithms
		BinarySearch binarySearch = new BinarySearch();
		LinearSearch linearSearch = new LinearSearch();

		// Prompt for course code
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter a course code: ");
		String fileName = sc.next();
		String packageName = GradeManager.class.getPackageName();

		// Read a json file
		String filePath = prop.getProperty("filepath") + File.separator + packageName + File.separator + "data"
				+ File.separator + fileName + ".json";

		InputStream is = new FileInputStream(filePath);
		JsonReader jsonReader = Json.createReader(is);
		JsonObject jObject = jsonReader.readObject();
		Section section = new Section(jObject);
		System.out.println(section.toString());

		boolean edit = true;
		boolean find = true;
		while (edit) {
			// Ask to edit each time
			System.out.print("Select menu [find | edit | quit]? ");
			String editString = sc.next();
			switch (editString) {
				case "edit":
					edit = true;
					break;
				default:
					edit = false;
					break;
			}

			Student student;
			if (find) {
				System.out.print("Enter what you want: ");
				String searchType = sc.next();
				String searchKey = sc.next();
				Benchmark benchmark = new Benchmark();
				searchType = searchType.replace(",", "");
				student = linearSearch.search(section.students, searchType, searchKey);
				binarySearch.search(section.students, searchType, searchKey);
				System.out.println("[Benchmark] " + benchmark.getCounterLS() + " comparisons by Linear Search, "
						+ benchmark.getCounterBS() + " comparisons by Binary Search");
			}

			if (edit | find) {
				System.out.print("Enter a student: ");
				String studentName = sc.next();
				student = section.getStudent(studentName);
				if (!(student == null)) {
					System.out.println(student.toString());
					// Ask for course work
					System.out.print("Enter a coursework you want to edit: ");
					String courseWork = sc.next();

					// Ask for replacement score
					System.out.print("Enter a new score: ");
					int newScore = sc.nextInt();

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
						if (curStudent.name.equalsIgnoreCase(student.name)) {
							JsonObject newStudentObject = curStudent.updateInfo(courseWork, newScore);
							studentArrayBuilder.add(newStudentObject);
						} else {
							studentArrayBuilder.add(curStudent.asJsonObject());
						}
					}

					courseBuilder.add("Students", studentArrayBuilder.build());

					jObject = courseBuilder.build();

				} else {
					System.out.println("Student Not Found!");
				}
			}

		}

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
}
