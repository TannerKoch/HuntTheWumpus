/*
 * @author James DeVeuve & Tanner Koch
 * TA: Mike Collins && Ben Whitely
 * Hunt The Wumpus Iteration 2
 * 
 * The Intent of this class is for the TextView to act as an observer to the 
 * PlayGame method.  The update method gets an update each time the board is changed
 * in the PlayGame method.  When the update is called, the printMap layout method in class
 * Board changes to the changed board.  Then the printMap is added to a JTxextArea
 * within a panel on PlayGame.
 * 
 */
import java.awt.Color;
import java.awt.Font;
import java.awt.TextArea;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;
import javax.swing.JTextArea;

public class TextView extends JPanel implements Observer {
	JTextArea textView = new JTextArea();
	Board b = new Board();

	public TextView() {
		textView.setBackground(Color.WHITE);
		setLayout(null);
		textView.setSize(500, 500);
		textView.setLocation(0, 0);
		textView.setFont(new Font("Courier", Font.PLAIN, 15));
		add(textView);
		textView.setText(b.printMap());
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		String map;
		Board b = (Board) arg0;
		boolean s = b.getEndGame();
		if (s == true) {
			map = b.printFinishedMap();
		} else
			map = b.printMap();

		textView.setText(map);

	}

}
