import java.io.FileNotFoundException;
import java.util.Scanner;

public class Solution {

	public static void main(String[] args) throws FileNotFoundException {
		// Print out a statement saying to the user to enter a filename.
		System.out.print("Hello and welcome to maze solver. Please insert a file:");
		// Scan user input for file
		Scanner input = new Scanner(System.in);
		String reply = input.nextLine();
		boolean finished = false;

		Maze maze = new Maze(reply);

		while (!finished) {
			// Only mark the current position X, if it not the start
			maze.board[maze.currentPosition[0]][maze.currentPosition[1]] = 4;

			boolean spaceFound = maze.movePosition(0, 4);
			if (spaceFound == false) {
				maze.board[maze.currentPosition[0]][maze.currentPosition[1]] = 5;
				maze.movePosition(4, 5);
				spaceFound = true;
			}

			finished = maze.checkFinish();
		}

	}
}
