/*
 * Takes a file, reverses the order of the words in the file, and generates a new file.
 */
package arraylistplay;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class ReverseCopy {
    public static void main(String[] args) throws FileNotFoundException, IOException {
        String inputFileName = "WordsINeverSaid.text";
        String outputFileName = "WordsINeverSaidReversed.text";
        createFullFileName(inputFileName);
        ArrayList<String> words = readWordsFromFileName(inputFileName);
        writeWordsToFile(words,outputFileName);
    }

    private static ArrayList<String> readWordsFromFileName(String inputFileName) throws FileNotFoundException {
        // Equate a scanner with the input file
        Scanner scanner = establishScanner(inputFileName);
        ArrayList<String> words = new ArrayList<>();
        while (scanner.hasNext()) {
            String word = scanner.next();
            words.add(word);
        }
        // Return the words
        return words;
    }

    private static void writeWordsToFile(ArrayList<String> words, String outputFileName) throws IOException {
        // Equate a printer with an output file
        PrintWriter printer = getPrintWriter(outputFileName);
        // Print the words to the file
        for ( int x = words.size() - 1; x >= 0; x--) {
            printer.println(words.get(x));
        }
        printer.close();
    }

    private static PrintWriter getPrintWriter(String outputFileName) throws FileNotFoundException {
        String fullFileName = createFullFileName(outputFileName);
        PrintWriter printer = new PrintWriter(fullFileName);
        return printer;
    }

    private static Scanner establishScanner(String inputFileName) throws FileNotFoundException {
        String fullFileName = createFullFileName(inputFileName);
        return new Scanner(new File(fullFileName));
    }
    // Create the full file name for a simple file name, assuming that it will be
    // found in the CS1Files/data subdirectory of the user's home directory.
    private static String createFullFileName(String fileName) {
        String separator = System.getProperty("file.separator");
        String home = System.getProperty("user.home");
        String path = home + separator + "CS1Files" + separator + "data" + separator;
        String fullFileName = path + fileName;
        return fullFileName;
    }
}
