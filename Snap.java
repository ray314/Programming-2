/**
 * 
 */
package Games;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class for the game Snap.
 * @author Justin Yeung
 *
 */
public class Snap {

	private int playerTurn = 1;
	private Deck player1Deck;
	private Deck player2Deck;
	private ArrayList<Card> pile;
	
	public final int PLAYER_1 = 1;
	public final int PLAYER_2 = 2;
	
	/**
	 * Initializes the Snap class.
	 */
	public Snap()
	{
		player1Deck = new Deck(true); // Initialise the full deck for each player.
		player2Deck = new Deck(true);
		
		for (int i = 0; i < 26; i++)
		{
			player1Deck.drawCard();
			player2Deck.drawCard(); // Remove 26 cards from each deck so each player has a deck of 26 cards
		}
		
		pile = new ArrayList<Card>(); // Create a new ArrayList.
		setupPlayerDecks(); // Shuffle the player decks.
	}
	
	/**
	 * Shuffles the player's decks.
	 */
	private void setupPlayerDecks()
	{
		player1Deck.shuffle();
		player2Deck.shuffle();
	}
	
	/**
	 * Picks up the centre pile and adds it to the player's deck. Shuffles the player's deck afterwards.
	 * @param player
	 */
	private void pickupPile(int player)
	{
		for (Card cards : pile)
		{
			if (player == PLAYER_1)
			{
				player1Deck.placeCard(cards);
			}
			else if (player == PLAYER_2)
			{
				player2Deck.placeCard(cards);
			}
		}
		
		switch (player)
		{
		case PLAYER_1:
			player1Deck.shuffle();
			
		case PLAYER_2:
			player2Deck.shuffle();
		}
		pile.clear();
	}
	
	/**
	 * Check if the player has snapped.
	 * @return boolean
	 */
	private boolean checkSnap()
	{
		if (pile.size() == 0)
		{
			System.out.println("Cannot snap when there are no cards in the pile");
			return false;
		}
		
		Card lastCard = pile.get(pile.size() - 1); // Check last two elements in the pile.
		Card last2Card = pile.get(pile.size() - 2);
		if (lastCard.getValue() == last2Card.getValue())
		{
			return true;
		}
		return false;
	}
	
	/**
	 * Calls the snap for the player and returns if the snap was successful
	 * @param player
	 * @return boolean
	 */
	public boolean snap(int player)
	{
		if (player == PLAYER_1)
		{
			playerTurn = 2;
			if (checkSnap() == true)
			{
				pickupPile(PLAYER_1);
				return true;
			}
			else
			{
				pickupPile(PLAYER_2);
			}
			
		}
		
		else if (player == PLAYER_2)
		{
			playerTurn = 1;
			if (checkSnap() == true)
			{
				pickupPile(PLAYER_2);
				return true;
			}
			else
			{
				pickupPile(PLAYER_1);
			}
			
		}
		return false;
	}
	
	/**
	 * Draws a card from the player's deck.
	 * @param player
	 * @return Card
	 */
	public Card drawCard(int player)
	{
		Card playerCard;
		if (player == PLAYER_1)
		{
			playerCard = player1Deck.drawCard();
			pile.add(playerCard);
			playerTurn = 2;
			return playerCard;
		}
		else if (player == PLAYER_2)
		{
			playerCard = player2Deck.drawCard();
			pile.add(playerCard);
			playerTurn = 1;
			return playerCard;
		}
		
		return null;
	}
	
	/**
	 * Checks if one player has ran out of cards.
	 * @return boolean
	 */
	public boolean hasGameFinished()
	{
		if (player1Deck.hasCardsRemaining() == false)
		{
			return true;
		}
		else if (player2Deck.hasCardsRemaining() == false)
		{
			return true;
		}
		
		return false;
	}
	
	/**
	 * Check who is the winner
	 * @param player
	 * @return boolean
	 */
	public boolean isWinner(int player)
	{
		switch (player)
		{
		
		case PLAYER_1:
			return player1Deck.hasCardsRemaining();
			
		case PLAYER_2:
			return player2Deck.hasCardsRemaining();
		}
		
		return false;
	}
	
	/**
	 * Gets the player's turn
	 * @return int
	 */
	public int getPlayerTurn()
	{
		return playerTurn;
	}
	
	/**
	 * Gets the player's remaining cards
	 * @param player
	 * @return int
	 */
	public int getPlayerCardsRemaining(int player)
	{
		switch (player)
		{
		case PLAYER_1:
			return player1Deck.getDeckSize();
			
		case PLAYER_2:
			return player2Deck.getDeckSize();
		}
		
		return 0;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Snap snap = new Snap(); // Instantiate Snap class
		Scanner scan = new Scanner(System.in); // Instantiate Scanner class
		int input = 0; 
		int playerTurn = snap.getPlayerTurn();
		Card[] playerCard = new Card[3];
		boolean snapSuccessful;
		boolean keepPlaying = true;
		
		while (keepPlaying == true)
		{
			System.out.printf("Player %d's cards remaining: %d%n", playerTurn, snap.getPlayerCardsRemaining(playerTurn));
			System.out.printf("Player %d turn(1 = draw, 2 = snap): ", playerTurn);
			input = scan.nextInt();
			
			if (input == 1) // Check the player's input.
			{
				playerCard[playerTurn] = snap.drawCard(playerTurn);
				System.out.println("You have drawn: " + playerCard[playerTurn].toString());
				playerTurn = snap.getPlayerTurn(); // Next player's turn
			}
			else if (input == 2)
			{
				snapSuccessful = snap.snap(playerTurn);
				if (snapSuccessful == true)
				{
					System.out.printf("Snapped! The pile of cards now go to player %d's deck.%n", playerTurn);
				}
				else
				{
					System.out.println("Unsuccessful snap, the pile of cards now go to the opposing player's deck.");
				}
				playerTurn = snap.getPlayerTurn();
			}
			
			if (snap.hasGameFinished() == true)
			{
				keepPlaying = false; // Break the loop if one player ran out of cards.
			}
		}
		
		if (snap.isWinner(1) == true)
		{
			System.out.println("Player 2 has ran out of cards.");
			System.out.println("Player 1 has won the game with " + snap.getPlayerCardsRemaining(1) + " cards remaining.");
		}
		else if (snap.isWinner(2) == true)
		{
			System.out.println("Player 1 has ran out of cards.");
			System.out.println("Player 2 has won the game with " + snap.getPlayerCardsRemaining(2) + " cards remaining.");
		}
		else
		{
			System.out.println("The game has ended in a draw with both players ran out of cards.");
		}
	}
}
