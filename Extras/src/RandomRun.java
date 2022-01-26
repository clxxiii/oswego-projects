/*
 * Rolling a number in the same amount of rolls as the rolled number
 */
import java.util.Arrays;

public class RandomRun {
    public static void main(String[] args) {
        int dieSize = 6;
        int goal = 1;

        rolledNumberEqualsRollNumber(goal, dieSize);
    }

    // How many rounds of rolling does it take
    private static int rolledNumberEqualsRollNumber(int goal, int dieSize) {
        int rolls = 0;
        int rollRounds = 0;
        while (rolls != goal) {
            rolls = rollDieUntilX(goal, dieSize);
            rollRounds++;
        }
        System.out.println("It Took " + rollRounds + " rounds to roll " + goal + " on a " + dieSize + "-sided die in "
                + goal + " rolls.");
        return rollRounds;
    }


    // "How many rolls does it take to get x on a y sided die"
    private static int rollDieUntilX(int goal, int dieSize) {
        int i = 0;
        int repeats = 0;
        while (i != goal) {
            i = (int)(Math.random() * dieSize);
            repeats++;
        }
//        System.out.println(n + "!");
//        System.out.println("Program terminated after " + repeats + " Iterations");
        return repeats;
    }


//    // Method for Appending an int to an int[] Array
//    private static int[] appendArray(int[] list, int num) {
//        int[] newArray = Arrays.copyOf(list, list.length + 1);
//        newArray[ newArray.length - 1] = num;
//        return newArray;
//    }
}
