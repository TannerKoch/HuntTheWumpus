/*
 * @author James DeVeuve & Tanner Koch
 * TA: Mike Collins && Ben Whitely
 * Hunt The Wumpus Iteration 2
 * 
 * The Intent of this class is for the wumpusGui to act as an observer to the 
 * PlayGame method.  The update method gets an update each time the board is changed
 * in the PlayGame method.  When the update is called, the GUI layout method in class
 * Board changes to the changed board.
 * 
 */


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class wumpusGui extends JPanel implements Observer {
	
	private Board piece = new Board();
	Slime slime = new Slime();
	Blood blood = new Blood();
	Hunter hunter = new Hunter();
	Wumpus wumpus = new Wumpus();
	Pit pit = new Pit();
	Goop goop = new Goop();
	boardPiece boardpiece = new boardPiece();

	public wumpusGui() {
		this.setBackground(Color.GRAY);
		this.add(piece.layoutScreen2());
	}

	@Override
	public void update(Observable arg0, Object arg) {
		this.setLayout(null);
		this.removeAll();
		Board b = (Board) arg0;
		boolean s = b.getEndGame();
		if (s == true) {
			this.add(b.FinishedlayoutScreen2());
		}
		else {
			this.add(b.layoutScreen2());
		}
	}

}


