/*
 * A program to computate the white space on a six sided die
 */
package shapes;

public class WhiteSpace {
    public static void main(String[] args) {
        // Establish Givens
        double dieEdgeLength = 0.63;

        // Calculate the surface area of the die
        SSquare dieFace = new SSquare(dieEdgeLength);
        double dieSurfaceArea = ( dieFace.area() * 6 );

        // Calculate the surface area of all the pips
        double pipDiameter = dieEdgeLength / 6;
        SCircle pip = new SCircle(pipDiameter / 2);
        int numberOfPips = 1 + 2 + 3 + 4 + 5 + 6;
        double pipSurfaceArea = pip.area() * numberOfPips;

        // Calculate and print the white space
        double whiteSpace = dieSurfaceArea - pipSurfaceArea;
        System.out.println("Area of the white space on the die: " + whiteSpace);
    }
}
