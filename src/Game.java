import java.util.Scanner;

/**
 * This is the main class of the Wheel of Fortune game. From here you launch the
 * game and call the methods from the Phrase and Wheel classes
 * 
 * @author Jiri Valcikevic
 * @version 1.0
 */

public class Game {

	public static void main(String[] args) {
		Scanner reader = new Scanner(System.in);
		String userInput;
		String wheelResult;
		char userGuess;
		int numberOfPlayers = 0;
		int pointsAdded = 0; // points accrued this round
		int bonusTotal = 0; // this is delivered from the Phrase class later and
							// lowers each time a player guesses a letter. So
							// the quicker it is solved, the more bonus points
							// they score
		Wheel wheelObj = new Wheel();
		Phrase phraseObj = new Phrase();

		System.out.println("This is Wheel of Fortune!");
		System.out.println("Type number of players");
		try {
			numberOfPlayers = reader.nextInt();			
		} catch (Exception e) {
			System.out.println("Sorry, You must write number");
			System.out.println("Program finished, you must run it again ...");
			System.exit(0);
		}
		
		// player score(s) stored here
		int[] playerScores = new int[numberOfPlayers];
		
		// first element of array is always 0. So when this is used, +1 is always added
		int currentPlayer = 0; 

		System.out.println("Player 1, Type \"S\" or \"s\" to start a game:");
		// spinning the wheel and showing the results

		do {
			userInput = reader.nextLine().toLowerCase();

			if (userInput.equals("s")) {

				wheelResult = wheelObj.spinWheel();

				switch (wheelResult) {
				case "Bankrupt":
					playerScores[currentPlayer] = 0;
					System.out.println("Sorry, Player " + (currentPlayer + 1) + ", bankrupt!");
					currentPlayer = NextPlayer(currentPlayer, numberOfPlayers); 
					System.out.println("Type \"S\" to Start your turn, Player " + (currentPlayer + 1));
					break;
				case "Lose a Turn":
					System.out.println("Sorry, Player " + (currentPlayer + 1) + ", you lose a turn");
					currentPlayer = NextPlayer(currentPlayer, numberOfPlayers);
					System.out.println("Type \"S\" to Start your turn, Player " + (currentPlayer + 1));
					break;
				case "Free Spin":
					System.out.println("Free spin, Type \"S\" to Start again ...");
					break;
				default:
					System.out.println("You landed on" + " £" + wheelResult + ". Guess a letter. Vowels cost £250");
					int wheelValue = Integer.parseInt(wheelResult);
					phraseObj.gameBoard();

					// Guess calculations
					int letterCount;
					userGuess = reader.next().toLowerCase().charAt(0);

					// Checks if vowels were used in guess and docks before phrase comparison
					if ((userGuess == 'a') || (userGuess == 'e') || (userGuess == 'i') || (userGuess == 'o') || (userGuess == 'u')) {
						playerScores[currentPlayer] -= 250;
						System.out.println("£250 docked from score for vowel use");
					}

					letterCount = phraseObj.makeGuess(userGuess);

					if (letterCount >= 1) {
						pointsAdded = letterCount * wheelValue;
						System.out.println("Correct! This letter was found " + letterCount + " times(s)");
						System.out.println("£" + pointsAdded + " has been added to your score");
						playerScores[currentPlayer] += pointsAdded;
						System.out.println("Your new score is £" + playerScores[currentPlayer]);
						System.out.println("Type \"S\" to continue or \"G\" to solve the phrase");
					} else {
						currentPlayer = NextPlayer(currentPlayer, numberOfPlayers);
						System.out.println("Sorry that letter isn't present. Start your turn, Player " + (currentPlayer + 1));
					}
				}
			}

			// instead of spinning, a player can choose to guess the whole phrase
			if (userInput.equals("g")) {
				System.out.println("Enter your full phrase guess");
				String userFullGuess;
				String guessCheck;

				userFullGuess = userInput = reader.nextLine();
				guessCheck = phraseObj.fullGuess(userFullGuess);

				if (guessCheck.equals("incorrect")) {
					currentPlayer = NextPlayer(currentPlayer, numberOfPlayers);
					System.out.println("Sorry that is incorrect. Type \"S\" to Start your turn, Player " + (currentPlayer + 1)); 
				} 
				else {
					bonusTotal = Integer.parseInt(guessCheck);
				}
			} 
		} 
		// Game checks if whole phrase has been guessed and ends if true
		while ((!phraseObj.gameComplete()) && (!userInput.equals("end")));
		
		if (userInput.equals("end")) {
			System.out.println("Game terminated");
		}
		else {
			System.out.println("Congratulations, Player " + (currentPlayer + 1) + ", you have completed the phrase");
			System.out.println("Score = £" + playerScores[currentPlayer]);
			System.out.println("Bonus Points = £" + bonusTotal);
			playerScores[currentPlayer] += bonusTotal;
			System.out.println("Final score = £" + playerScores[currentPlayer]);
		}
		reader.close();
	}

	/**
	 * this function moves the game on so the next player can spin. if it is at
	 * the last player then 0 is returned to start from the beginning
	 * 
	 * @param currentPlayer
	 *            the player whose turn it currently is
	 * @param numberOfPlayers
	 *            the total number of people playing the game
	 * @return 0 or +1 depending on whose turn it is
	 */
	private static int NextPlayer(int currentPlayer, int numberOfPlayers) {
		if ((currentPlayer + 1) == numberOfPlayers) {
			return 0;
		} 
		else {
			return currentPlayer + 1;
		}
	}
}
