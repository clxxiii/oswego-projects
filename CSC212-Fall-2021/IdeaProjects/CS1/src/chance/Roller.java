/*
 * Program to make use of the die class
 */
package chance;

public class Roller {
    public static void main(String[] args) {
        // Create a standard die and roll it 5 times
        createAndRollStandardDieFiveTimes();
        // Create a twenty-sided die and roll it 5 times
        createAndRollTwentySidedDieFiveTimes();
        // Create a standard die and roll it 20 times
        createAndRollStandardDie(20);
        // Create a standard die and roll it 30 times
        createAndRollStandardDie(30);
        // Create a nine-sided die and roll it 20 times
        createAndRollNineSidedDie(20);
        // Create a nine-sided die and roll it 30 times
        createAndRollNineSidedDie(30);
        // Ten times, create a standard die and roll until you get a 1
        System.out.println("\nTen times, roll a standard die for a 1.");
        for (int i = 1; i <= 10; i++) {
            createAndRollStandardDieFor1();
            System.out.println();
        }
        // Ten times, create a twelve-sided die and roll until you get a 1
        System.out.println("\nTen times, roll a twelve-sided die for a 1.");
        for (int i = 1; i <= 10; i++) {
            createAndRollTwelveSidedDieFor1();
            System.out.println();
        }
    }

    private static void createAndRollTwelveSidedDieFor1() {
        Die die = new Die(12);
        System.out.print(die.top() + " ");
        while (die.top() != 1) {
            die.roll();
            System.out.print(die.top() + " ");
        }
    }

    private static void createAndRollStandardDieFor1() {
        Die die = new Die();
        System.out.print(die.top() + " ");
        while (die.top() != 1) {
            die.roll();
            System.out.print(die.top() + " ");
        }
    }

    private static void createAndRollNineSidedDie(int rollCount) {
        System.out.println("\nRoll a standard die "+ rollCount +" times ...");
        Die die = new Die(9);
        for (int i = 0; i < rollCount; i++) {
            die.roll(); System.out.print(die.top() + " ");
        }
        System.out.println("");
    }

    private static void createAndRollStandardDie(int rollCount) {
        System.out.println("\nRoll a standard die "+ rollCount +" times ...");
        Die die = new Die();
        for (int i = 0; i < rollCount; i++) {
            die.roll(); System.out.print(die.top() + " ");
        }
        System.out.println("");
    }

    private static void createAndRollTwentySidedDieFiveTimes() {
        System.out.println("\nRoll a twenty-sided die 5 times ...");
        Die die = new Die(20);
        die.roll(); System.out.print(die.top() + " ");
        die.roll(); System.out.print(die.top() + " ");
        die.roll(); System.out.print(die.top() + " ");
        die.roll(); System.out.print(die.top() + " ");
        die.roll(); System.out.print(die.top() + " \n");
    }

    private static void createAndRollStandardDieFiveTimes() {
        System.out.println("\nRoll a standard die 5 times ...");
        Die die = new Die();
        die.roll(); System.out.print(die.top() + " ");
        die.roll(); System.out.print(die.top() + " ");
        die.roll(); System.out.print(die.top() + " ");
        die.roll(); System.out.print(die.top() + " ");
        die.roll(); System.out.print(die.top() + " \n");
    }
}
