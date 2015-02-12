/**
 * @author James DeVeuve & Tanner Koch
 * TA: Mike Collins & Ben Whitely
 * Hunt The Wumpus Iteration 1
 * 
 * The intent of this class is to act as the main method that handles the execution of the
 * Hunt the Wumpus application. Using a loop it ensures that the game continuously updates the
 * hunters position as well as suitably prints warnings based on hazards the hunter encounters.
 * Within this loop, the system will propagate the player for directions based on the actions 
 * they wish to perform, then perform them accordingly. When an event occurs to cause the game
 * to end, a suitable message is printed to the console and the program closes.
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observer;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.plaf.basic.BasicArrowButton;

public class PlayGame {
	// this class is the one that will be notifying observers
	/**
	 * TODO: Register wumpusGui and Board as observers, board handles the
	 * text-based view, wumpusGui handles the gui view. After Each valid move
	 * call notify observers and update the other maps accordingly.
	 */
	Slime slime = new Slime();
	Pit pit = new Pit();
	Blood blood = new Blood();
	Goop goop = new Goop();
	Wumpus wumpus = new Wumpus();
	boardPiece bp = new boardPiece();
	Hunter h = new Hunter();
	Board b = new Board();
	private JPanel view = new TextView();
	private JPanel view1 = new wumpusGui();
	private JTabbedPane severalPanels;
	private JPanel textBox = new JPanel();
	private JTextField text = new JTextField(15);
	ActionListener buttonListener = new MovementButtonListener();
	ActionListener shootListener = new ArrowButtonListener();

	JLabel buttonLabel = new JLabel();
	JLabel arrowLabel = new JLabel();
	Font font = new Font("Verdana", Font.BOLD, 12);
	JPanel upperPanel = new JPanel();
	JPanel lowerPanel = new JPanel();

	//
	BasicArrowButton up, down, left, right, shootUp, shootDown, shootLeft,
			shootRight;
	JPanel directionPanel = new JPanel();
	JPanel arrowPanel = new JPanel();
	JPanel holdThePanels = new JPanel();
	JFrame frame = new JFrame("Hunt The Wumpus");
	//
	JPanel holdTheMovementLabel = new JPanel();
	JPanel holdTheArrowLabel = new JPanel();
	
	boolean result;

	public static void main(String[] args) {
		Board b = new Board();
		PlayGame p = new PlayGame();
		System.out.println(b.printMap());
	}

	// print finished version
	//
	public PlayGame() {
		super();
		addObservers();
		holdThePanels.setLayout(new BorderLayout());
		frame.setSize(704, 570);
		frame.setResizable(false);
		frame.setBackground(Color.DARK_GRAY);

		//
		up = new BasicArrowButton(SwingConstants.NORTH);
		down = new BasicArrowButton(SwingConstants.SOUTH);
		left = new BasicArrowButton(SwingConstants.WEST);
		right = new BasicArrowButton(SwingConstants.EAST);
		directionPanel.setPreferredSize(new Dimension(100, 100));
		directionPanel.setLayout(new GridLayout(2, 3));
		arrowPanel.setLayout(new GridLayout(2, 3));
		arrowPanel.setPreferredSize(new Dimension(100, 100));

		//
		up.setFocusable(false);
		down.setFocusable(false);
		left.setFocusable(false);
		right.setFocusable(false);
		//
		up.addActionListener(buttonListener);
		down.addActionListener(buttonListener);
		left.addActionListener(buttonListener);
		right.addActionListener(buttonListener);
		//
		directionPanel.add(new JPanel());
		directionPanel.add(up);
		directionPanel.add(new JPanel());
		directionPanel.add(left);
		directionPanel.add(down);
		directionPanel.add(right);
		// //////////////////////////
		// set up shooting arrows now
		shootUp = new BasicArrowButton(SwingConstants.NORTH);
		shootLeft = new BasicArrowButton(SwingConstants.WEST);
		shootDown = new BasicArrowButton(SwingConstants.SOUTH);
		shootRight = new BasicArrowButton(SwingConstants.EAST);
		shootUp.setFocusable(false);
		shootDown.setFocusable(false);
		shootLeft.setFocusable(false);
		shootRight.setFocusable(false);
		shootUp.addActionListener(shootListener);
		shootDown.addActionListener(shootListener);
		shootLeft.addActionListener(shootListener);
		shootRight.addActionListener(shootListener);
		arrowPanel.add(new JPanel());
		arrowPanel.add(shootUp);
		arrowPanel.add(new JPanel());
		arrowPanel.add(shootLeft);
		arrowPanel.add(shootDown);
		arrowPanel.add(shootRight);

		holdTheMovementLabel.setLayout(new FlowLayout());
		holdTheMovementLabel.add(new JLabel());
		holdTheMovementLabel.add(buttonLabel);
		holdTheMovementLabel.add(new JLabel());
		holdTheArrowLabel.setLayout(new FlowLayout());
		holdTheArrowLabel.add(new JLabel());
		holdTheArrowLabel.add(arrowLabel);
		holdTheArrowLabel.add(new JLabel());
		severalPanels = new JTabbedPane();
		severalPanels.add(view, "Text View");
		severalPanels.add(view1, "GUI");
		frame.add(severalPanels, BorderLayout.CENTER);

		arrowLabel.setText("Arrow Buttons");
		arrowLabel.setForeground(Color.BLACK);
		arrowLabel.setFont(font);
		buttonLabel.setText("Movement Buttons");
		buttonLabel.setForeground(Color.BLACK);
		textBox.setLayout(new GridLayout(5, 1));
		//
		text.setForeground(Color.RED);
		text.setEditable(false);
		text.setFont(font);
		text.setText("Click a direction to begin");
		buttonLabel.setFont(font);
		//
		textBox.add(new JPanel());
		textBox.add(new JPanel());
		textBox.add(text);
		textBox.add(new JPanel());
		textBox.add(new JPanel());
		// finish the frame
		upperPanel.setLayout(new GridLayout(2, 1));
		upperPanel.add(holdTheMovementLabel);
		upperPanel.add(directionPanel);
		//
		lowerPanel.setLayout(new GridLayout(2, 1));
		lowerPanel.add(holdTheArrowLabel);
		lowerPanel.add(arrowPanel);
		//
		holdThePanels.add(upperPanel, BorderLayout.NORTH);
		holdThePanels.add(lowerPanel, BorderLayout.SOUTH);
		holdThePanels.add(textBox, BorderLayout.CENTER);

		frame.add(holdThePanels, BorderLayout.WEST);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

	}

	private void addObservers() {
		b.addObserver((Observer) view);
		b.addObserver((Observer) view1);
	}

	private class MovementButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			text.setText(null);
			int value = 0;
			if (arg0.getSource() == up) {
				b.moveHunter(2);
			}
			if (arg0.getSource() == down) {
				b.moveHunter(1);
			}
			if (arg0.getSource() == left) {
				b.moveHunter(3);
			}
			if (arg0.getSource() == right) {
				b.moveHunter(4);
			}
			value = b.hunterAndHazard();
			if (value == 1) {
				text.setText(slime.causeHazard());
			}
			if (value == 2) {
				System.out.println("Game Over!");
				b.setEndGame(1);
				b.printFinishedMap();
				result = false;
				gameEnded();
			}
			if (value == 3) {
				text.setText(blood.causeHazard());
			}
			if (value == 4) {
				text.setText(goop.causeHazard());
			}
			if (value == 5) {
				text.setText(wumpus.causeHazard());
				System.out.println("Game Over!");
				b.setEndGame(1);
				b.printFinishedMap();
				result = false;
				gameEnded();
			}
		}

	}

	private class ArrowButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == shootUp) {
				if (b.shootArrow(2))
					result = true;
				else
					result = false;
				gameEnded();
			}
			if (e.getSource() == shootDown) {
				if (b.shootArrow(2))
					result = true;
				else
					result = false;
				gameEnded();
			}
			if (e.getSource() == shootLeft) {
				if (b.shootArrow(1))
					result = true;
				else
					result = false;
				gameEnded();
			}
			if (e.getSource() == shootRight) {
				if (b.shootArrow(1))
					result = true;
				else
					result = false;
				gameEnded();
			}

		}

	}

	public void gameEnded() {
		shootUp.removeActionListener(shootListener);
		shootDown.removeActionListener(shootListener);
		shootLeft.removeActionListener(shootListener);
		shootRight.removeActionListener(shootListener);
		up.removeActionListener(buttonListener);
		down.removeActionListener(buttonListener);
		left.removeActionListener(buttonListener);
		right.removeActionListener(buttonListener);
		text.setText(outcome(result));
	}
	public String outcome(boolean result) {
		String outcome = "";
		if (result == true) {
			outcome =  "You've slain The Wumpus";
		} else {
			outcome =  "You've died!";
		}
		return outcome;
	}

}

