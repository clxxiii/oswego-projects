import java.util.Scanner;

public class Eggs {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("How many eggs? ");
        int eggs = scanner.nextInt();
        getEggCounts(eggs);
    }
    public static void getEggCounts(int eggs) {
        // Divide the number of eggs by 144, and throw away the decimal to get the amount of gross.
        int grossCount = (int) (eggs/144);
        // Subtract the amount of gross times 144 from the number of eggs
        eggs = eggs % 144;
        // Divide the remaining eggs by 12, and throw away the decimal to get the amount of dozens.
        int dozenCount = (int) (eggs/12);
        // Subtract the amount of dozens times 12 from the number of remaining eggs
        eggs = eggs % 12;
        // Print the gross, the dozens, and the eggs left over.
        System.out.println("Your number of eggs is " +
                grossCount + " gross, " +
                dozenCount + " dozen, and " +
                eggs);
    }
}
