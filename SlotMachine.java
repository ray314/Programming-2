package slotMachine;

import java.util.*;

/**
 * @author Justin Yeung
 *
 */
public class SlotMachine
{
    private Random generator = new Random();
    private int tokenCredit;

    public SlotMachine(int tokenCredit)
    {
        this.tokenCredit = tokenCredit;
    }

    public void topupTokens(int tokens)
    {
        if (tokenCredit < 0)
        {
            tokenCredit = 0; // Set credit to 0 if it goes negative.
        }

        tokenCredit += tokens;
    }

    public int cashoutTokens()
    {
        if (tokenCredit > 0)
        {
            return tokenCredit;
        }
        return 0;
        
    }

    public void pullLever()
    {
        int[] numbers = new int[3];
        numbers[0] = generator.nextInt(9);
        numbers[1] = generator.nextInt(9);
        numbers[2] = generator.nextInt(9);

        this.tokenCredit--;
        System.out.printf("{%d,%d,%d}%n", numbers[0], numbers[1], numbers[2]);
        if ((numbers[0] == 0) && (numbers[1] == 0) && (numbers[2] == 0))
        {
            tokenCredit += 500;
            System.out.println("Super Jackpot Winner");
        }
        else if ((numbers[0] == numbers[1]) && (numbers[1] == numbers[2]) && (numbers[2] == numbers[0]))
        {
            tokenCredit += 50;
            System.out.println("Jackpot Winner");
        }
        else if ((numbers[0] == numbers[1]) || (numbers[1] == numbers[2]) || (numbers[2] == numbers[0]))
        {
            tokenCredit++;
            System.out.println("Free spin");
        }
        else 
        {
            System.out.println("Bad luck, Try again");
        }

    }

    public void pullLever(int tokenInput)
    {
        int[] numbers = new int[3];
        numbers[0] = generator.nextInt(9);
        numbers[1] = generator.nextInt(9);
        numbers[2] = generator.nextInt(9);

        this.tokenCredit -= tokenInput;
        System.out.printf("{%d,%d,%d}%n", numbers[0], numbers[1], numbers[2]);
        if ((numbers[0] == 0) && (numbers[1] == 0) && (numbers[2] == 0))
        {
            tokenCredit = tokenCredit + tokenInput * 500;
            System.out.println("Super Jackpot Winner");
        }
        else if ((numbers[0] == numbers[1]) && (numbers[1] == numbers[2]) && (numbers[2] == numbers[0]))
        {
            tokenCredit = tokenCredit + tokenInput * 50;
            System.out.println("Jackpot Winner");
        }
        else if ((numbers[0] == numbers[1]) || (numbers[1] == numbers[2]) || (numbers[2] == numbers[0]))
        {
            tokenCredit += tokenInput;
            System.out.println("Free spin");
        }
        else 
        {
            System.out.println("Bad luck, Try again");
        }
    }

    public int getTokenBalance()
    {
        return tokenCredit;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int tokens;
        int tokenInput;
        SlotMachine slot;
        boolean keepPlaying = true;
        boolean exitCasino = false;
        int houseCredit = 0;

        while (exitCasino == false) // Check if the player is still in the casino
        {

            System.out.print("How many tokens to start with? "); // Ask the user how many tokens to start with
            tokens = input.nextInt(); // Set tokens to user input.
            System.out.print("How many tokens to use each lever pull? ");
            tokenInput = input.nextInt();

            slot = new SlotMachine(tokens); // Create a new slot machine and input the amount of tokens.
            
            while (keepPlaying == true) // Loop to keep the machine going.
            {
                if (slot.getTokenBalance() <= 0) // Check if the user has credits
                {
                    System.out.print("You currently have no credits. Do you want to topup(y/n)? ");
                    if (input.next().charAt(0) == 'y')
                    {
                        System.out.print("How much to topup? "); // Ask how much to top up
                        slot.topupTokens(input.nextInt()); // Call topup method and pass the user input
                    }
                    else
                    {
                        break; // Break the loop if the user does not want to top up
                    }
                }

                System.out.printf("Credit remaining: %d. Pull lever(y/n)? ", slot.getTokenBalance());
                if (input.next().charAt(0) == 'y')
                {
                    if (slot.getTokenBalance() <= 0) // Check if the user has enough tokens
                    {
                        System.out.print("You have ran out of tokens, topup(y/n)? ");
                        if (input.next().charAt(0) == 'y')
                        {
                            System.out.print("How much to topup? "); // Ask how much to top up
                            slot.topupTokens(input.nextInt()); // Call topup method and pass the user input
                        }
                        else
                        {
                            break; // Else break the loop.
                        }
                    }

                    slot.pullLever(tokenInput); // Pass the variable into the method.
                    houseCredit += tokenInput; // Increment the house credits.
                }

                System.out.println("Keep playing or cash out(y/n)? ");
                if (input.next().charAt(0) == 'n')
                {
                    keepPlaying = false;    
                }
            }

            System.out.printf("You have cashed out %d tokens.%n", slot.cashoutTokens()); // Print the cash out tokens
            System.out.printf("You house credit is %d tokens%n", houseCredit);
            System.out.print("Leave casino or play a different slot machine(y/n)? ");
            if (input.next().charAt(0) == 'y') // Ask the user if they want to leave the casino or play on a different machine
            {
                exitCasino = true; // Break the while loop and end the program.
            }
    }
        
    System.out.println("You have left the casino.");

    }
}