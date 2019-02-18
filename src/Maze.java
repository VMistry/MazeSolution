import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Maze {

	int[] gridSize;
	String startPosition;
	int[] endPosition;
	int[] currentPosition;
	int[][] board;

	public Maze(String reply) throws FileNotFoundException {
		// Look for and read the file which was chosen by the user
		File file = new File(reply);
		Scanner sc = new Scanner(file);

		// Extract the size, start point and end point.
		gridSize = positionArray(sc.nextLine());
		startPosition = sc.nextLine();
		currentPosition = positionArray(startPosition);
		endPosition = positionArray(sc.nextLine());
		String[] strBoard = new String[gridSize[0]];
		board = new int[gridSize[1]][gridSize[0]];

		for (int index = 0; index < gridSize[1]; index++) {
			strBoard = sc.nextLine().split(" ");
			for (int element = 0; element < gridSize[0]; element++) {
				board[index][element] = Integer.parseInt(strBoard[element]);
			}
		}

		board[currentPosition[1]][currentPosition[0]] = 2;
		board[endPosition[1]][endPosition[0]] = 3;
	}

	public int[] getEndPosition() {
		return endPosition;
	}

	public int[] getCurrentPosition() {
		return currentPosition;
	}

	public boolean setCurrentPosition(int arrayIndex, int value, int newMark) {
		currentPosition[arrayIndex] = currentPosition[arrayIndex] + value;
		board[currentPosition[0]][currentPosition[1]] = newMark;
		return true;
	}

	public boolean movePosition(int targetPosition, int newMark) {
		boolean foundSpace = false;
		if (currentPosition[0] == 0) {
			if (board[currentPosition[0] + (gridSize[0] - 1)][currentPosition[1]] == targetPosition) {
				foundSpace = setCurrentPosition(0, (gridSize[0] - 1), newMark);
			} else if (board[currentPosition[0]][currentPosition[1] + 1] == targetPosition) {
				foundSpace = setCurrentPosition(1, 1, newMark);
			} else if (board[currentPosition[0] + 1][currentPosition[1]] == targetPosition) {
				foundSpace = setCurrentPosition(0, 1, newMark);
			} else if (board[currentPosition[0]][currentPosition[1] - 1] == targetPosition) {
				foundSpace = setCurrentPosition(1, -1, newMark);
			}
		}

		else if (currentPosition[1] == 0) {
			if (board[currentPosition[0]][currentPosition[1] + (gridSize[1] - 1)] == targetPosition) {
				foundSpace = setCurrentPosition(1, (gridSize[0] - 1), newMark);
			} else if (board[currentPosition[0] + 1][currentPosition[1]] == targetPosition) {
				foundSpace = setCurrentPosition(0, 1, newMark);
			} else if (board[currentPosition[0]][currentPosition[1] + 1] == targetPosition) {
				foundSpace = setCurrentPosition(1, 1, newMark);
			} else if (board[currentPosition[0] - 1][currentPosition[1]] == targetPosition) {
				foundSpace = setCurrentPosition(0, -1, newMark);
			}
		}

		else if (currentPosition[0] == (gridSize[1] - 1)) {
			if (board[currentPosition[0] - (gridSize[0] - 1)][currentPosition[1]] == targetPosition) {
				foundSpace = setCurrentPosition(0, -(gridSize[0] - 1), newMark);
			} else if (board[currentPosition[0]][currentPosition[1] + 1] == targetPosition) {
				foundSpace = setCurrentPosition(1, 1, newMark);
			} else if (board[currentPosition[0] - 1][currentPosition[1]] == targetPosition) {
				foundSpace = setCurrentPosition(0, -1, newMark);
			} else if (board[currentPosition[0]][currentPosition[1] - 1] == targetPosition) {
				foundSpace = setCurrentPosition(1, -1, newMark);
			}
		}

		else if (currentPosition[1] == (gridSize[0] - 1)) {
			if (board[currentPosition[0]][currentPosition[1] - (gridSize[1] - 1)] == targetPosition) {
				foundSpace = setCurrentPosition(1, -(gridSize[0] - 1), newMark);
			} else if (board[currentPosition[0] + 1][currentPosition[1]] == targetPosition) {
				foundSpace = setCurrentPosition(0, 1, newMark);
			} else if (board[currentPosition[0]][currentPosition[1] - 1] == targetPosition) {
				foundSpace = setCurrentPosition(1, -1, newMark);
			} else if (board[currentPosition[0] - 1][currentPosition[1]] == targetPosition) {
				foundSpace = setCurrentPosition(0, -1, newMark);
			}
		}

		// If it finds a 0/5 in any of the North, East South, West direction, move one
		// place.
		else if (board[currentPosition[0]][currentPosition[1] + 1] == targetPosition) {
			foundSpace = setCurrentPosition(1, 1, newMark);
		} else if (board[currentPosition[0] + 1][currentPosition[1]] == targetPosition) {
			foundSpace = setCurrentPosition(0, 1, newMark);
		} else if (board[currentPosition[0]][currentPosition[1] - 1] == targetPosition) {
			foundSpace = setCurrentPosition(1, -1, newMark);
		} else if (board[currentPosition[0] - 1][currentPosition[1]] == targetPosition) {
			foundSpace = setCurrentPosition(0, -1, newMark);
		}
		return foundSpace;
	}

	public boolean checkFinish() {
		boolean finished = false;
		int[] start = positionArray(startPosition);

		if (currentPosition[0] == 0) {
			if (board[currentPosition[0]][currentPosition[1] + 1] == 3
					|| board[currentPosition[0]][currentPosition[1] - 1] == 3
					|| board[currentPosition[0] + 1][currentPosition[1]] == 3
					|| board[currentPosition[0] + (gridSize[0] - 1)][currentPosition[1]] == 3) {
				board[start[1]][start[0]] = 2;
				System.out.println("Solved Maze");
				printBoard();
				finished = true;
			}

			else if ((board[currentPosition[0]][currentPosition[1] + 1] == 5
					|| board[currentPosition[0]][currentPosition[1] + 1] == 1)
					&& (board[currentPosition[0]][currentPosition[1] - 1] == 5
							|| board[currentPosition[0]][currentPosition[1] - 1] == 1)
					&& (board[currentPosition[0] + 1][currentPosition[1]] == 5
							|| board[currentPosition[0] + 1][currentPosition[1]] == 1)
					&& (board[currentPosition[0] + (gridSize[0] - 1)][currentPosition[1]] == 5
							|| board[currentPosition[0] + (gridSize[0] - 1)][currentPosition[1]] == 1)) {
				System.out.println("I am unable to solve the maze");
				finished = true;
			}
		}

		else if (currentPosition[1] == 0) {
			if (board[currentPosition[0]][currentPosition[1] + 1] == 3
					|| board[currentPosition[0]][currentPosition[1] + (gridSize[1] - 1)] == 3
					|| board[currentPosition[0] + 1][currentPosition[1]] == 3
					|| board[currentPosition[0] - 1][currentPosition[1]] == 3) {
				board[start[1]][start[0]] = 2;
				System.out.println("Solved Maze");
				printBoard();
				finished = true;
			}

			else if ((board[currentPosition[0]][currentPosition[1] + 1] == 5
					|| board[currentPosition[0]][currentPosition[1] + 1] == 1)
					&& (board[currentPosition[0]][currentPosition[1] + (gridSize[1] - 1)] == 5
							|| board[currentPosition[0]][currentPosition[1] + (gridSize[1] - 1)] == 1)
					&& (board[currentPosition[0] + 1][currentPosition[1]] == 5
							|| board[currentPosition[0] + 1][currentPosition[1]] == 1)
					&& (board[currentPosition[0] - 1][currentPosition[1]] == 5
							|| board[currentPosition[0] - 1][currentPosition[1]] == 1)) {
				System.out.println("I am unable to solve the maze");
				finished = true;
			}
		}

		else if (currentPosition[0] == (gridSize[0] - 1)) {
			if (board[currentPosition[0]][currentPosition[1] + 1] == 3
					|| board[currentPosition[0]][currentPosition[1] - 1] == 3
					|| board[currentPosition[0] - (gridSize[0] - 1)][currentPosition[1]] == 3
					|| board[currentPosition[0] - 1][currentPosition[1]] == 3) {
				board[start[1]][start[0]] = 2;
				System.out.println("Solved Maze");
				printBoard();
				finished = true;
			}

			else if ((board[currentPosition[0]][currentPosition[1] + 1] == 5
					|| board[currentPosition[0]][currentPosition[1] + 1] == 1)
					&& (board[currentPosition[0]][currentPosition[1] - 1] == 5
							|| board[currentPosition[0]][currentPosition[1] - 1] == 1)
					&& (board[currentPosition[0] - (gridSize[0] - 1)][currentPosition[1]] == 5
							|| board[currentPosition[0] - (gridSize[0] - 1)][currentPosition[1]] == 1)
					&& (board[currentPosition[0] - 1][currentPosition[1]] == 5
							|| board[currentPosition[0] - 1][currentPosition[1]] == 1)) {
				System.out.println("I am unable to solve the maze");
				finished = true;
			}
		}

		else if (currentPosition[1] == (gridSize[1] - 1)) {
			if (board[currentPosition[0]][currentPosition[1] - (gridSize[1] - 1)] == 3
					|| board[currentPosition[0]][currentPosition[1] - 1] == 3
					|| board[currentPosition[0] + 1][currentPosition[1]] == 3
					|| board[currentPosition[0] - 1][currentPosition[1]] == 3) {
				board[start[1]][start[0]] = 2;
				System.out.println("Solved Maze");
				printBoard();
				finished = true;
			}

			else if ((board[currentPosition[0]][currentPosition[1] - (gridSize[1] - 1)] == 5
					|| board[currentPosition[0]][currentPosition[1] - (gridSize[1] - 1)] == 1)
					&& (board[currentPosition[0]][currentPosition[1] - 1] == 5
							|| board[currentPosition[0]][currentPosition[1] - 1] == 1)
					&& (board[currentPosition[0] + 1][currentPosition[1]] == 5
							|| board[currentPosition[0] + 1][currentPosition[1]] == 1)
					&& (board[currentPosition[0] - 1][currentPosition[1]] == 5
							|| board[currentPosition[0] - 1][currentPosition[1]] == 1)) {
				System.out.println("I am unable to solve the maze");
				finished = true;
			}
		}

		else {
			if (board[currentPosition[0]][currentPosition[1] + 1] == 3
					|| board[currentPosition[0]][currentPosition[1] - 1] == 3
					|| board[currentPosition[0] + 1][currentPosition[1]] == 3
					|| board[currentPosition[0] - 1][currentPosition[1]] == 3) {
				board[start[1]][start[0]] = 2;
				System.out.println("Solved Maze");
				printBoard();
				finished = true;
			}

			else if ((board[currentPosition[0]][currentPosition[1] + 1] == 5
					|| board[currentPosition[0]][currentPosition[1] + 1] == 1)
					&& (board[currentPosition[0]][currentPosition[1] - 1] == 5
							|| board[currentPosition[0]][currentPosition[1] - 1] == 1)
					&& (board[currentPosition[0] + 1][currentPosition[1]] == 5
							|| board[currentPosition[0] + 1][currentPosition[1]] == 1)
					&& (board[currentPosition[0] - 1][currentPosition[1]] == 5
							|| board[currentPosition[0] - 1][currentPosition[1]] == 1)) {
				System.out.println("I am unable to solve the maze");
				finished = true;
			}
		}

		return finished;
	}

	public int[] positionArray(String input) {
		int[] array = new int[2];
		String[] strArray = input.split(" ");

		for (int index = 0; index < strArray.length; index++) {
			array[index] = Integer.parseInt(strArray[index]);
		}
		return array;
	}

	public void printBoard() {
		for (int indexX = 0; indexX < board.length; indexX++) {
			for (int indexY = 0; indexY < board[0].length; indexY++) {
				// 0 is space
				// 1 is wall
				// 2 is start
				// 3 is end
				// 4 is path
				// 5 is removed path
				switch (board[indexX][indexY]) {
				case 1:
					System.out.print("# ");
					break;
				case 2:
					System.out.print("S ");
					break;
				case 3:
					System.out.print("E ");
					break;
				case 4:
					System.out.print("X ");
					break;
				default:
					System.out.print("  ");
					break;
				}
			}
			System.out.println("");
		}
	}

}
