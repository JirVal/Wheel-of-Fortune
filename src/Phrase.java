/**
 * This is where the phrase is stored and can be accessed using the methods
 * 
 * @version 1.0
  */
public class Phrase {
	String gamePhrase = "luke i am your father"; //correct phrase
	String guessPhrase = "---- - -- ---- ------"; //place holders
	int guessCount = 26; //guess count starts at 26 and is lowered 1 for a letter guess and 5 for an incorrect full guess
	
	/**
	 * prints the game board out to the console
	 */
	public void gameBoard(){
		System.out.println(guessPhrase);
	}
		/**
		 * takes the letter guessed from the game class and checks it against the
		 * game phrase
		 * 
		 * @param letter the letter guessed by the player
		 * @return whether that letter appears in the phrase and how many times
		 */
		public int makeGuess(char letter){
		guessCount --; //minus 1 per letter guess
		String guesses = "";
		int lettersFound = 0;
		
		for (int x = 0; x<gamePhrase.length(); x++){
			if (letter == gamePhrase.charAt(x)){
				
				guesses += letter;
				lettersFound++;
			}
			else {
				guesses += guessPhrase.charAt(x);
			}
		}
		
		guessPhrase = guesses;
		System.out.println(guessPhrase);
		return lettersFound;
		
	}
	
	/**
	 * takes the guess of the full phrase from the player and checks it
	 * against the game phrase 
	 * 
	 * @param guess phrase guess input from player
	 * @return the guessCount (lowered with each guess) times 200, providing the bonus points for a correct answer
	 * or the feedback that the answer was incorrect and 5 points docked from the bonus
	 */
	public String fullGuess(String guess){
		String bonusTrans;
		if (guess.equals(gamePhrase)){
			guessPhrase = guess;
			bonusTrans = Integer.toString(guessCount * 200);
		
			return bonusTrans;
		}
	else {
		guessCount -= 5; //minus 5 for incorrect guess
		return "incorrect";
		}
	}
	
	/**
	 * checks the two phrases against each other and returns a true or false
	 * 
	 * @return if the whole phrase has been guessed
	 */
	public boolean gameComplete(){
		if (guessPhrase.equals(gamePhrase)){
			return true;
		}
		else {
			return false;
		}
	}
	
}
