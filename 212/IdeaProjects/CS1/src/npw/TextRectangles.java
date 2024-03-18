package npw;

import java.util.Scanner;

public class TextRectangles {
    public static void main(String[] args) {
        // Get variables from input stream
        int[] dimensions = getRectangleDimensions();
        int nrOfRows = dimensions[0];
        int nrOfColumns = dimensions[1];

        drawRectangle(nrOfRows, nrOfColumns);
        }
        
        private static void drawRectangle(int nrOfRows, int nrOfColumns) {
            int i = 1;
            while (i <= nrOfRows) {
                drawOneRow(nrOfColumns);
                i = i + 1;
            }
        }

    private static void drawOneRow(int nrOfColumns) {
        int i = 1;
        while (i <= nrOfColumns) {
            System.out.print('*');
            i++;
        }
        System.out.println("");
    }

    private static int[] getRectangleDimensions() {
        int[] dimensions = new int[2];
        Scanner sc = new Scanner(System.in);
        System.out.print("Number of rows? ");
        dimensions[0] = sc.nextInt();
        System.out.print("Number of columns? ");
        dimensions[1] = sc.nextInt();
        sc.close();
        return dimensions;
    }
}
