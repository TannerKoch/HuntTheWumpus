/**
 * @authors James DeVeuve & Tanner Koch
 * TA: Mike Collins & Ben Whitely
 * Hunt The Wumpus Iteration 2
 * 
 * The intent of this class is to provide the bulk of the functionality
 * for the hunt the wumpus program. This class takes in the various boardPiece objects
 * and constructs a playable hunt the wumpus board. It also handles the movement of the 
 * hunter and the shooting of the hunters arrow, while updating the map via print screen.
 */
import java.awt.Color;
import java.awt.GridLayout;
import java.util.Observable;
import java.util.Random;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/* adding to the wumpus:
 * 
 * getImage method in each class to provide blood/slime/goop/wumpus picture
 * new class to set the gui up/ handle how the game is run/set background image
 * figure out how to implement the observer pattern 
 * --- board be observable, update observers as a player moves so you can have text/
 * --- graphics display
 * button panel similar to section... implement the keyboard listener later
 * Hide the board in black, until the hunter has visited it, then show what that room holds.
 * 
 * Random side shit:
 * Maybe have a couple different possibilities for what an empty room looks like
 * ---randomly roll and decide which image it should be every time getImage is called.
 * 
 */
public class Board extends Observable {
	Random r = new Random();
	int numberOfPits = r.nextInt(3) + 3;

	Random j = new Random();
	boardPiece[][] board = new boardPiece[10][10];
	int[][] visibleBoard = new int[10][10]; // will store an int as to what can
											// be visible and not
	boardPiece[][] visible = new boardPiece[10][10]; // the board that has the
														// [X] pieces taken out
														// when moved

	private int[][] storeValues = new int[10][10];
	int hunterRow = 0;
	int hunterColumn = 0;
	int slimeValue = 1;
	int pitValue = 2;
	int bloodValue = 3;
	int goopValue = 4;
	int wumpusValue = 5;
	int hunterValue = 6;
	boolean endGame = false;

	Wumpus wumpus = new Wumpus();
	boardPiece boardPiece = new boardPiece();
	Blood blood = new Blood();
	Hunter hunter = new Hunter();
	int wumpusRow = j.nextInt(10);
	int wumpusColumn = j.nextInt(10);

	public Board() {
		for (int i = 0; i > storeValues.length; i++) {
			for (int j = 0; j < storeValues[0].length; j++) {
				this.storeValues[i][j] = 0;
			}
		}
		setBoard();
		setVisibleArray();
	}

