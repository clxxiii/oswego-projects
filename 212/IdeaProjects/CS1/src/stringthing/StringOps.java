/*
 * Program to illustrate some basic character string processing functionality
 */
package stringthing;

public class StringOps {

    public static void main(String[] args) {
        // ESTABLISH SOME STRINGS
        String date = "Wednesday, October 18, 1955";
        String time = "8 AM";
        String lab = "String Thing";

        // COMPUTE THE LENGTHS OF THE STRINGS
        int dateLength = date.length();
        int timeLength = time.length();
        int labLength = lab.length();
        System.out.println("\ndateLength = " + dateLength);
        System.out.println("timeLength = " + timeLength);
        System.out.println("labLength = " + labLength);

        // COMPUTE SOME POSITIONS
        int p1 = date.indexOf(",");
        int p2 = time.indexOf(" ");
        int p3 = lab.indexOf("ing");
        System.out.println("\np1 = " + p1);
        System.out.println("p2 = " + p2);
        System.out.println("p3 = " + p3);

        // COMPUTE SOME 2 ARGUMENT SUBSTRING VALUES
        System.out.println("\ndate.substring(0,9) = " + date.substring(0,9));
        System.out.println("time.substring(2,4) = " + time.substring(2,4));
        System.out.println("lab.substring(7,8) = " + lab.substring(7,8));

        // COMPUTE SOME 1 ARGUMENT SUBSTRING VALUES
        System.out.println("\ndate.substring(11) = " + date.substring(11));
        System.out.println("time.substring(2) = " + time.substring(2));
        System.out.println("lab.substring(7) = " + lab.substring(7));

        // CREATE A STRING
        String line = date + " | " + time + " | " + lab;
        System.out.println("\nline =  " + line);
    }
}
