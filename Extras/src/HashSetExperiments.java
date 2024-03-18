
/** 
 * This program uses basic HashSet functionality and uses them
 * to demo the various properties of Sets.
 * 
 * Name: Eli Fereira
 */

import java.util.HashSet;

public class HashSetExperiments {
    public static void main(String[] args) {
        // Build all sets
        HashSet<String> setT = new HashSet<String>();
        HashSet<String> setW = new HashSet<String>();
        HashSet<String> setO = new HashSet<String>();

        setT.add("The Wrath of Kahn");
        setT.add("The Search for Spock");
        setT.add("The Voyage Home");
        setT.add("The Undiscovered Country");

        setW.add("A New Hope");
        setW.add("The Empire Strikes Back");
        setW.add("Return of the Jedi");

        setO.add("The Empire Strikes Back");
        setO.add("Return of the Jedi");

        System.out.println("\nSolution to problem (c); " + solutionC(setT, setW, setO));
        System.out.println("Solution to problem (d); " + solutionD(setT, setW));
        System.out.println("Solution to problem (e); " + solutionE(setT, setW, setO));
        System.out.println("Solution to problem (f); " + solutionF(setT));
        System.out.println("Solution to problem (g); " + solutionG(setT));
    }

    private static String solutionC(HashSet<String> setT, HashSet<String> setW, HashSet<String> setO) {
        String returnString = "";
        returnString += "Set T Cardinality: " + setT.size() + "; ";
        returnString += "Set W Cardinality: " + setW.size() + "; ";
        returnString += "Set O Cardinality: " + setO.size();

        return returnString;
    }

    private static HashSet<String> solutionD(HashSet<String> setT, HashSet<String> setW) {
        setT.addAll(setW);
        return setT;
    }

    private static HashSet<String> solutionE(HashSet<String> setT, HashSet<String> setW, HashSet<String> setO) {
        setT.addAll(setW);
        setT.removeAll(setO);
        return setT;
    }

    private static boolean solutionF(HashSet<String> setT) {
        HashSet<String> compareSet = new HashSet<String>();
        compareSet.add("The Wrath of Kahn");
        compareSet.add("The Search for Spock");
        compareSet.add("The Voyage Home");
        return setT.containsAll(compareSet);
    }

    private static boolean solutionG(HashSet<String> setT) {
        HashSet<String> compareSet = new HashSet<String>();
        compareSet.add("The Wrath of Kahn");
        compareSet.add("The Search for Spock");
        compareSet.add("The Voyage Home");
        return (setT.containsAll(compareSet) & setT != compareSet);
    }

    /*
     * Solution to problem (c); Set T Cardinality: 4; Set W Cardinality: 3; Set O
     * Cardinality: 2
     * 
     * Solution to problem (d); [A New Hope, The Wrath of Kahn, The Empire Strikes
     * Back, Return of the Jedi, The Voyage Home, The Search for Spock, The
     * Undiscovered Country]
     * 
     * Solution to problem (e); [A New Hope, The Wrath of Kahn, The Voyage Home, The
     * Search for Spock, The Undiscovered Country]
     * 
     * Solution to problem (f); true
     * 
     * Solution to problem (g); true
     */
}
