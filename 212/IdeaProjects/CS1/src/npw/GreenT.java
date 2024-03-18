/*
 * Program to paint a green T using the NPW
 */


package npw;

import painter.SPainter;
import shapes.SRectangle;

import javax.swing.*;
import java.awt.*;

public class GreenT {
    // Green T code
    private void paintTheImage() {
        // Make Painter
        SPainter klee = new SPainter("Green T", 600, 600);
        // Make Rectangle
        SRectangle rect = new SRectangle(400,100);
        // Move the painter down to make room for the T
        klee.setHeading(180); klee.mfd(50);
        // Make painter paint in green
        klee.setColor(Color.GREEN);
        //Paint rectangle for the first time
        klee.paint(rect);

        // Change the dimensions of the rectangle
        rect.setHeight(100); rect.setWidth(400);
        //Move painter up (the painter is already facing down, so I just move it backwards)
        klee.mbk(250);
        //Paint the rectangle again
        klee.paint(rect);
    }

    // call paintTheImage() upon construction
    public GreenT() {
        paintTheImage();
    }

    // Construct a blue dot when calling main()
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new GreenT();
            }
        });
    }
}
