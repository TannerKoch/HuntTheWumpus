import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * @author James DeVeuve & Tanner Koch
 * TA: Mike Collins & Ben Whitely
 * Hunt The Wumpus Iteration 2
 * 
 * This class is designed to provide a Goop object (of the super type boardPiece) that 
 * can be used by a board object in order to place Goop around the wumpus and slime pits
 * within the game. Provides a textual display of the object ([G]) and provides a hazard 
 * interface that this object implements ("You've Stepped In Blood!").
 */



public class Goop extends boardPiece implements Hazard{
	private String fileName = "Goop.png";
	private Image image;
	public Goop() {
		
	}
	public String getSymbol() {
		return "[G]";
	}
	public String causeHazard() {
		return "You've stepped in goop";
		
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
