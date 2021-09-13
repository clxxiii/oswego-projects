/*
 * Program to paint a target using the NPW
 */


package npw;

import painter.SPainter;
import shapes.SCircle;

import javax.swing.*;
import java.awt.*;

public class Target {
    // Target Code
    private void paintTheImage() {
        // Make the painter
        SPainter klee = new SPainter("Target", 600, 600);
        // Make the three dots
        SCircle outerDot = new SCircle(150);
        SCircle middleDot = new SCircle(100);
        SCircle innerDot = new SCircle(50);
        // Paint dot 1
        klee.setColor(Color.RED);
        klee.paint(outerDot);
        // Paint dot 2
        klee.setColor(Color.WHITE);
        klee.paint(middleDot);
        // Paint dot 3
        klee.setColor(Color.RED);
        klee.paint(innerDot);
    }

    // call paintTheImage() upon construction
    public Target() {
        paintTheImage();
    }

    // Construct a blue dot when calling main()
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Target();
            }
        });
    }
}
