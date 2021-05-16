/**
 * 
 */
package Games;

import java.util.ArrayList;
import java.util.Collections;
//import java.util.Random;

/**
 * Class used to create a deck of cards as an object.
 * @author Justin Yeung
 *
 */
public class Deck 
{
	// Using ArrayList instead of a fixed array so I can manipulate list.
    private int deckSize;
    //private Card[] deck;
    private ArrayList<Card> deck = new ArrayList<>();
    //public final int MAX_SIZE = 51;
    //private Random rand = new Random();
    //private int cardIndex = 0;

    /**
     * Initialize the deck without any cards
     */
    public Deck()
    {
        //initialiseFullDeck();
    }
    /**
     * Initialize the deck with full 52 cards if boolean is true.
     * @param fullDeck
     */
    public Deck(boolean fullDeck)
    {
        if (fullDeck == true)
        {
            initialiseFullDeck();
        }
    }
    
    /**
     * Method for initializing the full deck.
     */
    private void initialiseFullDeck()
    {  	 	
    	for (int suit = 1; suit <= 4; suit++) // suit
    	{
    		for (int value = 1; value <= 13; value++) // value
    		{
    			deck.add(new Card(value, suit));
    		}
    	}
    	deckSize = deck.size();
    }
    
    /**
     * Draws a card from the deck then returns the value as a Card object
     * @return Card
     */
    public Card drawCard()
    {
    	Card tempCard = deck.get(0);
    	
    	if (deckSize > 0)
    	{
    		deck.remove(0);
    		deckSize = deck.size();
    		return tempCard;
    	}
    	else
    	{
    		System.out.println("The deck has ran out of cards");
    		return null;
    	}
    }
    
    /**
     * Places the card back the deck at the last index.
     * @param card
     */
    public void placeCard(Card card)
    {
    	deck.add(card);
    	deckSize = deck.size(); // Update the deckSize
    }
    
    /**
     *  Shuffles the deck using Collections.shuffle method.
     */
    public void shuffle()
    {
    	//int index = 0;
    	//Card temp;
    	Collections.shuffle(deck);
    	
    	/*for (int i = deck.length - 1; i > 0; i--)
    	{
    		index = rand.nextInt(i + 1);
    		temp = deck[index];
    		deck[index] = deck[i];
    		deck[i] = temp;
    	}*/
    	
    }
    
    /**
     * Returns the value of the current deck size.
     * @return int
     */
    public int getDeckSize()
    {
    	return deckSize;
    }
    
    /**
     * Checks if the deck has remaining cards left
     * @return boolean
     */
    public boolean hasCardsRemaining()
    {
    	for (Card cards : deck)
    	{
    		if (cards != null)
    		{
    			return true;
    		}
    	}
    	return false;
    }
    
    /**
     * Returns the string of all card ranks in the deck.
     */
    public String toString()
    {
    	String cardString = new String();
    	for (Card cards : deck )
    	{
    		cardString = cardString.concat(cards.toString() + ", ");
    	}
    	return cardString;
    }
}
