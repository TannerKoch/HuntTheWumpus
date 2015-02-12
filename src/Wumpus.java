import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * @author James DeVeuve & Tanner Koch
 * TA: Mike Collins & Ben Whitely
 * Hunt The Wumpus Iteration 2
 * 
 * This class is designed to provide a Wumpus object (of the super type boardPiece) that 
 * can be used by a board object in order to place the wumpus within the game.
 * Provides a textual display of the object ([W]) and provides a hazard that this object 
 * implements ("Rawr I ate you!").
 */



public class Wumpus extends boardPiece implements Hazard {
	private Image image;
	private String fileName = "Wumpus.png";
	public Wumpus() {
		
	}
	public String causeHazard() {
		return "'Rawr I ate you' - The Wumpus";
	}
	public String getSymbol() {
		return "[W]";
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
