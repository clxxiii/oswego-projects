/*
 * Basic computations using shapes and the NPW
 */
package shapes;

import painter.SPainter;

import java.awt.*;

public class ShapesThing {

    public static void main(String[] args) {
        // Task 8
        SSquare square = new SSquare(400);

        System.out.println("square = " + square.toString());
        System.out.println("area of square = " + square.area());
        System.out.println("perimiter of square = " + square.perimeter());
        System.out.println("diagonal of square = " + square.diagonal());

        // Task 9
        SCircle disk = square.inscribingCircle();

        System.out.println("disk = " + disk.toString());
        System.out.println("area of disk = " + disk.area());
        System.out.println("perimiter of disk = " + disk.perimeter());

        // Task 10
        SSquare diamond = disk.inscribingSquare();
        System.out.println("diamond = " + diamond.toString());

        // Display all three objects
        SPainter painter = new SPainter("Blue Diamond", 600, 600);
        painter.setColor(Color.BLACK);
        
    }
}
