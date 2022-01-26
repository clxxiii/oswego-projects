/*
 * 2nd tester class for the Person class
 */
package people;

public class PersonDemo2 {

    public static void main(String[] args) {

        // Create an array of six person objects
        Person[] people = new Person[6];
        people[0] = new Person("Bob Dylan", 5,24,1941);
        people[1] = new Person("Noomi Rapace", 12,28,1974);
        people[2] = new Person("Pharrell Williams", 4,5,1973);
        people[3] = new Person("Frank Sinatra", 12,12,1915);
        people[4] = new Person("Diana Krall", 11,16,1964);
        people[5] = new Person("Eli Fereira", 6,29,2003);

        // Display the six person objects to the standard output stream
        for (Person person : people) {
            System.out.println(person + " " + person.initials() + " " + person.isBoomer());
        }
    }
}
