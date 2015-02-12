import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * @author James DeVeuve & Tanner Koch
 * TA: Mike Collins & Ben Whitely
 * Hunt The Wumpus Iteration 1
 * 
 * This class is designed to provide a blood object (of the super type boardPiece) that 
 * can be used by a board object in order to place blood around the wumpus within the game.
 * Provides a textual display of the object ([B]) and provides a hazard that this object 
 * implements ("You've Stepped In Blood!").
 */



public class Blood extends boardPiece implements Hazard{
	private String fileName = "Blood.png";
	private Image image;
	//private String hazard;
	
	
	public Blood() {
		
	}
	public String getSymbol() {
		return "[B]";
	}
	public String causeHazard() {
		return "You've stepped in blood!";		
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
