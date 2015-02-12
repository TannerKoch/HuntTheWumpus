import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * @author James DeVeuve & Tanner Koch
 * TA: Mike Collins & Ben Whitely
 * Hunt The Wumpus Iteration 2
 * 
 * This class is designed to provide a Pit object (of the super type boardPiece) that 
 * can be used by a board object in order to place pits randomly around the board.
 * Provides a textual display of the object ([P]) and provides a hazard that this object 
 * implements ("You've Fallen Into A Pit!").
 */



public class Pit extends boardPiece implements Hazard{
	private Image image;
	private String fileName = "SlimePit.png";
	private String hazard;
	public Pit() {
		
	}
	public String causeHazard() {
		hazard = "You've fallen into a pit!";
		return hazard;
	}
	public String getSymbol() {
		return "[P]";
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
