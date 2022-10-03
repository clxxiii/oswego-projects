package npw;

import java.util.Scanner;
import java.awt.Color;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import painter.SPainter;
import shapes.SSquare;

public class Stella {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(Stella::new);
    }

    public Stella() {
        paintSetup();
    }

    private void paintSetup() {
        int nrOfSquares = getNumber("how many squares");
        Color outsideColor = new Color(getRandom(255), getRandom(255), getRandom(255));
        Color insideColor = new Color(getRandom(255), getRandom(255), getRandom(255));
        int canvasSize = 800;
        int largestSquareSize = 700;

        SPainter painter = new SPainter("Stella Squares", canvasSize, canvasSize);
        SSquare square = new SSquare(largestSquareSize);
        
        paintTheImage(painter, square, nrOfSquares, insideColor, outsideColor);
        
    }
    
    private void paintTheImage(SPainter painter, SSquare square, int nrOfSquares, Color insideColor,
    Color outsideColor) {
        
        int currentSquareSize = (int) square.side();
        double squareDecreasingSize = (double) square.side() / nrOfSquares;

        for(int i = 0; currentSquareSize > 0; i++) {
            Color nextColor = nextColor(i, insideColor, outsideColor);
            paintSquare(painter, square, nextColor);
            currentSquareSize = currentSquareSize - (int) (squareDecreasingSize);
            square.shrink(squareDecreasingSize);
        }
    }

    private void paintSquare(SPainter painter, SSquare square, Color nextColor) {
        painter.setColor(nextColor);
        painter.paint(square);
    }

    private Color nextColor(int i, Color insideColor, Color outsideColor) {
        if (i % 2 == 0) {
            return outsideColor;
        }
        return insideColor;
    }

    private int getRandom(int i) {
        return (int) Math.floor(Math.random() * i);
    }

    private int getNumber(String message) {
        String nss = JOptionPane.showInputDialog(null, message + "?");
        Scanner sc = new Scanner(nss);
        int num = sc.nextInt();
        sc.close();
        return num;
    }
}
