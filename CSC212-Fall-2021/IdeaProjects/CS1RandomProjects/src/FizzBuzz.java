/*
 * Multiple of 3 = Fizz
 * Multiple of 5 = Buzz
 * Both = FizzBuzz
 * Neither = number
 */

public class FizzBuzz {
    public static void main(String[] args) {

        for (int i = 1; i <=100; i++) {
            String text = "";

            if (i % 3 == 0) { text += "Fizz"; }
            if (i % 5 == 0) { text += "Buzz"; }

            if (text == "") {text = "" + i; }
            System.out.println(text);

        }
    }
}
