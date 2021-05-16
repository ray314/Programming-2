/**
 * 
 */
package Games;

import java.util.Scanner;

/**
 * @author Justin Yeung
 *
 */
public class DrinkingGame {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Deck newDeck = new Deck(true);
		Card card1;
		Card card2;
		int player1Shots = 0;
		int player2Shots = 0;
		Scanner scan = new Scanner(System.in);
		newDeck.shuffle();
		boolean keepPlaying = true;
		
		while (keepPlaying == true)
		{
			System.out.print("Player one: Press enter to draw a card.");
			scan.nextLine();
			card1 = newDeck.drawCard();
			System.out.print("Player two: Press enter to draw a card.");
			scan.nextLine();
			card2 = newDeck.drawCard();
			
			System.out.println("Player one card rank: " + card1.toString());
			//System.out.printf("Value: %d Suit: %d%n", card1.getValue(), card1.getSuit());
			System.out.println("Player two card rank: " + card2.toString());
			//System.out.printf("Value: %d Suit: %d%n", card2.getValue(), card2.getSuit());
			//System.out.println(card1.compareTo(card2));
			if (card1.compareTo(card2) > 0) // Compare the card rank
			{
				System.out.println("Player one's card rank is higher, Player two must drink the shot");
				player2Shots++;
			}
			else if (card1.compareTo(card2) < 0)
			{
				System.out.println("Player two's card rank is higher, Player one must drink the shot");
				player1Shots++;
			}
			else
			{
				System.out.println("The player card ranks draw.");
			}
			System.out.println("Current cards left: " + newDeck.getDeckSize());
			if (newDeck.getDeckSize() <= 0)
			{
				System.out.println("Ran out of cards, game ending...");
				System.out.println("Player one has drank " + player1Shots + "shots.");
				System.out.println("Player two has drank " + player2Shots + "shots.");
				keepPlaying = false;
			}
		}
	}

}
