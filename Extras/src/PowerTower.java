import java.util.Scanner;

public class PowerTower {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Print first number in the power tower");
        int num1 = sc.nextInt();
        System.out.println("Print second number in the power tower");
        int num2 = sc.nextInt();
        System.out.print(num1 + " ^^ " + num2 + " = " + powerTower(num1, num2));
        sc.close();
    }

    public static int powerTower(int num1, int num2) {
        if (num2 == 1) {
            return num1;
        }

        return powerTower(num1, (int) Math.pow(num1, num2));
    }
}
