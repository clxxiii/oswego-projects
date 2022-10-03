/*
 * Model a die in terms of two properties:
 *
 * - order: the number of faces
 * - top: the value of the top face
 */
package chance;

public class Die {
    // Private Instance Variables
    private final int order;
    private int top;

    // Constructors
    public Die() {
        order = 6;
        top = (int) ( ( Math.random() * order) + 1);
    }

    public Die(int nrOfFaces) {
        order = nrOfFaces;
        top = (int) ( ( Math.random() * order) + 1);
    }

    // Methods
    public int top() {
        return top;
    }

    public void roll() {
        top = (int) ( ( Math.random() * order) + 1);
    }
}
