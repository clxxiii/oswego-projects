package assignment03;

/*
    CSC 241 Spring 2022
    Assignment 3
    Name: Eli Fereira
    ID: 806061464
 */

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;
import java.util.Scanner;

public class GradeManager {
    public static void main(String[] args) throws Exception {

        // Properties
        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream("config.properties");
        prop.load(fis);

        // Read a json file
        String packageName = GradeManager.class.getPackageName();
        Scanner scan = new Scanner(System.in);
        String fileName = null;            // TODO: you should scan the course code from user's input.
        String filePath = prop.getProperty("filepath") + File.separator + packageName + File.separator + "data" + File.separator + fileName;

        // TODO: read a json file
        // TODO: update the json file as user requests to edit
    }
}
