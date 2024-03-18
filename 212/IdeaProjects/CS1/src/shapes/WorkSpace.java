/*
 * A class for calculating the free space available
 */
package shapes;

public class WorkSpace {

    public static void main(String[] args) {
        double deskHeight = 66.0;
        double deskWidth = 152.0;
        double notebookHeight = 21.0;
        double notebookWidth = 29.7;
        double labManualHeight = 25.4;
        double labManualWidth = 30.48;
        double canRadius = 3.175;
        double plateDiameter = 20;

        SRectangle desk = new SRectangle(deskHeight,deskWidth);
        SRectangle notebook = new SRectangle(notebookHeight, notebookWidth);
        SRectangle labManual = new SRectangle(labManualHeight, labManualWidth);
        SCircle can = new SCircle(canRadius);
        SSquare coaster = can.circumscribingSquare();
        SCircle plates = new SCircle(plateDiameter / 2);

        double notebookSpace = notebook.area() * 2;
        double labManualSpace = labManual.area();
        double coasterSpace = coaster.area() * 3;
        double platesSpace = plates.area() * 6;

        double coveredDeskSpace = notebookSpace + labManualSpace + coasterSpace + platesSpace;
        double availableDeskSpace = desk.area() - coveredDeskSpace;
        System.out.println("Available Desk Space = " + availableDeskSpace);
    }
}
