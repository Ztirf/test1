package edu.chalmers.tictacbam.model;

public class SimpleBrick implements IBrick{
	
	private Player player;
	private boolean bonus = true;
	private int x;
	private int y;
	
	public SimpleBrick(Player player, int x, int y){
		this.setPlayer(player);
	}
	
	public Player getPlayer(){
		return player;
	}
	
	public void setPlayer(Player player){
		this.player = player;
	}

	public String toString(){
		return player.getPlayerTag() + "";
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
		return x;
	}

	@Override
	public int getY() {
		// TODO Auto-generated method stub
		return y;
	}
}
