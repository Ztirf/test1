package edu.chalmers.tictacbam.model;

public class Player {
	private int playerTag;
	
	private int bombs = 1;
	private int rotateLeft = 1;
	private int rotateRight = 1;
	
	public Player(int playerTag){
		this.setPlayerTag(playerTag);
	}

	public int getPlayerTag() {
		return playerTag;
	}

	public void setPlayerTag(int playerTag) {
		this.playerTag = playerTag;
	}

	public int getBombs() {
		return bombs;
	}

	public void setBombs(int bombs) {
		this.bombs = bombs;
	}

	public int getRotateLeft() {
		return rotateLeft;
	}

	public void setRotateLeft(int rotateLeft) {
		this.rotateLeft = rotateLeft;
	}

	public int getRotateRight() {
		return rotateRight;
	}

	public void setRotateRight(int rotateRight) {
		this.rotateRight = rotateRight;
	}
}
