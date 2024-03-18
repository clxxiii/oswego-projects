/*
 * Using expressions to demonstrate how parentheses work when calculating equations.
 */
package expressions;

public class ExpressionsThing {
    public static void main(String[] args) {
        // Task 3
        double one = 3.13 * 5 + 5;
        System.out.println("one = " + one);

        double two = 3.13 * ( 5 + 5 );
        System.out.println("two = " + two);

        double three = (3.13 * ( 5 + 5 ) );
        System.out.println("three = " + three);
        // Task 4
        int four =  ( 5 * 6 );
        System.out.println("four = " + four);

        double five = ( 55.0 / 2.0 );
        System.out.println("five = " + five);

        double six = ( 65.0 / 3.0 );
        System.out.println("six = " + six);

        double seven = ( five + six );
        System.out.println("seven = " + seven);
        // Task 5
        double eight = (3.14 * (11.3 * 11.3) );
        System.out.println("eight = " + eight);

        double nine = (27.7 * 27.7);
        System.out.println("nine = " + nine);

        double ten = ( (eight + nine) / 2 );
        System.out.println("ten = " + ten);

        double eleven = ( 243.5 * 0.17 );
        System.out.println("eleven = " + eleven);
        // Task 6
        int twelve = ( 3 / 3 );
        System.out.println("twelve = " + twelve);

        int thirteen = ( ( 7 - 2 ) - 4 );
        System.out.println("thirteen = " + thirteen);

        int fourteen = ( ( 7 + 9 ) / ( 1 + 3 ) );
        System.out.println("fourteen = " + fourteen);

        int fifteen = ( 6 - ( (  2 + 2 ) + 4 ) / 8 );
        System.out.println("fifteen = " + fifteen);
    }
}
