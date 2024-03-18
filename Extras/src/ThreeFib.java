import java.util.Scanner;

public class ThreeFib {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean repeat = true;
        while (repeat) {
            System.out.print("Enter the number you'd like to 3fib: ");
            int num = sc.nextInt();
            int result = threeFib(num);
            if (result == -1) {
                repeat = !repeat;
            } else {
                System.out.println("3fib(" + num + ")" + " = " + result);
            }
        }
        sc.close();
    }

    public static int threeFib(int num) {
        if (num < 1) {
            return -1;
        }
        if (num == 1 || num == 2 || num == 3) {
            return 1;
        }
        return threeFib(num - 1) + threeFib(num - 2) + threeFib(num - 3);
    }
}
