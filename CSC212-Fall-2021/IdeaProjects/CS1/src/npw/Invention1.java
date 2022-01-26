package npw;

import painter.SPainter;
import shapes.SCircle;
import shapes.SSquare;

import java.awt.Color;

import javax.swing.*;


public class Invention1 {
    
    int largestSideLength = 500;
    SPainter painter = new SPainter("Deterministic Invention", 600, 600);
    SSquare square = new SSquare(largestSideLength);
    SCircle circle = new SCircle(square.side() / 5);
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(Invention1::new);
    }
    public Invention1() { paintTheImage(); }
    
    /*
     * I have an idea for the design
     * 
     * Basically, Make squares of side length 300, 280, 260, etc. All Inscribed.
     * By inscribed, i mean they are rotated so the corners touch the outside square's side.
     */
    private void paintTheImage() {
        
        for (int i = 250; i > 0; i = i - 2) {
            painter.setColor(getColor(i));
            square.shrink(2);
            painter.paint(square);
        }

        for (int i = 0; i < 250; i = i + 20) {
            painter.setColor(getColor(i));
            painter.paint(circle);
            circle.shrink(1);
        }
    }
    private Color getColor(int i) {
        return new Color(i, 255, 255);
    }

}
