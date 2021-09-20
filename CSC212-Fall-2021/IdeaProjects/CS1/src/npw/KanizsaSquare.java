package npw;

import painter.SPainter;
import shapes.SCircle;
import shapes.SSquare;

import javax.swing.*;
import java.awt.*;

public class KanizsaSquare {
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
        painter.setColor(Color.BLACK);
        painter.draw(square);
        painter.setHeading(0.0);
    }

    public void paintBlueCircles(SPainter painter, SCircle dot) {
        Color color = Color.BLUE;

        moveAndPaintCircle(painter, dot, 100.0, -100.0, color);
        moveAndPaintCircle(painter, dot, -100.0, 100.0, color);
    }

    public void paintGreenCircles(SPainter painter, SCircle dot) {
        Color color = Color.GREEN;

        moveAndPaintCircle(painter, dot, -100.0, -100.0, color);
        moveAndPaintCircle(painter, dot, 100.0, 100.0, color);
    }

    public void paintWhiteSquare(SPainter painter, SSquare square) {
        painter.setColor(Color.WHITE);
        painter.paint(square);
    }
    // Method for moving and painting circles to cut down on code size
    public void moveAndPaintCircle(SPainter painter, SCircle dot, double moveNorth, double moveWest, Color color) {
        painter.mfd(moveNorth);
        painter.setHeading(90); painter.mfd(moveWest);
        painter.setColor(color);
        painter.paint(dot);
        painter.mbk(moveWest);
        painter.faceNorth(); painter.mbk(moveNorth);
    }

    // Necessary for NPW
    public KanizsaSquare() { paintTheImage(); }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new KanizsaSquare();
            }
        });
    }
}
