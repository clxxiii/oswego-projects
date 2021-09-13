/*
 * Program to paint a blue dot using the NPW
 */


package npw;

import painter.SPainter;
import shapes.SCircle;

import javax.swing.*;
import java.awt.*;

public class BlueDot {
    // Blue dot code
    private void paintTheImage() {
        SPainter klee = new SPainter("Blue Dot", 600, 600);
        SCircle dot = new SCircle(200);
        klee.setColor(Color.BLUE);
        klee.paint(dot);
    }

    // call paintTheImage() upon construction
    public BlueDot() {
        paintTheImage();
    }

    // Construct a blue dot when calling main()
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new BlueDot();
            }
        });
    }
}
