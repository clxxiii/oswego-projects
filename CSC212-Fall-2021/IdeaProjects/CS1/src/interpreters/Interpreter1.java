/*
 * This interpreter is intended to pain different colored dots in a window..
 *
 * The commands that the interpreter can recognize and respond to are:
 * - BLUE: paint a blue dot
 * - RED: paint a red dot
 * - HELP: show a list of commands in a dialogue message box
 * - EXIT: terminate the program
 */
package interpreters;

import painter.SPainter;
import shapes.SCircle;

import javax.naming.InsufficientResourcesException;
import javax.swing.*;
import java.awt.*;

public class Interpreter1 {
    private void interpreter() {
        // Create objects to think with
        SPainter miro = new SPainter("Dot Thing",400,400);
        miro.setScreenLocation(0,0);
        SCircle dot = new SCircle(180);

        // Repeatedly take a command from an input dialog box and interpret it
        while (true) {
            String command = JOptionPane.showInputDialog(null, "Command?");
            if ( command == null ) { command = "exit"; } // User clicked on cancel
            if ( command.equalsIgnoreCase("blue") ) {
                miro.setColor(Color.BLUE);
                miro.paint(dot);
            } else if ( command.equalsIgnoreCase("red") ) {
                miro.setColor(Color.RED);
                miro.paint(dot);
            } else if ( command.equalsIgnoreCase("help") ) {
                JOptionPane.showMessageDialog(null, "Valid commands are: "
                    + "RED | BLUE | HELP | EXIT");
            } else if ( command.equalsIgnoreCase("exit") ) {
                miro.end();
                System.out.println("Thank you for viewing the dots!");
                break;
            } else {
                JOptionPane.showMessageDialog(null, "Unrecognizable command: " +
                        command.toUpperCase());
            }
        }
    }
    // Simple Painting Infrastructure
    public Interpreter1() { interpreter(); }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Interpreter1();
            }
        });
    }
}
