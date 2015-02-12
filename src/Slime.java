import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;



/**
 * @author James DeVeuve & Tanner Koch
 * TA: Mike Collins & Ben Whitely
 * Hunt The Wumpus Iteration 2
 * 
 * This class is designed to provide a Slime object (of the super type boardPiece) that 
 * can be used by a board object in order to place Slime around the pits within the game.
 * Provides a textual display of the object ([S]) and provides a hazard that this object 
 * implements ("You've Stepped In Slime!").
 */
public class Slime extends boardPiece implements Hazard {
	private Image image;
	private String fileName = "Slime.png";
	public Slime() {
		
	}
	public String causeHazard() {
		return "You stepped in Slime!";
	}
	public String getSymbol() {
		return "[S]";
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
