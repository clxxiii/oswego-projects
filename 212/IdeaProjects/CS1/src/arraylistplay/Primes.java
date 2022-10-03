/*
 * Experimentation with arraylist notation and arraylistss in general
 */
package arraylistplay;

import java.util.ArrayList;

public class Primes {
    public static void main(String[] args) {
        ArrayList<Integer> primes = new ArrayList<>();

        primes.add(2);
        primes.add(3);
        primes.add(5);
        primes.add(7);

        System.out.println("size of primes list = " + primes.size());
        System.out.println("first prime = " + primes.get(0));
        System.out.println("last prime = " + primes.get(3));
        System.out.println("last prime = " + primes.get(primes.size() - 1));

        System.out.println("\nThe inital list ...");
        int i = 0;
        while (i < primes.size()) {
            System.out.println(primes.get(i));
            i = i + 1;
        }

        int temp = primes.get(0);
        primes.set(0, primes.get(primes.size() - 1));
        primes.set(primes.size() - 1, temp);

        System.out.println("\nThe final list ...");
        for (int x = 0; x < primes.size(); x = x + 1) {
            System.out.println(primes.get(x));
        }
    }
}
