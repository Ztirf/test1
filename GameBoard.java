package edu.chalmers.tictacbam.model;

import java.util.LinkedList;
import java.util.List;

public class GameBoard {

	//
	// GAMESTATES
	//
	public static enum State {
		MENU, OPTION_MENU, GAME_START, PLAYER_ONE_TURN, PLAYER_TWO_TURN, PLAYER_ONE_WON, PLAYER_TWO_WON, GAME_ENDED;
	}

	private State state = State.MENU;

	//
	// VARIABLES
	//

	// The Players
	private Player playerOne;
	private Player playerTwo;

	//Russian tic tac toe rule
	private boolean secondBrick = false;
	private SimpleBrick firstBrick = null;
	private int firstX = -1;
	private int firstY = -1;
	
	// THE BOARD
	private IBrick[][] board = new IBrick[5][5];

	//
	// CONSTRUCTOR STUFF
	//

	// GAMEBOARD SINGLETON
	private static GameBoard gameBoard = null;

	/**
	 * Private constructor
	 */
	private GameBoard() {

	}

	/**
	 * Gets the GameBoard singleton
	 * 
	 * @return GameBoard, Singleton
	 */
	public static GameBoard getInstance() {
		if (gameBoard == null) {
			gameBoard = new GameBoard();
		}
		return gameBoard;
	}

	//
	// GAMESTUFF
	//