	private void setBoard() {

		while (board[wumpusRow][wumpusColumn] != null
				|| (wumpusRow == 0 && wumpusColumn == 0)) {
			wumpusRow = j.nextInt(10);
			wumpusColumn = j.nextInt(10);
		}

		board[wumpusRow][wumpusColumn] = wumpus;
		storeValues[wumpusRow][wumpusColumn] = wumpusValue;

		board[(wumpusRow + 1) % 10][wumpusColumn] = blood;
		storeValues[(wumpusRow + 1) % 10][wumpusColumn] = bloodValue;

		board[(wumpusRow + 2) % 10][wumpusColumn] = blood;
		storeValues[(wumpusRow + 2) % 10][wumpusColumn] = bloodValue;

		board[wumpusRow][(wumpusColumn + 1) % 10] = blood;
		storeValues[wumpusRow][(wumpusColumn + 1) % 10] = bloodValue;
		board[wumpusRow][(wumpusColumn + 2) % 10] = blood;
		storeValues[wumpusRow][(wumpusColumn + 2) % 10] = bloodValue;
		board[(wumpusRow + 1) % 10][(wumpusColumn + 1) % 10] = blood;
		storeValues[(wumpusRow + 1) % 10][(wumpusColumn + 1) % 10] = bloodValue;

		if (wumpusRow > 0) {
			board[wumpusRow - 1][(wumpusColumn + 1) % 10] = blood;
			storeValues[wumpusRow - 1][(wumpusColumn + 1) % 10] = bloodValue;
			if (wumpusRow == 1) {
				board[0][wumpusColumn] = blood;
				storeValues[0][wumpusColumn] = bloodValue;
				board[9][wumpusColumn] = blood;
				storeValues[9][wumpusColumn] = bloodValue;
			} else {
				board[wumpusRow - 1][wumpusColumn] = blood;
				storeValues[wumpusRow - 1][wumpusColumn] = bloodValue;
				board[wumpusRow - 2][wumpusColumn] = blood;
				storeValues[wumpusRow - 2][wumpusColumn] = bloodValue;
			}
		} else {
			board[9][wumpusColumn - 1] = blood;
			board[9][(wumpusColumn + 1) % 9] = blood;
			storeValues[9][(wumpusColumn + 1) % 9] = bloodValue;
			storeValues[9][wumpusColumn - 1] = bloodValue;
			board[9][wumpusColumn] = blood;
			storeValues[9][wumpusColumn] = bloodValue;
			board[8][wumpusColumn] = blood;
			storeValues[8][wumpusColumn] = bloodValue;

		}
		if (wumpusColumn > 0) {
			board[(wumpusRow + 1) % 10][wumpusColumn - 1] = blood;
			storeValues[(wumpusRow + 1) % 10][wumpusColumn - 1] = bloodValue;
			if (wumpusColumn == 1) {
				board[wumpusRow][0] = blood;
				storeValues[wumpusRow][0] = bloodValue;
				board[wumpusRow][9] = blood;
				storeValues[wumpusRow][9] = bloodValue;
			} else {
				board[wumpusRow][wumpusColumn - 1] = blood;
				storeValues[wumpusRow][wumpusColumn - 1] = bloodValue;
				board[wumpusRow][wumpusColumn - 2] = blood;
				storeValues[wumpusRow][wumpusColumn - 2] = bloodValue;
			}
		} else {
			board[wumpusRow - 1][9] = blood;
			board[(wumpusRow + 1) % 9][9] = blood;
			storeValues[(wumpusRow + 1) % 9][9] = bloodValue;
			storeValues[wumpusRow - 1][9] = bloodValue;
			board[wumpusRow][9] = blood;
			storeValues[wumpusRow][9] = bloodValue;
			board[wumpusRow][8] = blood;
			storeValues[wumpusRow][8] = bloodValue;
		}
		if (wumpusRow > 0 && wumpusColumn > 0) {
			board[wumpusRow - 1][wumpusColumn - 1] = blood;
			storeValues[wumpusRow - 1][wumpusColumn - 1] = bloodValue;
		}

		// Randomly place the slime and pits
		// being mindful of where the wumpus is at

		// CHANGES:
		// fixed the implementation of blood spawning
		// fixed the slime spawning --> wont spawn over blood.
		// changed <=numberOfPits to < numberOfPits to get the correct #
		for (int i = 0; i < numberOfPits; i++) {
			int a = j.nextInt(10);
			int b = j.nextInt(10);
			while ((a == wumpusRow && b == wumpusColumn) || (a == 0)
					&& (b == 0)) {
				a = j.nextInt(10);
				b = j.nextInt(10);
			}

			// if a == 0 && not wumpusRow
			// else if a != 0 && not wumpusRow
			board[a][b] = new Pit();
			storeValues[a][b] = pitValue;
			if (a == 0 && !collision(a, b)) {
				if (board[9][b] == blood) {
					board[9][b] = new Goop();
					storeValues[9][b] = goopValue;
				} else if (board[9][b] == null) {
					board[9][b] = new Slime();
					storeValues[9][b] = slimeValue;
				}
			} else if (a != 0 && !collision(a - 1, b)) {
				if (board[a - 1][b] == blood) {
					board[a - 1][b] = new Goop();
					storeValues[a - 1][b] = goopValue;
				} else if (board[a - 1][b] == null) {
					board[a - 1][b] = new Slime();
					storeValues[a - 1][b] = slimeValue;
				}
			}
			if (a == 9 && !collision(a, b)) {
				if (board[0][b] == blood) {
					board[0][b] = new Goop();
					storeValues[0][b] = goopValue;
				} else if (board[0][b] == null) {
					board[0][b] = new Slime();
					storeValues[0][b] = slimeValue;
				}
			} else if (a != 9 && !collision(a + 1, b)) {
				if (board[a + 1][b] == blood) {
					board[a + 1][b] = new Goop();
					storeValues[a + 1][b] = goopValue;
				} else if (board[a + 1][b] == null) {
					board[a + 1][b] = new Slime();
					storeValues[a + 1][b] = slimeValue;
				}
			}
			if (b == 0 && !collision(a, b)) {
				if (board[a][9] == blood) {
					board[a][9] = new Goop();
					storeValues[a][9] = goopValue;
				} else if (board[a][9] == null) {
					board[a][9] = new Slime();
					storeValues[a][9] = slimeValue;
				}
			} else if (b != 0 && !collision(a, b - 1)) {
				if (board[a][b - 1] == blood) {
					board[a][b - 1] = new Goop();
					storeValues[a][b - 1] = goopValue;
				} else if (board[a][b - 1] == null) {
					board[a][b - 1] = new Slime();
					storeValues[a][b - 1] = slimeValue;
				}
			}
			if (b == 9 && !collision(a, b)) {
				if (board[a][0] == blood) {
					board[a][0] = new Goop();
					storeValues[a][0] = goopValue;
				} else if (board[a][0] == null) {
					board[a][0] = new Slime();
					storeValues[a][0] = slimeValue;
				}
			} else if (b != 9 && !collision(a, b + 1)) {
				if (board[a][b + 1] == blood) {
					board[a][b + 1] = new Goop();
					storeValues[a][b + 1] = goopValue;
				} else if (board[a][b + 1] == null) {
					board[a][b + 1] = new Slime();
					storeValues[a][b + 1] = slimeValue;
				}

			}
		}
		storeValues[hunterRow][hunterColumn] = hunterValue;
		board[hunterRow][hunterColumn] = hunter;
		visibleBoard[hunterRow][hunterColumn] = 1;
		for (int k = 0; k < board.length; k++) {
			for (int p = 0; p < board[0].length; p++) {
				if (board[k][p] == null) {
					board[k][p] = new UnvisitedPiece();
				}
			}
		}
		this.setChanged();
		notifyObservers();

	}

