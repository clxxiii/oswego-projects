package classnotes.cards;

public class Card {

    // Private Instance Variables
    private String rank;
    private String suit;

    // Constructor
    public Card(String rank, String suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public String toString() {
        return "(" + rank + " of " + suit + ")";
    }

    public String getSuit() {
        return suit;
    }

    public String getRank() {
        return rank;
    }
    
}
