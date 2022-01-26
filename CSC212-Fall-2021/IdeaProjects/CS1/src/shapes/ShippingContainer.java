/*
 * A class to calculate the maximum length of a rod
 * able to fit into a three-dimensional container
 */
package shapes;

import java.util.Scanner;

public class ShippingContainer {
    public static void main(String[] args) {
        // Get all the dimensions of the container
        int[] containerDimensions = getContainerDimensions();
        int containerHeight = containerDimensions[0];
        int containerWidth = containerDimensions[1];
        int containerLength = containerDimensions[2];

        // Calculate and construct the curtain
        SRectangle containerEyeView = new SRectangle(containerLength, containerWidth);
        double curtainLength = containerEyeView.diagonal();
        SRectangle curtain = new SRectangle(containerHeight, curtainLength);

        //Calculate the length of the rod and print it
        double rodLength = curtain.diagonal();
        System.out.println("\nMaximum length of a rod inside the container: " + rodLength);
    }

    /*
     * Fetch container dimensions from a scanner
     */
    private static int[] getContainerDimensions() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please enter three integers for the dimsions of the container (HxWxL): ");
        int[] containerDimensions = new int[3];
        containerDimensions[0] = scanner.nextInt();
        containerDimensions[1] = scanner.nextInt();
        containerDimensions[2] = scanner.nextInt();
        scanner.close();
        return containerDimensions;
    }
}
