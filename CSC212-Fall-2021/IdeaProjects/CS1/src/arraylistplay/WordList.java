/*
 * Program featuring an array to store and interactively manipulate a list of numbers
 */
package arraylistplay;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class WordList {
    // Private instance variables
    private static ArrayList<String> words = new ArrayList<>();
    private static int numberOfWords = 0;
    private static Scanner commandReader = new Scanner(System.in);

    public static void main(String[] args) {
        try {
            // establish the array
            readWords();
            //enter the interpreter
            interpreter();
        } catch (FileNotFoundException ex) {
            System.out.println("The file was not found, please try again.");
            System.exit(-1);
        }
    }

    /*
     * Assuming that the dat afile will be foudn in the public_html/data
     * subdirectory of the user's home directory. 
     */
    private static Scanner establishScanner(String fn) throws FileNotFoundException{ 
        String separator = File.separator;
        String homeDirectory = System.getProperty("user.home");
        String path = homeDirectory + separator + "public_html" + separator + "data" + separator;
        String fullFileName = path + fn;
        return new Scanner(new File(fullFileName));
    }
    
    private static void readWords() throws FileNotFoundException {
        Scanner scanner = establishScanner("WordSet.txt");
        while (scanner.hasNext()) {
            words.add(scanner.next());
            numberOfWords++;
        }
    }

    private static void displayNumbers() {
        for(int i = 0; i < numberOfWords; i++) {
            System.out.println(words.get(i));
        }
    }

    private static void interpreter() {
        System.out.print(">>> ");
        String command = commandReader.next();
        if (command.equalsIgnoreCase("DISPLAY")) {
            interpretDisplayCommand();
        }
        else if (command.equalsIgnoreCase("PRINT")) {
            interpretPrintCommand();
        }
        else if (command.equalsIgnoreCase("SWAP")) {
            interpretSwapCommand();
        }
        else if (command.equalsIgnoreCase("ADD")) {
            interpretAddCommand();
        }
        else if (command.equalsIgnoreCase("HELP")) {
            interpretHelpCommand();
        }
        else if (command.equalsIgnoreCase("EXIT")) {
            System.exit(0);
        }
        else {
            System.out.println("### Unrecognizable command: " + command);
        }
        interpreter();
    }
    
    
    private static void interpretDisplayCommand() {
        displayNumbers();
    }
    
    private static void interpretPrintCommand() {
        String operand = commandReader.next();
        if (operand.equalsIgnoreCase("FIRST")) {
            System.out.println(words.get(0));
        }
        else if (operand.equalsIgnoreCase("LAST")) {
            System.out.println(words.get(words.size() - 1));
        }
        else {
            try {
                int index = Integer.valueOf(operand);
                System.out.println(words.get(index - 1));
            }
            catch (IndexOutOfBoundsException e) {
                System.out.println("### Index out of bounds of words array!");
            }
        }
    }
    
    private static void interpretHelpCommand() {
        System.out.println("HELP - displays this menu of commands");
        System.out.println("DISPLAY - display the list of numbers");
        System.out.println("PRINT - print a number (FIRST;LAST;nth)");
        System.out.println("SWAP - exchange two elements (nth;mth)");
        System.out.println("ADD - add a number to the list (FIRST;LAST)");
        System.out.println("EXIT - terminate execution of the program");
    }    

    private static void interpretSwapCommand() {
        int position1 = commandReader.nextInt();
        int position2 = commandReader.nextInt();

        String temp = words.get(position1 - 1);
        words.set(position1 - 1, words.get(position2 - 1));
        words.set(position2 - 1, temp);
    }
    
    private static void interpretAddCommand() {
        String position = commandReader.next();
        if (position.equalsIgnoreCase("LAST")) {
            words.add(commandReader.next());
            numberOfWords++;
        }
        else if (position.equalsIgnoreCase("FIRST")) {
            words.add(0, commandReader.next());
            numberOfWords++;
        }
        else {
            System.out.println("## invalid operand for ADD command");
        }
    }
}
