/*
 * Experimentation with array notation and arrays in general
 */
package arrayplay;

public class Primes {
    public static void main(String[] args) {
        int[] primes = new int[4];

        primes[0] = 2;
        primes[1] = 3;
        primes[2] = 5;
        primes[3] = 7;

        System.out.println("length of primes array = " + primes.length);
        System.out.println("first prime = " + primes[0]);
        System.out.println("last prime = " + primes[3]);
        System.out.println("last prime = " + primes[primes.length - 1]);

        System.out.println("\nThe inital array ...");
        int i = 0;
        while (i < primes.length) {
            System.out.println(primes[i]);
            i = i + 1;
        }

        int temp = primes[0];
        primes[0] = primes[primes.length - 1];
        primes[primes.length - 1] = temp;

        System.out.println("\nThe final array ...");
        for (int x = 0; x < primes.length; x = x + 1) {
            System.out.println(primes[x]);
        }
    }
}
