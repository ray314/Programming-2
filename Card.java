/**
 * 
 */
package Games;

/**
 * Card class for creating a card object.
 * @author Justin Yeung
 * 
 */
public class Card implements Comparable<Card>
{
    private int suit; // suit
    private int value; // value
    // Use char and string arrays to store the suit and value in string/char form.
    private char[] suitChars = {' ', 'S', 'C', 'D', 'H'}; // Use dummy char at the start so we can start at 1
    private String[] valueChars = new String[] {" ", "A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};

    /**
     * Initializes the Card class into an object.
     * @param value - The value of the card in int
     * @param suit - The suit of the card in int
     */
    public Card(int value, int suit)
    {
        this.suit = suit;
        this.value = value;
    }

    /**
     * Gets the card value as an int
     * @return int 
     */
    public int getValue()
    {
        return value;
    }

    /**
     * Gets the suit of the card as an int
     * @return int 
     */
    public int getSuit()
    {
        return suit;
    }

    /**
     * Displays the card rank as a string
     */
    public String toString()
    {
        return valueChars[value] + Character.toString(suitChars[suit]) ;
    }

    /**
     * Compare from first card to other card, positive: main card wins, 
     * negative: other card wins
     * zero: draw
     */
    public int compareTo(Card other)
    {
        int cardSuit1 = suit;
        int cardValue1 = value;
        int cardSuit2 = other.suit;
        int cardValue2 = other.value;
        return ((cardSuit1 + cardValue1) - (cardSuit2 + cardValue2));
    }
}
