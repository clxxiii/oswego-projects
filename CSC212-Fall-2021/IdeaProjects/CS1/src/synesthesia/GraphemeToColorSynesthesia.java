/*
 * Program to simulate the phenomenon known as grapheme to color synesthesia.
 * This program is written as the interpreter that recognizes and responds to:
 * - exit | terminate the program
 * - remap | redefine the mapping from letters to the colors
 * - WORD OR PHRASE | simply show the word or phrase in synesthetic color
 */

package synesthesia;

import painter.SPainter;

import javax.swing.*;
import java.awt.*;

public class GraphemeToColorSynesthesia {

    // Private instance variables
    private static final int fontSize = 30;
    private static final String theLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static String[] letters;
    private static Color[] colors;

    private void paintingCode() {

        // Initialization
        SPainter miro = new SPainter(1200, 200);
        miro.setScreenLocation(30,30);
        miro.setFontSize(fontSize);
        initializeColorMap(theLetters);

        //Interpreter
        while (true) {
            String input = JOptionPane.showInputDialog(null,
                    "Please enter a word, or a few words");
            if (input == null) { input = "EXIT"; }
            input = input.toUpperCase();

            if (input.equals("EXIT")) {
                break;
            }
            else if (input.equals("REMAP")) {
                initializeColorMap(theLetters);
                showLetters(miro, theLetters);
            }
            else {
                showLetters(miro, input.toUpperCase());
            }
        }
        miro.end();
    }

    private static void showLetters(SPainter miro, String input) {
        eraseWhiteBoard(miro);

        miro.moveTo(new Point.Double(100, 100));

        for (int i = 0; i < input.length(); i++) {
            String letter = input.substring(i, i + 1);
            Color color = getLetterColor(letter);
            miro.setColor(color);
            miro.draw(letter);
            miro.mrt(fontSize);
        }
    }

    private static void initializeColorMap(String specialLetters) {
        letters = new String[specialLetters.length()];
        colors = new Color[specialLetters.length()];
        for (int i = 0; i < letters.length; i++) {
            letters[i] = specialLetters.substring(i, i + 1);
            colors[i] = randomColor();
        }
    }

    private static Color getLetterColor(String letter) {
        for (int i = 0; i < letters.length; i++) {
            if (letter.equalsIgnoreCase(letters[i])) {
                return colors[i];
            }
        }
        return Color.BLACK;
    }

    private static Color randomColor() {
        int rv = (int) (Math.random()*256);
        int gv = (int) (Math.random()*256);
        int bv = (int) (Math.random()*256);
        return new Color(rv, gv, bv);
    }

    private static void eraseWhiteBoard(SPainter miro) {
        miro.setColor(Color.WHITE);
        miro.wash();
        miro.paintFrame(Color.BLACK, 5);
    }


    // Infrastructure
    public GraphemeToColorSynesthesia() {
        paintingCode();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new GraphemeToColorSynesthesia();
            }
        });
    }
}
