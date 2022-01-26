/*
 * Experimentation with larger array notation and arrays in general
 */
package arraylistplay;

import java.util.ArrayList;

public class Streets {
    public static void main(String[] args) {
        ArrayList<String> streets = new ArrayList<>();
        streets.add("Iberville");
        streets.add("Decatur");
        streets.add("Toulouse");
        streets.add("Bourbon");
        streets.add("Dauphine");
        streets.add("Royal");
        streets.add("St Ann");
        streets.add("St Peter");
        streets.add("Conti");
        streets.add("Exchange");
        streets.add("Bienville");
        streets.add("Dumaine");

        System.out.println("length of streets array = " + streets.size());
        System.out.println("first street = " + streets.get(0));
        System.out.println("last street = " + streets.get(3));
        System.out.println("last street = " + streets.get(streets.size() - 1));

        System.out.println("\nThe inital array ...");
        int i = 0;
        while (i < streets.size()) {
            System.out.println(streets.get(i));
            i = i + 1;
        }

        String temp = streets.get(0);
        streets.set(0, streets.get(streets.size() - 1));
        streets.set(streets.size() - 1, temp);

        System.out.println("\nThe final array ...");
        for (int x = 0; x < streets.size(); x = x + 1) {
            System.out.println(streets.get(x));
        }
    }
}
