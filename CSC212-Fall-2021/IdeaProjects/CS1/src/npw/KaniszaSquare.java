package npw;

import painter.SPainter;
import shapes.SCircle;
import shapes.SSquare;

import javax.swing.*;

public class KaniszaSquare {
    public void paintTheImage() {
        // Make Objects
        SPainter painter = new SPainter("Kanizsa Square",400,400);

        SSquare square = new SSquare(200);
        SCircle dot = new SCircle(50);

        // Creation Methods
        drawBlackSquare(painter, square);
        paintBlueCircles(painter, dot);
        paintGreenCircles(painter, dot);
        paintWhiteSquare(painter, square);
    }

    public void drawBlackSquare(SPainter painter, SSquare square) {
        painter.setHeading(45.0);
    }
    // Necessary for NPW
    public KaniszaSquare() { paintTheImage(); }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new KaniszaSquare();
            }
        });
    }
}
