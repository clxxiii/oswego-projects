/*
 * Using expressions to demonstrate how parentheses work when calculating equations.
 */
package expressions;

public class ExpressionsThing {
    public static void main(String[] args) {
        double one = 3.13 * 5 + 5;
        System.out.println("one = " + one);
        double two = 3.13 * ( 5 + 5 );
        System.out.println("two = " + two);
        double three = (3.13 * ( 5 + 5 ) );
        System.out.println("three = " + three);
        int four =  ( 5 * 6 );
        System.out.println("four = " + four);
        double five = ( 55.0 / 2.0 );
        System.out.println("five = " + five);
        double six = ( 65.0 / 3.0 );
        System.out.println("six = " + six);
        double seven = ( five + six );
        System.out.println("seven = " + seven);
    }
}
