
/**
 * Represents an object capable of taking a String and compressing it into a hash
 */

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class HashFunction {
    public static void main(String[] args) {
        // System.out.print("Enter a String to be hashed: ");
        // Scanner sc = new Scanner(System.in);
        // System.out.println("Hash result: " + hash(sc.next()));
        // sc.close();
        System.out.print("Enter an int to brute force: ");
        Scanner sc = new Scanner(System.in);
        System.out.println("Brute Force result: " + findCollision(sc.nextInt()));
        sc.close();
    }

    private static int hash(String input) {
        // Pad input length to at least 3
        while (input.length() < 3) {
            input += "0";
        }

        char[] inputArray = input.toCharArray();

        for (int i = 1; i < inputArray.length - 1; i++) {
            inputArray[i - 1] = (char) (inputArray[i - 1] ^ inputArray[i]);
        }
        inputArray[inputArray.length - 1] = (char) (inputArray[inputArray.length - 1] ^ inputArray[0]);

        int result = 0;

        for (int i = 0; i < inputArray.length; i++) {
            result += (int) inputArray[i];
        }

        result %= 1000;

        while (result < 100) {
            result += 10;
        }

        return result;
    }

    public static String findCollision(int hash) {
        Queue<String> todo = new LinkedList<>();
        String test = "";

        while (hash(test) != hash) {
            for (int i = 'a'; i <= 'z'; i++) {
                todo.add(test + (char) i);
            }
            test = todo.remove();
        }

        return test;
    }
}