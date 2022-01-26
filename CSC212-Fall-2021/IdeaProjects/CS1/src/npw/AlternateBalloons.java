/*
 * Program that paints 300 balloons of 6 different colors in a blue sky
 * It will feature commands
 */
package npw;

import painter.SPainter;
import shapes.SCircle;
import shapes.SSquare;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class AlternateBalloons {
    // Painter doing its thing
    private void paintTheImage() {
        SPainter painter = new SPainter("Alternate Balloons", 600, 600);
        paintSky(painter);
        int nROfBalloons = 300;
        paintBalloons(painter, nROfBalloons);
    }

    private void paintBalloons(SPainter painter, int nROfBalloons) {
        int i = 1;
        while (i <= nROfBalloons) {
            paintOneBalloon(painter);
            i++;
        }
    }

    private void paintOneBalloon(SPainter painter) {
        Random rgen = new Random();
        int rn = rgen.nextInt(6);
        if (rn == 0) { painter.setColor(Color.decode("#0EB8B9")); }
        else if (rn == 1) { painter.setColor(Color.decode("#11EE99")); }
        else if (rn == 2) { painter.setColor(Color.decode("#96F2E6")); }
        else if (rn == 3) { painter.setColor(Color.decode("#11222E")); }
        else if (rn == 4) { painter.setColor(Color.decode("#C18811")); }
        else { painter.setColor(Color.decode("#BADA55")); }
        painter.move();
        int balloonRadius = 20;
        SCircle balloon = new SCircle(balloonRadius);
        painter.paint(balloon);
        painter.setColor(Color.BLACK);
        painter.draw(balloon);
    }

    private void paintSky(SPainter painter) {
        painter.setColor(Color.BLUE);
        SSquare sky = new SSquare(2000);
        painter.paint(sky);
    }

    // Required Infrastructure
    public AlternateBalloons() { paintTheImage(); }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new AlternateBalloons();
            }
        });
    }
}
