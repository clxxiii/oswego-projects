/*
 * A program for calculating the yellow space on the diagram in Challenge 3
 */
package shapes;

public class YellowSpace {
    public static void main(String[] args) {
        int tableclothSideLength = 800;
        int diamondDistanceFromTablecloth = 80;
        int squareDistanceFromDiamond = 40;

        // Create the yellow diamond
        double diamondCircleDiameter = tableclothSideLength - (diamondDistanceFromTablecloth * 2);
        SCircle diamondCircle = new SCircle(diamondCircleDiameter / 2);
        SSquare diamond = diamondCircle.inscribingSquare();

        // Create the square
        double squareCircleDiameter = diamond.side() - (squareDistanceFromDiamond * 2);
        SCircle squareCircle = new SCircle(squareCircleDiameter / 2);
        SSquare greySquare = squareCircle.inscribingSquare();

        // Calculate and print the yellow space
        double yellowSpace = diamond.area() - greySquare.area();
        System.out.println("Yellow Space: " + yellowSpace);
    }
}
