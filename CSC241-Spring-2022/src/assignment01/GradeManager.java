package assignment01;

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
        boolean noExit = true;
        // String studentName = promptForInput("Enter a students name: ");

        Path p = Paths.get(System.getProperty("user.dir"), "assignment01", "John.txt");

        System.out.println(p.toString());

        // File studentFile = new File(p.toString());

        // Scanner sc = new Scanner(studentFile);

        // System.out.println(sc.nextLine());

        // while (noExit) {

        // noExit = false;
        // }
    }

    private static String promptForInput(String msg) {
        System.out.print(msg);
        Scanner sc = new Scanner(System.in);
        String name = sc.nextLine();
        sc.close();
        return name;
    }
}
