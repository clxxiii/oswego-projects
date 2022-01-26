package npw;

import painter.SPainter;
import shapes.SCircle;

import javax.swing.*;
import java.util.Random;
import java.util.Scanner;

public class AbstractGradient {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(AbstractGradient::new);
    }

    public AbstractGradient() { paintTheImage(); }

    private void paintTheImage() {
        // Grab the input information
        int width = getNumber("width");
        int height = getNumber("height");
        int dotSpace = getNumber("Distance in between the dots");

        // Establish the painter
        SPainter painter = new SPainter("Abstract Gradient", width, height);
        SCircle dot = new SCircle(5);

        // Move the painter to the upper left corner and get ready to paint the gradient
        painter.mfd(height/2.0);
        painter.tl();
        painter.mfd(width/2.0);
        painter.tl();

        // Paint!
        paintGradient(painter, dot, height, width, dotSpace);
    }

    private void paintGradient(SPainter painter, SCircle dot, int height, int width, int dotSpace) {
        // Calculate the number of columns, We want to fill the screen, but don't want any columns half on the canvas.
        // A column takes up the horizontal space of a dot's diameter plus the space between it and a neighbor.
        double colWidth = dot.diameter() + dotSpace;
        // We don't want a column all the way on the edge on the right side so subtract 1
        int nrOfCols = (int) Math.floor(( width / colWidth )) - 1;

        int cols = 0;
        while (cols < nrOfCols) {
            nextCol(painter, dot, dotSpace);
            paintColumn(painter, dot, height);
            cols = cols + 1;
        }
    }

    // Dots are spaced more tightly together near the bottom of the canvas
    private void paintColumn(SPainter painter, SCircle dot, int height) {
        int totalDistanceTraveled = 0;

        // Paint a column with a decreasing distance between dots
        while(totalDistanceTraveled < height - (dot.radius() * 3)) {
            int travel = randomDistance((height - totalDistanceTraveled) / 4);
            totalDistanceTraveled = totalDistanceTraveled + travel;
            painter.mfd(travel);
            paintOneDot(painter, dot);
        }
        // Make the method invariant with respect to painter position
        painter.mbk(totalDistanceTraveled);
    }

    private int randomDistance(int maxDistance) {
        Random rgen = new Random();
        return rgen.nextInt(maxDistance);
    }

    private void paintOneDot(SPainter painter, SCircle dot) {
        painter.paint(dot);
    }

    private void nextCol(SPainter painter, SCircle dot, int dotSpace) {
        painter.tl();
        painter.mfd(dot.diameter() + dotSpace);
        painter.tr();
    }

    private int getNumber(String message) {
        String nss = JOptionPane.showInputDialog(null, message+"?");
        Scanner sc = new Scanner(nss);
        int num = sc.nextInt();
        sc.close();
        return num;
    }
}
