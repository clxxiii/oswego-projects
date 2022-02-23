package assignment02;

import java.nio.file.*;
import java.util.Scanner;

/*
    CSC 241 Spring 2022
    Assignment 1
    Name: Eli Fereira
    ID: 806061464
 */

public class GradeManager {
    public static void main(String[] args) throws Exception {
        // Get course code and print
        System.out.print("Enter a course code: ");
        Scanner sc = new Scanner(System.in);
		Path studentPath = Paths.get(System.getProperty("user.dir"), "src", "assignment02","data", sc.next() + ".txt");
		sc.close();
		Section section = new Section(studentPath.toString());
		System.out.println(section.name);
    }
}
