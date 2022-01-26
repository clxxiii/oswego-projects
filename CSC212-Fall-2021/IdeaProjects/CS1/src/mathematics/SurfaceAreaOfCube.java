/*
 * Program that features two functions to compute the surface area of a cube.
 * - The edge length will be read from the standard input stream
 * - The surface area will be printed to the standard output stream
 * - A face of the cube will be modeled as a simple square.
 */
package mathematics;

import shapes.SSquare;

import java.util.Scanner;

public class SurfaceAreaOfCube {
     public static void main(String[] args) {
         double edgeLength = edgeLength();
         double surfaceArea = surfaceArea(edgeLength);
         System.out.println("surface area = " + surfaceArea);
     }

    private static double edgeLength() {
        System.out.print("Please enter the edge length of the cube: ");
        Scanner scanner = new Scanner(System.in);
        double edgelength = scanner.nextDouble();
        scanner.close();
        return edgelength;
    }

    private static double surfaceArea(double edgeLength) {
        SSquare face = new SSquare(edgeLength);
        int nrOfFaces = 6;
        double surfaceArea = face.area() * nrOfFaces;
        return surfaceArea;
    }
}
