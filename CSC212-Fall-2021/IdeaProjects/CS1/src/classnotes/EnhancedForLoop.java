package classnotes;

import java.util.ArrayList;

import painter.SPainter;
import shapes.SCircle;

import java.awt.*;

public class EnhancedForLoop {
    private static ArrayList<Color> colors = new ArrayList<>();
    
    public static void main(String[] args) {
        colors.add(Color.RED);
        colors.add(Color.ORANGE);
        colors.add(Color.YELLOW);
        colors.add(Color.GREEN);
        colors.add(Color.BLUE);
        colors.add(Color.PINK);

        /*
         * THE BIG IDEA
         */
        for (Color c : colors) {
            System.out.println(c);
        }

        paintRowOfDots(colors);

    }
    private static void paintRowOfDots(ArrayList<Color> colors) {
        SPainter painter = new SPainter("Dots", 700, 700);
        SCircle dot = new SCircle(30);
        int starSides = 10;
        for (int i = 0; i <= starSides; i++) {
            paintRow(painter, dot, colors);
            painter.setHeading((360 / starSides) * i);
        }
    }

    private static void paintRow(SPainter painter, SCircle dot, ArrayList<Color> colors) {
        for (Color c : colors) {
            painter.setColor(c);
            painter.paint(dot);
            painter.mrt(dot.diameter());
        }
        painter.mlt(dot.diameter() * colors.size());
    }
}
