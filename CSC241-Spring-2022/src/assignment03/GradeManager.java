package assignment03;

/*
    CSC 241 Spring 2022
    Assignment 3
    Name: Eli Fereira
    ID: 806061464
 */

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;
import java.util.Scanner;
import javax.json.*;

public class GradeManager {
	public static void main(String[] args) throws Exception {

		// Properties
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream("config.properties");
		prop.load(fis);

		// Read a json file
		String packageName = GradeManager.class.getPackageName();
		String fileName = getCourseCode();
		String filePath = prop.getProperty("filepath") + File.separator + packageName + File.separator + "data"
				+ File.separator + fileName + ".json";

		InputStream is = new FileInputStream(filePath);
		JsonReader jsonReader = Json.createReader(is);
		Section section = new Section(jsonReader.readObject());
		System.out.println(section.toString());

		// TODO: update the json file as user requests to edit
	}

	private static String getCourseCode() {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter the name of the course: ");
		String courseCode = sc.nextLine();
		sc.close();
		return courseCode;
	}
}
