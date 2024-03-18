import java.util.Arrays;
import java.util.Scanner;

/*
 * When you roll an X-sided die, how often do you roll the number of times you've rolled?
 */
public class RollNumberEqualsRollCount {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("How many sides does the die have? ");
        int dieSides = input.nextInt();
        System.out.print("How many times would you like to test? ");
        int iterations = input.nextInt();
        int[] successfulRolls = {};

        for (int i = 0; i < iterations; i++) {
            int successfulRoll = rolledNumberEqualsRollNumber(dieSides);
            System.out.println("It took " + successfulRoll +" rolls to roll the current roll count");
            successfulRolls = appendArray(successfulRolls, successfulRoll);
        }

        System.out.println("Array of results:");
        System.out.println(Arrays.deepToString(makeArrayTwoDimensional(successfulRolls)));
    }

    // Answer to the original question
    private static int rolledNumberEqualsRollNumber(int sides) {
        int rollCount = 0;
        int rolledNumber;
        while (true) {
            rolledNumber = rollDie(sides);
            rollCount++;
            System.out.println("Roll Count: " + rollCount);
            // To prevent the rolls going to infinity, I'm using the mod of the sides.
            // e.g. It's impossible to roll x now, so I'll start from 0
            if (rollCount % sides == rolledNumber) { break; }
            else {
                System.out.println(rolledNumber + " != " + rollCount % sides + ", rolling again");
            }

        }
        return rollCount;
    }


    private static int rollDie(int sides) {
        int roll = (int)(Math.random() * sides);
        System.out.println("Roll: " + roll);
        return roll;
        /*
         * For the purposes of simplicity, one of the sides has 0,
         * and the highest number on the die is one less than
         * the side count.
        */
    }
    // Method for Appending an int to an int[] Array
    private static int[] appendArray(int[] list, int num) {
        int[] newArray = Arrays.copyOf(list, list.length + 1);
        newArray[ newArray.length - 1] = num;
        return newArray;
    }

    // Turning an array into a 2D array for use in a google sheet.
    private static int[][] makeArrayTwoDimensional(int[] arr) {
        int[][] returnArray = new int[arr.length][];
        for(int i = 0; i < arr.length; i++) {
            int[] cellArray = {arr[i]};
            returnArray[i] = cellArray;
        }
        return returnArray;
    }
}

