package npw;

import painter.SPainter;
import shapes.SCircle;
import shapes.SSquare;

import java.awt.Color;

import javax.swing.*;

public class Invention2 {

    int largestSideLength = 500;
    SPainter painter = new SPainter("Nondeterministic Invention", 600, 600);
    SSquare square = new SSquare(largestSideLength);
    SCircle circle = new SCircle(square.side() / 5);

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Invention2::new);
    }

    public Invention2() {
        paintTheImage();
    }

    private void paintTheImage() {

        for (int i = 0; i < 250; i = i + 2) {
            painter.setColor(getColor(i));
            square.shrink(2);
            painter.paint(square);
        }

        for (int i = 150; i < 250; i = i + 1) {
            painter.setColor(getColor(i));
            painter.paint(circle);
            circle.shrink(1);
        }
    }

    private Color getColor(int i) {
        return new Color(255 - getRandom(i),255 - getRandom(i),255 - getRandom(i));
    }

    private int getRandom(int i) {
        return (int) Math.floor((Math.random() * i));
    }

}
