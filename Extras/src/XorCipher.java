
/**
 * This program takes a String, asks for an integer for the key,
 * and uses a XOR bitwise operator to encode/decode the message.
 * 
 * Name: Eli Fereira
 */

import java.util.Scanner;

public class XorCipher {
    public static void main(String[] args) {
        // Ask for input
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a String: ");
        String input = sc.nextLine();
        System.out.print("Enter an integer between 1 and 255, inclusive: ");
        int key = sc.nextInt();
        sc.close();

        // Xor Cipher
        char[] string = new char[input.length()];
        for (int i = 0; i < input.length(); i++) {
            char character = input.charAt(i);
            int encodedInt = character ^ key;
            char encodedChar = (char) (encodedInt);
            string[i] = encodedChar;

            System.out.println(character +
                    " [" +
                    (int) (character) +
                    "] -> " +
                    encodedChar +
                    " [" +
                    encodedInt +
                    "]");
        }

        System.out.println("Result: " + new String(string));

    }
}

/*
 * Encoding Example:
 * 
 * Enter a String: Dr. Schlegel
 * Enter an integer between 1 and 255, inclusive: 3
 * D [68] -> G [71]
 * r [114] -> q [113]
 * . [46] -> - [45]
 *   [32] -> # [35]
 * S [83] -> P [80]
 * c [99] -> ` [96]
 * h [104] -> k [107]
 * l [108] -> o [111]
 * e [101] -> f [102]
 * g [103] -> d [100]
 * e [101] -> f [102]
 * l [108] -> o [111]
 * Result: Gq-#P`kofdfo
 */

/*
 * Decoding Example:
 * 
 * Enter a String: Gq-#P`kofdfo
 * Enter an integer between 1 and 255, inclusive: 3
 * G [71] -> D [68]
 * q [113] -> r [114]
 * - [45] -> . [46]
 * # [35] -> [32]
 * P [80] -> S [83]
 * ` [96] -> c [99]
 * k [107] -> h [104]
 * o [111] -> l [108]
 * f [102] -> e [101]
 * d [100] -> g [103]
 * f [102] -> e [101]
 * o [111] -> l [108]
 * Result: Dr. Schlegel
 */