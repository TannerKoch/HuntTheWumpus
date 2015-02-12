/**
 * @author James DeVeuve & Tanner Koch
 * TA: Mike Collins
 * Hunt The Wumpus Iteration 1
 * 
 * This class is designed to test the basic functionality of hunt the wumpus.
 */

import static org.junit.Assert.*;

import java.util.Scanner;

import org.junit.Test;


public class testTheWumpus {

	@Test
	public void test() {
		Hunter hunter = new Hunter();
		Board board = new Board();
		board.printMap();
		Scanner j = new Scanner(System.in);
		if (j.next().equals("right")) {
			
		}
		
	}

}
