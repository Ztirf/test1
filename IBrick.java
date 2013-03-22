package edu.chalmers.tictacbam.model;

public interface IBrick {

	public void act();
	public Player getPlayer();
	public String toString();
	public boolean bonusAvailable();
	public void takeBonus();
	public int getX();
	public int getY();
	
}
