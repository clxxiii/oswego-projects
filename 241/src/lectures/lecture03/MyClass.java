package lectures.lecture03;

import java.io.File;
import java.nio.file.FileSystems;
import java.util.Scanner;

public class MyClass {
    public static void main(String[] args) throws Exception {

        // classroom
        Classroom classroom = new LectureClassRoom("172", 48);
        classroom.initializeClassTime(10);
        ((LectureClassRoom)classroom).setClassTime(1);

//        for (int i = 0; i < classroom.classTime.length; i++) {
//            if (classroom.classTime[i])
//                System.out.println("Time " + (i+1) + " is occupied");
//            else
//                System.out.println("Time " + (i+1) + " is not occupied");
//        }

        // textbook
        Book myBook = new Textbook("Dr. Laker");
        myBook.showAuthor();
        myBook.showPublisher();
        ((Textbook)myBook).addMaxPage(100);
        myBook.showMaxPage();

        // students
        String fileName = "roster.txt";
        String packageName = MyClass.class.getPackage().getName();
        String filePath = FileSystems.getDefault().getPath("src",packageName, fileName).toAbsolutePath().toString();
        File inputFile = new File(filePath);

        Person[] people = new Person[5];
        int pIndex = 0;
        Instructor instructor = new Instructor("Dr. Laker", 99, "Professor");
        people[pIndex] = instructor;
		
        Scanner line = new Scanner(inputFile);
        String name, id;
		
        while (line.hasNext()) {
            String rLine = line.nextLine();         // student's name
            name = rLine.substring(0,rLine.lastIndexOf(" "));
            id = rLine.substring(rLine.lastIndexOf(" ")+1);
			
            people[++pIndex] = new Student(name, 19, id);
        }
		
        for (int i = 0; i < 5; i++) {
			//System.out.println(people[i].name);
            people[i].identify();
        }
		line.close();
    }
}