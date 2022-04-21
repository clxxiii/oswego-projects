import java.util.Scanner;

public class PowerTower {
    public static void main(String[] args) {
        boolean repeat = true;
        Scanner sc = new Scanner(System.in);
        while (repeat) {
            try {
                System.out.print("First number in the power tower: ");
                long num1 = sc.nextLong();
                System.out.print("Second number in the power tower: ");
                long num2 = sc.nextLong();

                long result = powerTower(num1, num2);
                System.out.println(num1 + "^^" + num2 + " = " + result + "\n");
            } catch (Exception e) {
                repeat = !repeat;
            }
        }
        sc.close();
    }

    public static long powerTower(long num1, long num2) {
        if (num2 == 1) {
            return num1;
        }
        long result = Math.round(Math.pow(num1, powerTower(num1, (num2 - 1))));
        return result;
    }
}