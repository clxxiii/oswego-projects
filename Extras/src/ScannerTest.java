import java.util.Scanner;

public class ScannerTest {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("gimme an int");
        try {
            int a = input.nextInt();
        } catch (Throwable error) {
            System.out.println("i said an int lmao " + error);
        }
    }
}
