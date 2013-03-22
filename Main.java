package edu.chalmers.tictacbam.model;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		testBonus();
		
		
	}
	
	
	public static void testBomb1(){
		GameBoard game = GameBoard.getInstance();
		System.out.println(game.getState());
		
		game.startGame();
		System.out.println(game.getState());
		System.out.println("");
		
		
		game.placeBrick(1, 1);
		game.printBoard();
		System.out.println(game.getState());
		System.out.println("");
		
		game.placeBrick(2, 1);
		game.printBoard();
		System.out.println(game.getState());
		System.out.println("");
		
		game.placeBrick(3, 1);
		game.printBoard();
		System.out.println(game.getState());
		System.out.println("");
		
		game.placeBrick(3, 2);
		game.printBoard();
		System.out.println(game.getState());
		System.out.println("");
		
		game.placeBrick(3, 3);
		game.printBoard();
		System.out.println(game.getState());
		System.out.println("");
		
		game.placeBrick(2, 3);
		game.printBoard();
		System.out.println(game.getState());
		System.out.println("");
		
		game.placeBrick(1, 3);
		game.printBoard();
		System.out.println(game.getState());
		System.out.println("");
		
		game.placeBrick(1, 2);
		game.printBoard();
		System.out.println(game.getState());
		System.out.println("");
		
		game.placeBomb(2, 2);
		game.printBoard();
		System.out.println(game.getState());
		System.out.println("");
	}
	
	public static void testBomb2(){
		GameBoard game = GameBoard.getInstance();
		System.out.println(game.getState());
		
		game.startGame();
		System.out.println(game.getState());
		System.out.println("");
		
		
		game.placeBrick(4, 3);
		game.printBoard();
		System.out.println(game.getState());
		System.out.println("");
		
		game.placeBrick(3, 3);
		game.printBoard();
		System.out.println(game.getState());
		System.out.println("");
		
		game.placeBrick(3, 4);
		game.printBoard();
		System.out.println(game.getState());
		System.out.println("");
		
		game.placeBomb(4, 4);
		game.printBoard();
		System.out.println(game.getState());
		System.out.println("");
		
	}
	
	public static void testBomb3(){
		GameBoard game = GameBoard.getInstance();
		System.out.println(game.getState());
		
		game.startGame();
		System.out.println(game.getState());
		System.out.println("");
		
		
		game.placeBrick(1, 0);
		game.printBoard();
		System.out.println(game.getState());
		System.out.println("");
		
		game.placeBrick(1, 1);
		game.printBoard();
		System.out.println(game.getState());
		System.out.println("");
		
		game.placeBrick(2, 1);
		game.printBoard();
		System.out.println(game.getState());
		System.out.println("");
		
		game.placeBrick(3, 1);
		game.printBoard();
		System.out.println(game.getState());
		System.out.println("");
		
		game.placeBrick(3, 0);
		game.printBoard();
		System.out.println(game.getState());
		System.out.println("");
		
		game.placeBomb(2, 0);
		game.printBoard();
		System.out.println(game.getState());
		System.out.println("");
		
	}
	
	
	public static void testRotate1(){
		GameBoard game = GameBoard.getInstance();
		System.out.println(game.getState());
		
		game.startGame();
		System.out.println(game.getState());
		System.out.println("");
		
		
		game.placeBrick(1, 1);
		game.printBoard();
		System.out.println(game.getState());
		System.out.println("");
		
		game.placeBrick(2, 1);
		game.printBoard();
		System.out.println(game.getState());
		System.out.println("");
		
		game.placeBrick(3, 1);
		game.printBoard();
		System.out.println(game.getState());
		System.out.println("");
		
		game.placeRotate("RIGHT", 2, 2);
		game.printBoard();
		System.out.println(game.getState());
		System.out.println("");
		
	}
	
	public static void testRotate2(){
		GameBoard game = GameBoard.getInstance();
		System.out.println(game.getState());
		
		game.startGame();
		System.out.println(game.getState());
		System.out.println("");
		
		
		game.placeBrick(2, 1);
		game.printBoard();
		System.out.println(game.getState());
		System.out.println("");
		
		game.placeBrick(3, 1);
		game.printBoard();
		System.out.println(game.getState());
		System.out.println("");
		
		game.placeBrick(3, 0);
		game.printBoard();
		System.out.println(game.getState());
		System.out.println("");
		
		game.placeRotate("RIGHT", 2, 0);
		game.printBoard();
		System.out.println(game.getState());
		System.out.println("");
		
	}
	
	public static void testRotate3(){
		GameBoard game = GameBoard.getInstance();
		System.out.println(game.getState());
		
		game.startGame();
		System.out.println(game.getState());
		System.out.println("");
		
		
		game.placeBrick(3, 4);
		game.printBoard();
		System.out.println(game.getState());
		System.out.println("");
		
		game.placeBrick(3, 3);
		game.printBoard();
		System.out.println(game.getState());
		System.out.println("");
		
		game.placeBrick(4, 3);
		game.printBoard();
		System.out.println(game.getState());
		System.out.println("");
		
		game.placeRotate("RIGHT", 4, 4);
		game.printBoard();
		System.out.println(game.getState());
		System.out.println("");
		
	}
	
	public static void testBonus(){
		GameBoard game = GameBoard.getInstance();
		System.out.println(game.getState());
		
		game.startGame();
		System.out.println(game.getState());
		System.out.println("");
		
		
		game.placeBrick(0, 0);
		game.printBoard();
		System.out.println(game.getState());
		System.out.println("");
		
		game.placeBrick(3, 1);
		game.printBoard();
		System.out.println(game.getState());
		System.out.println("");
		
		game.placeBrick(2, 0);
		game.printBoard();
		System.out.println(game.getState());
		System.out.println("");
		
		game.placeBrick(4, 0);
		game.printBoard();
		System.out.println(game.getState());
		System.out.println("");
		
		game.placeBrick(1, 0);
		game.printBoard();
		System.out.println(game.getState());
		System.out.println("");
		
		game.placeBrick(4, 4);
		game.printBoard();
		System.out.println(game.getState());
		System.out.println("");
		
		game.placeBrick(2, 1);
		game.printBoard();
		System.out.println(game.getState());
		System.out.println("");
		
		System.out.println(game.getPlayerOne().getBombs());
		
	}

}
