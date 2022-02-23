package assignment02;

import java.io.File;

/*
    CSC 241 Spring 2022
    Assignment 1
    Name: Eli Fereira
    ID: 806061464
 */

import java.util.Scanner;

abstract class Course {
    String name;            // course name (e.g., Programming Methodology)
    String crn;             // course crn (e.g., 14607)
    String code;            // course code (cs241)

    // more variables may be added

    // complete the class as described in assignment2.pdf

}

class Section extends Course {
    int capacity;           // maximum number of students
    int curEnrol;           // current number of students who enrolled
    String time;

	public Section(String path) {
		File sectionDataFile = new File(path);
		try {
			Scanner sc = new Scanner(sectionDataFile);
			String[] data = new String[6];
			for(int i = 0; i < data.length; i++) {
				String line = sc.nextLine();
				line = line.substring(line.indexOf(":") + 1);
				data[i] = line;
			}
			sc.close();
			
			super.name = data[0];
			super.crn = data[1];
			this.capacity = Integer.valueOf(data[2]);
			super.code = data[3];
			this.time = data[4];
			this.curEnrol = Integer.valueOf(data[5]);

		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}
    // more variables may be added

    // complete the class as described in assignment2.pdf
    
}