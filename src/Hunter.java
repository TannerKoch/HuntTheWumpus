import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * @author James DeVeuve & Tanner Koch
 * TA: Mike Collins & Ben Whitely
 * Hunt The Wumpus Iteration 2
 * 
 * This class is designed to provide a Hunter object (of the super type boardPiece) that 
 * can be used by a board object in order to correctly walk through the game.
 * Provides a textual display of the object ([H]), though it does not actually implement
 * any form of shoot since that is handled within the board class.
 */



public class Hunter extends boardPiece {
	private String fileName = "TheHunter.png";
	private Image image;
	public Hunter() {
		
	}
	public String getSymbol() {
		return "[H]";
	}
	public Image getImage() {
		try {
			image = ImageIO.read(new File(fileName));
		} catch(IOException e) {
			e.printStackTrace();
		}
		return image;
	}
}
