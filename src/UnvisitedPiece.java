
/**
 * @author James DeVeuve & Tanner Koch
 * TA: Mike Collins and Ben Whitely
 * Hunt The Wumpus Iteration 2
 * 
 * This class is designed to provide an UnvisitedPiece object (of the super type boardPiece) that 
 * can be used by a board object in order to place an "X" in the location of an unvisited space within the game.
 * Provides a textual display of the object ([X]) in the unvisited space.
 */


public class UnvisitedPiece extends boardPiece {
	public boolean isVisible;
	public UnvisitedPiece() {
		isVisible = false;

	}
	public String getSymbol() {
		return "[X]";
	}

}
