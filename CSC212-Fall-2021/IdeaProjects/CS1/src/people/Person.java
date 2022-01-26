/*
 * Models a person using five values:
 *
 * Name:
 *   First Name: String
 *   Last Name: String
 * Birthday:
 *   Day: int
 *   Month: int
 *   Year: int
 */

package people;

public class Person implements PersonSpecification {

    // Instance Variables
    private String firstName;
    private String lastName;
    private int day;
    private int month;
    private int year;

    public Person(String fullName, int month, int day, int year) {
        firstName = fullName.substring(0, fullName.indexOf(" "));
        lastName = fullName.substring(fullName.indexOf(" ") + 1);
        this.month = month;
        this.day = day;
        this.year = year;
    }

    public String toString() {
        return firstName + " " + lastName + ", born " +
                month + "/" + day + "/" + year;
    }

    public String firstName() {
        return firstName;
    }

    public String lastName() {
        return lastName;
    }

    public int month() {
        return month;
    }

    public int day() {
        return day;
    }

    public int year() {
        return year;
    }

    public String initials() {
        String firstInitial = firstName.substring(0, 1);
        String lastInitial = lastName.substring(0, 1);
        return firstInitial.toUpperCase() + lastInitial.toUpperCase();
    }

    public boolean isBoomer() {
        return (year >= 1946 & year <= 1964);
    }
}
