package npw;

import painter.SPainter;
import shapes.SCircle;

import javax.swing.*;
import java.awt.*;

public class BlueDot {
    private void paintTheImage() {
        SPainter klee = new SPainter("Blue Dot", 600, 600);
        SCircle dot = new SCircle(200);
        klee.setColor(Color.decode("#0EB8B9"));
        klee.paint(dot);
    }
    public BlueDot() {
        paintTheImage();
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new BlueDot();
            }
        });
    }
}