	// Basic print out of the map for now.
	public String printMap() {
		String s = "";
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				if (visibleBoard[i][j] != 1) {

					// System.out.print("[X]");
					s += "[X]";
				} else if (i == hunterRow && j == hunterColumn) {
					// System.out.print("[H]");
					s += "[H]";
				} else
					// System.out.print(visible[i][j].getSymbol());
					s += visible[i][j].getSymbol();

			}
			// System.out.println();
			s += "\n";
		}
		return s;
	}

	public void setVisibleArray() {
		for (int i = 0; i < visible.length; i++) {
			for (int j = 0; j < visible[0].length; j++) {
				if (board[i][j].getSymbol().equals("[X]")) {
					visible[i][j] = new boardPiece();
				} else {
					visible[i][j] = board[i][j];
				}
			}
		}
		// visible[hunterRow][hunterColumn] = new Hunter();
	}

	public int getHunterRow() {
		return hunterRow;
	}

	public int getHunterColumn() {
		return hunterColumn;
	}

	public int theMovement(int x) {
		if (x == 1) { // down
			return 1;
		}
		if (x == 2) { // up
			return 2;
		}
		if (x == 3) { // left
			return 3;
		}
		if (x == 4) {
			return 4; // right
		} else
			return 100;
	}

	public void moveHunter(int x) {
		int row = getHunterRow();
		int column = getHunterColumn();
		int movement = theMovement(x);
		if (row == 0 && column == 0) {
			visible[row][column] = new boardPiece();
		}
		if (movement == 1) {
			board[row][column] = new boardPiece();
			if (row == 9) {
				row = -1;
			}
			board[row + 1][column] = new Hunter();

			hunterRow = row + 1;
			hunterColumn = column;
		}
		if (movement == 2) {
			board[row][column] = new boardPiece();
			if (row == 0) {
				row = 10;
			}
			board[row - 1][column] = new Hunter();
			hunterRow = row - 1;
			hunterColumn = column;

		}
		if (movement == 3) {
			board[row][column] = new boardPiece();
			if (column == 0) {
				column = 10;
			}
			board[row][column - 1] = new Hunter();
			hunterRow = row;
			hunterColumn = column - 1;

		}
		if (movement == 4) {
			board[row][column] = new boardPiece();
			if (column == 9) {
				column = -1;
			}
			board[row][column + 1] = new Hunter();

			hunterRow = row;
			hunterColumn = column + 1;

		}
		visibleBoard[hunterRow][hunterColumn] = 1;
		this.setChanged();
		notifyObservers();
		System.out.println(printMap());
	}

	public int hunterAndHazard() {
		int Hrow = hunterRow;
		int HColumn = hunterColumn;
		int value = storeValues[Hrow][HColumn];
		if(value == 2 || value == 5){
			setEndGame(1);
		}
		this.setChanged();
		 notifyObservers();
		return value;
	}

	public boolean collision(int yourRow, int yourColumn) {
		if (yourRow == wumpusRow && yourColumn == wumpusColumn) {
			return true;
		} else
			return false;
	}

	public boolean shootArrow(int x) {
		if (x == 1) { // right
			if (hunterRow == wumpusRow) {
				System.out.println("You've slain the Wumpus! You win!!!!");
				setEndGame(1);
				this.setChanged();
				notifyObservers();
				return true;
			} else
				System.out
						.println("You've missed your shot and died. You Lose.");
			setEndGame(1);
			this.setChanged();
			notifyObservers();
			return false;
		} else if (x == 2) {
			if (hunterColumn == wumpusColumn) {
				System.out.println("You've slain the Wumpus! You win!!!!");
				setEndGame(1);
				this.setChanged();
				notifyObservers();
				return true;
			} else {
				System.out
						.println("You've missed your shot and died. You Lose.");
				setEndGame(1);
				this.setChanged();
				notifyObservers();
				return false;
			}
		} else
			return false;

	}

	public void makeVisible(int row, int col) {
		board[row][col].isVisible = true;
	}

	public String printFinishedMap() {
		String s = "";
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				if (board[i][j].getSymbol().equals("[X]")) {
					s += "[ ]";
					System.out.print("[ ]");
				} else {
					System.out.print(board[i][j].getSymbol());
					s += board[i][j].getSymbol();
				}
			}
			System.out.println();
			s += "\n";
		}
		return s;
	}

	public int[][] getBoard() {
		return storeValues;
	}

	JPanel layoutScreen2() {
		JPanel view = new JPanel();
		view.setLayout(new GridLayout(10, 10));
		view.setSize(500, 500);
		view.setBackground(Color.GRAY);
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				if (visibleBoard[i][j] != 1) {
					view.add(new JLabel(new ImageIcon(boardPiece.getImage2())));
				} else if (i == getHunterRow() && j == getHunterColumn()) {
					view.add(new JLabel(new ImageIcon(hunter.getImage())));
				} else
					view.add(new JLabel(new ImageIcon(visible[i][j].getImage())));
			}
		}
		return view;
	}

	JPanel FinishedlayoutScreen2() {
		JPanel view = new JPanel();
		view.setLayout(new GridLayout(10, 10));
		view.setSize(500, 500);
		view.setBackground(Color.GRAY);
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				if (board[i][j].getSymbol().equals("[X]")) {
					view.add(new JLabel(new ImageIcon(boardPiece.getImage())));
				} else
					view.add(new JLabel(new ImageIcon(board[i][j].getImage())));
			}
		}
		return view;
	}

	public void setEndGame(int x) {
		if (x == 1) {
			endGame = true;
		} else
			endGame = false;
	}

	public boolean getEndGame() {
		return endGame;
	}

}