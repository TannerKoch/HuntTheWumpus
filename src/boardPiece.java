import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * @author James DeVeuve & Tanner Koch
 * TA: Mike Collins & Ben Whitely
 * Hunt The Wumpus Iteration 2
 * 
 * The intent of this class is to provide a supertype that can be used by the board class
 * to create a 2d array of the supertype object. This allows us to randomly assign
 * pits/slime and the wumpus/blood at run time via polymorphism. (We are telling it
 * to use boardPiece objects, then swapping some of those objects to be of the type Blood/
 * Wumpus/Etc...
 */


public class boardPiece {
	public boolean isVisible;
	private Image image;
	private Image image2;
	private String fileName = "Ground.png";
	private String fileName2 = "Black.png";
	public boardPiece() {
		
	isVisible = false;	
	}
	public String getSymbol() {
		return "[ ]";
	}
	public Image getImage() {
		try {
			image = ImageIO.read(new File(fileName));
		} catch(IOException e) {
			e.printStackTrace();
		}
		return image;
	}
	public Image getImage2(){
		try {
			image2 = ImageIO.read(new File(fileName2));
		} catch(IOException e) {
			e.printStackTrace();
		}
		return image2;
	}

}
