package npw;

import painter.SPainter;
import shapes.SCircle;

import javax.swing.*;
import java.awt.*;

public class Dots {
    public void paintTheImage() {
        SPainter painter = new SPainter("Dots", 600, 600);
        SCircle dot = new SCircle(0);
        paintRowOfCircles(painter, dot, Color.CYAN,25, -100, 75);
        paintRowOfCircles(painter, dot, Color.YELLOW,30, 0, 100);
        paintRowOfCircles(painter, dot, Color.MAGENTA,35, 100, 150);
    }
    public void paintRowOfCircles(SPainter painter, SCircle dot, Color color,int radius, double lat, double spread) {
        dot.setRadius(radius);
        painter.mfd(lat);
        painter.setColor(color);
        painter.paint(dot);
        painter.setHeading(90.0);
        painter.mfd(spread);
        painter.paint(dot);
        painter.mbk(spread*2);
        painter.paint(dot);
        painter.mfd(spread);
        painter.faceNorth();
        painter.mbk(lat);
    }
    public Dots() { paintTheImage(); }

    public static void main( String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Dots();
            }
        });
    }
}
