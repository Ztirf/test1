package edu.chalmers.tictacbam.model;

public class EmptyBrick implements IBrick{
	
	private Player player = null;
	private boolean bonus = false;
	private int x;
	private int y;
	
	public EmptyBrick(int x, int y){
	}
	
	public Player getPlayer(){
		return player;
	}
	
	public String toString(){
		return 0 + "";
	}

	@Override
	public void act() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean bonusAvailable() {
		return bonus;
	}

	@Override
	public void takeBonus() {
		bonus = false;
		
	}

	@Override
	public int getX() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getY() {
		// TODO Auto-generated method stub
		return 0;
	}
}
