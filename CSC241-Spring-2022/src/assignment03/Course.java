package assignment03;

/*
    CSC 241 Spring 2022
    Assignment 3
    Name: Eli Fereira
    ID: 806061464
 */


import java.sql.Time;

abstract class Course {
    String name;            // course name (e.g., Programming Methodology)
    String crn;             // course crn (e.g., 14607)
    String code;            // course code (cs241)

    // more variables may be added

    // TODO: complete the class as described in assignment2.pdf

}

class Section extends Course {
    int capacity;           // maximum number of students
    int curEnrol;           // current number of students who enrolled
    Time time;

    // more variables may be added

    // TODO: complete the class as described in assignment2.pdf
    
}