	public void startGame() {
		playerOne = new Player(1);
		playerTwo = new Player(2);
		
		//RUSSIAN
		secondBrick = false;
		firstBrick = null;
		firstX = -1;
		firstY = -1;
		

		state = State.PLAYER_ONE_TURN;

		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				board[i][j] = new EmptyBrick(i,j);
			}
		}
	}

	public void endGame() {

	}

	/**
	 * Places a brick at a given location.
	 * 
	 * @param x
	 * @param y
	 */
	public boolean placeBrick(int x, int y) {

		if (board[x][y].getPlayer() != null) {
			return false;
		}

		SimpleBrick temp;
		
		if(secondBrick == false){
			return russianRule(x,y);
		}
		else{
		
			if (state == State.PLAYER_ONE_TURN) {
				temp = new SimpleBrick(playerOne, x, y);
				board[x][y] = temp;
				bonusCheck(temp);
				state = State.PLAYER_TWO_TURN;
			} else if (state == State.PLAYER_TWO_TURN) {
				temp = new SimpleBrick(playerTwo, x, y);
				board[x][y] = temp;
				bonusCheck(temp);
				state = State.PLAYER_ONE_TURN;
			}

		}
		
		 
		
		if (winCheck() == true) {
			if(state == State.PLAYER_TWO_TURN){
				state = State.PLAYER_ONE_WON;
			}
			else{
				state = State.PLAYER_TWO_WON;
			}
			return true;
		}

		return true;

	}

	/**
	 * Places a bomb that removes the surrounding bricks
	 * @param x, x coordinate for the bomb
	 * @param y, y coordinate for the bomb
	 * @return true if bomb was successfully placed
	 */
	public boolean placeBomb(int x, int y) {
		
		if (board[x][y].getPlayer() != null) {
			return false;
		}

		if (state == State.PLAYER_ONE_TURN) {
			if (playerOne.getBombs() <= 0) {
				return false;
			}
		} 
		else if (state == State.PLAYER_TWO_TURN) {
			if (playerTwo.getBombs() <= 0) {
				return false;
			}
		}
		
		
		int[] xs = this.getSurroundingX(x);
		int[] ys = this.getSurroundingY(y);
		
		for(int i = 0; i < 8; i++){
			
			if(xs[i] < 0 || xs[i] > 4 || ys[i] < 0 || ys[i] > 4){
				
			}
			else{
				board[xs[i]][ys[i]] = new EmptyBrick(xs[i],ys[i]); 
			}
			
		}
		
		if(state == State.PLAYER_ONE_TURN){
			playerOne.setBombs(playerOne.getBombs()-1);
		}
		else if(state == State.PLAYER_TWO_TURN){
			playerTwo.setBombs(playerTwo.getBombs()-1);
		}
		
		return placeBrick(x,y);
	}
	
	/**
	 * Rotation brick
	 * @param direction LEFT or RIGHT
	 * @param x coordinate
	 * @param y coordinate
	 * @return true if brick was placed
	 */
	public boolean placeRotate(String direction, int x, int y) {
		
		if (board[x][y].getPlayer() != null) {
			return false;
		}
		
		if(direction == "RIGHT" || direction == "LEFT"){}
		else{
			return false;
		}
		
		if(direction == "RIGHT"){
			if (state == State.PLAYER_ONE_TURN) {
				if (playerOne.getRotateRight() <= 0) {
					return false;
				}
			} 
			else if (state == State.PLAYER_TWO_TURN) {
				if (playerTwo.getRotateRight() <= 0) {
					return false;
				}
			}
		}
		else if(direction == "LEFT"){
			if (state == State.PLAYER_ONE_TURN) {
				if (playerOne.getRotateLeft() <= 0) {
					return false;
				}
			} 
			else if (state == State.PLAYER_TWO_TURN) {
				if (playerTwo.getRotateLeft() <= 0) {
					return false;
				}
			}
		}
		
		int[] xs = this.getSurroundingX(x);
		int[] ys = this.getSurroundingY(y);
			
		IBrick[][] kopia = this.getBoard();
		
		for (int i = 0; i < 8; i++) {

			if (xs[i] < 0 || xs[i] > 4 || ys[i] < 0 || ys[i] > 4) {

			} else {
				board[xs[i]][ys[i]] = new EmptyBrick(xs[i], ys[i]);
			}

		}
		
		
		//PLACERAR UT NYA POSITIONER
		
		//ROTATELEFT
		if(direction == "LEFT"){
			rotateHelper(kopia, xs[0], ys[0], xs[6], ys[6]);	
			rotateHelper(kopia, xs[1], ys[1], xs[7], ys[7]);		
			rotateHelper(kopia, xs[2], ys[2], xs[0], ys[0]);		
			rotateHelper(kopia, xs[3], ys[3], xs[1], ys[1]);	
			rotateHelper(kopia, xs[4], ys[4], xs[2], ys[2]);	
			rotateHelper(kopia, xs[5], ys[5], xs[3], ys[3]);	
			rotateHelper(kopia, xs[6], ys[6], xs[4], ys[4]);		
			rotateHelper(kopia, xs[7], ys[7], xs[5], ys[5]);
			
			if(state == State.PLAYER_ONE_TURN){
				playerOne.setRotateLeft(playerOne.getRotateLeft()-1);
			}
			else if(state == State.PLAYER_TWO_TURN){
				playerTwo.setRotateLeft(playerTwo.getRotateLeft()-1);
			}
			
		}
		
		//ROTATERIGHT
		if(direction == "RIGHT"){
			rotateHelper(kopia, xs[0], ys[0], xs[2], ys[2]);	
			rotateHelper(kopia, xs[1], ys[1], xs[3], ys[3]);		
			rotateHelper(kopia, xs[2], ys[2], xs[4], ys[4]);		
			rotateHelper(kopia, xs[3], ys[3], xs[5], ys[5]);	
			rotateHelper(kopia, xs[4], ys[4], xs[6], ys[6]);	
			rotateHelper(kopia, xs[5], ys[5], xs[7], ys[7]);	
			rotateHelper(kopia, xs[6], ys[6], xs[0], ys[0]);		
			rotateHelper(kopia, xs[7], ys[7], xs[1], ys[1]);
			
			if(state == State.PLAYER_ONE_TURN){
				playerOne.setRotateRight(playerOne.getRotateRight()-1);
			}
			else if(state == State.PLAYER_TWO_TURN){
				playerTwo.setRotateRight(playerTwo.getRotateRight()-1);
			}
			
		}
		
		
		return placeBrick(x,y);
	}

	
	
	
	private void bonusCheck(IBrick brick) {
		
		Player temp = brick.getPlayer();
		
		List<IBrick> bonusBricks = new LinkedList<IBrick>();
		LinkedList<IBrick> queue = new LinkedList<IBrick>();
		
		bonusBricks.add(brick);
		queue.add(brick);
		
		int x, y;
		
		IBrick tempB;
		
		while(queue.size() > 0){
			
			tempB = queue.getFirst();
			x = tempB.getX();
			y = tempB.getY();
			
			int[] xs = this.getSurroundingX(x);
			int[] ys = this.getSurroundingX(y);
			
			for(int i = 0; i < xs.length; i++){
				if(xs[i] < 5 && xs[i] >= 0 && ys[i] < 5 && ys[i] >= 0){
					
					if(board[xs[i]][ys[i]].bonusAvailable() && board[xs[i]][ys[i]].getPlayer() == temp && !bonusBricks.contains(board[xs[i]][ys[i]])){
						bonusBricks.add(board[xs[i]][ys[i]]);
						queue.add(board[xs[i]][ys[i]]);
						queue.remove(tempB);
						System.out.println(bonusBricks.size());
					}				
				}
				
			}
			if(queue.contains(tempB)){
				queue.remove(tempB);
			}
		}
		
		if(bonusBricks.size() >= 4){
			for(IBrick b: bonusBricks){
				b.takeBonus();
			}
			
			temp.setBombs(temp.getBombs() + 1);
		}
		
	}

	private boolean winCheck() {

		IBrick[][] kopia = getBoard();

		// Check Columns
		for (int i = 0; i < 5; i++) {
			if (checkRow(kopia[i]) == true) {
				return true;
			}
		}

		// Check Rows
		IBrick[] temp = new IBrick[5];
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {

				// TODO
				temp[j] = kopia[j][i];

			}

			if (checkRow(temp) == true) {
				return true;
			}
		}

		IBrick[] Diagonal1 = { kopia[0][0], kopia[1][1], kopia[2][2], kopia[3][3], kopia[4][4] };
		IBrick[] Diagonal2 = { kopia[1][0], kopia[2][1], kopia[3][2], kopia[4][3] };
		IBrick[] Diagonal3 = { kopia[0][1], kopia[1][2], kopia[2][3], kopia[3][4] };

		IBrick[] Diagonal4 = { kopia[0][4], kopia[1][3], kopia[2][2], kopia[3][1], kopia[4][0] };
		IBrick[] Diagonal5 = { kopia[0][3], kopia[1][2], kopia[2][1], kopia[3][0] };
		IBrick[] Diagonal6 = { kopia[1][4], kopia[2][3], kopia[3][2], kopia[4][1] };

		IBrick[][] diagonals = { Diagonal1, Diagonal2, Diagonal3, Diagonal4, Diagonal5, Diagonal6 };

		// Check Diagonals
		for (int i = 0; i < 6; i++) {
			if (checkRow(diagonals[i]) == true) {
				return true;
			}
		}

		return false;
	}

	private boolean checkRow(IBrick[] row) {

		Player lastPlayer = null;
		int count = 0;

		for (int i = 0; i < row.length; i++) {

			if (row[i].getPlayer() == lastPlayer && row[i].getPlayer() != null) {
				lastPlayer = row[i].getPlayer();
				count++;
			} else {
				lastPlayer = row[i].getPlayer();
				count = 1;
			}

			if (count == 4)
				return true;

		}

		return false;
	}

	//
	// GETTERS AND SETTERS
	//

	/**
	 * Method for returning the current gamestate
	 * @return State, gamestate
	 */
	public State getState() {
		return state;
	}

	/**
	 * Returns a clone of the gameboard.
	 * @return GameBoard
	 */
	public IBrick[][] getBoard() {
		
		IBrick[][] kopia = new IBrick[5][5];
		
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				if(board[i][j].getPlayer() == playerOne){
					kopia[i][j] = new SimpleBrick(playerOne, i, j);
				}
				else if(board[i][j].getPlayer() == playerTwo){
					kopia[i][j] = new SimpleBrick(playerTwo, i, j);
				}
				else{
					kopia[i][j] = new EmptyBrick(i, j);
				}			
			}
		}
		
		
		return kopia;
	}

	public Player getPlayerOne(){
		return playerOne;
	}
	
	public Player getPlayerTwo(){
		return playerTwo;
	}
	
	//
	// OTHER UTILITY
	//
	
	
	private boolean russianRule(int x , int y) {

		
		SimpleBrick temp = null;
		Player tempP = null;

		if (firstBrick == null) {
			if (state == State.PLAYER_ONE_TURN) {
				temp = new SimpleBrick(playerOne, x, y);
				board[x][y] = temp;
				state = State.PLAYER_TWO_TURN;
			} else if (state == State.PLAYER_TWO_TURN) {
				temp = new SimpleBrick(playerTwo, x ,y);
				board[x][y] = temp;
				state = State.PLAYER_ONE_TURN;
			}
			firstX = x;
			firstY = y;
			firstBrick = temp;
			return true;
		} 
		else if (secondBrick == false) {
			tempP = firstBrick.getPlayer();
			if (tempP == playerOne && state == State.PLAYER_ONE_TURN) {
				temp = new SimpleBrick(playerOne, x, y);

				int[] xs = getSurroundingX(x);
				int[] ys = getSurroundingY(y);

				for (int i = 0; i < xs.length; i++) {
					if (firstX == xs[i] && firstY == ys[i]) {
						return false;
					}
				}

				board[x][y] = temp;
				secondBrick = true;
				state = State.PLAYER_TWO_TURN;
				return true;
			} 
			else if (tempP == playerTwo && state == State.PLAYER_TWO_TURN) {
				temp = new SimpleBrick(playerTwo, x, y);

				int[] xs = getSurroundingX(x);
				int[] ys = getSurroundingY(y);

				for (int i = 0; i < xs.length; i++) {
					if (firstX == xs[i] && firstY == ys[i]) {
						return false;
					}
				}
				board[x][y] = temp;
				secondBrick = true;
				state = State.PLAYER_ONE_TURN;
				return true;
			}
			else{
				if (state == State.PLAYER_ONE_TURN) {
					temp = new SimpleBrick(playerOne, x, y);
					board[x][y] = temp;
					state = State.PLAYER_TWO_TURN;
				} else if (state == State.PLAYER_TWO_TURN) {
					temp = new SimpleBrick(playerTwo, x, y);
					board[x][y] = temp;
					state = State.PLAYER_ONE_TURN;
				}
			}

		}

		return true;
		
		
	}
	
	private void rotateHelper(IBrick[][] copy, int oldX, int oldY, int newX, int newY){
		
		if (oldX < 0 || oldX > 4 || oldY < 0 || oldY > 4) {
			return;
		}
		
		if (newX < 0 || newX > 4 || newY < 0 || newY > 4) {
			return;
		}
		
		board[newX][newY] = copy[oldX][oldY];
		
	}
	
	/**
	 * GETS SURROUNDING
	 * @param i
	 * @return
	 */
	private int[] getSurroundingX(int x) {
		// Exempel: X = 2, Y = 2

		int[] xs = new int[8];
		
		// X = 1, Y = 1
		xs[0] = x - 1;

		// X = 2, Y = 1
		xs[1] = x;

		// X = 3, Y = 1
		xs[2] = x + 1;

		// X = 3, Y = 2
		xs[3] = x + 1;

		// X = 3, Y = 3
		xs[4] = x + 1;

		// X = 2, Y = 3
		xs[5] = x;

		// X = 1, Y = 3
		xs[6] = x - 1;

		// X = 1, Y = 2
		xs[7] = x - 1;
		
		return xs;
	}

	private int[] getSurroundingY(int y) {
		// Exempel: X = 2, Y = 2

		int[] ys = new int[8];
		
		// X = 1, Y = 1
		ys[0] = y - 1;

		// X = 2, Y = 1
		ys[1] = y - 1;

		// X = 3, Y = 1
		ys[2] = y - 1;

		// X = 3, Y = 2
		ys[3] = y;

		// X = 3, Y = 3
		ys[4] = y + 1;

		// X = 2, Y = 3
		ys[5] = y + 1;

		// X = 1, Y = 3
		ys[6] = y + 1;

		// X = 1, Y = 2
		ys[7] = y;
		
		return ys;
	}
	

	/**
	 * Prints the board state
	 */
	public void printBoard() {

		IBrick[][] kopia = getBoard();

		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				System.out.print(kopia[j][i].toString() + " ");
			}
			System.out.println("");
		}
	}

}
