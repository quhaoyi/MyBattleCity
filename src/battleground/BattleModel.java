package battleground;
import java.awt.Color;
import java.util.*;

import tank.*;


public class BattleModel {
	//this is the size the battlefield
	private int width, height;
	//this is a list of all the enemy tanks
	private List<Tank> enemy;
	//this is a list of all the obstacles on the battlefield
	private List<Obstacle> obstacles;
	//this is a list of all the cannon balls on the battlefield
	private List<Cannonball> cannonballs;
	//this is the tank of the player
	private Tank player;
	//this is total cycles of this program to run
	private int cycles;
	//this boolean will determine whether or not pause
	private boolean pause;
	//this class is in charge of displaying the battlefield and all the stuff in it.
	private BattleView view;
	//this boolean represents whether the player is currently moving
	public boolean[] playerMove;
	//this is the refresh time of the battlefield
	private final int refreshTime = 10;
	
	//the constructor will initiate all the fields
	public BattleModel(int width, int height){
		this.width = width;
		this.height = height;
		this.enemy = new ArrayList<Tank>();
		this.obstacles = new ArrayList<Obstacle>();
		this.cannonballs = new ArrayList<Cannonball>();
		this.player = null;
		this.pause = false;
		this.cycles = 0;
		this.view = null;
		this.playerMove = new boolean[4];
	}
	
	//this method will add the player's tank to the battlefield
	public void addPlayer(Tank t){
		this.player = t;
	}
	
	//this method will add an enemy tank to the battlefield
	public void add(Tank t){
		this.enemy.add(t);
	}
	
	//this method will add an obstacle to the battlefield
	public void add(Obstacle o){
		this.obstacles.add(o);
	}

	//this method will add a cannon ball
	public void add(Cannonball c){
		this.cannonballs.add(c);
	}
	
	//this method will return player's tank
		public Tank getPlayerTank(){
			return this.player;
		}
	
	
	//this method will return a list of all the enemy tanks
	public List<Tank> getTanks(){
		return new ArrayList<Tank>(enemy);
	}
	
	//this method will return a list of all the obstacles
	public List<Obstacle> getObstacles(){
		return new ArrayList<Obstacle>(obstacles);
	}
	
	//this method will return a list of all the cannon balls
	public List<Cannonball> getCannonballs(){
		return cannonballs;
	}
	
	//this method will return the height of the battle field
	public int getHeight(){
		return this.height;
	}
	

	//this method will return the width of the battle field
	public int getWidth(){
		return this.width;
	}

	//this method will set the height of the battle field
	public void setHeight(int height){
		this.height = height;
	}

	//this method will set the width of the battle field
	public void setWidth(int width){
		this.width = width;
	}
	
	
	
	//this method will add a view class to display the battle field
	public void addView(BattleView view){
		this.view = view;
	}
	
	//this method will let the battlefield to pause
	public void pause(){
		this.pause = true;
	}
	
	//this method will let the paused battlefield to resume
	public void resume(){
		this.pause = false;
	}
	
	//this method will restart the battlefield
	public void restart(){
		this.cycles = 0;
	}
	
	//this method will let the program to run
	public void run(int cycles){
		this.cycles = cycles;
		start();
	}
	
	public void run(){
		run(Integer.MAX_VALUE);
	}
	
	//this method will control the movements of every stuff on the battlefield
	public void start() {
		while(cycles != 0){
			if(!pause){
				for(Tank t: enemy){
					if(!t.isDestroyed())
					t.move();
				}
				
				if(playerMove[0]){
					player.setDirection("N");
					player.move();
				}else if(playerMove[1]){
					player.setDirection("W");
					player.move();
				}else if(playerMove[2]){
					player.setDirection("S");
					player.move();
				}else if(playerMove[3]){
					player.setDirection("E");
					player.move();
				}
				
				for(Cannonball c: cannonballs){
					if(c.isValid()){
						c.move();
						c.move();
						c.move();
					}
						
				}
				this.view.notifyView();
				this.cycles --;
			}

			try{Thread.sleep(refreshTime);}
			catch(Exception e){};
		}
	}
}
