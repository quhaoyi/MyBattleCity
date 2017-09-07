package battleground;

import java.awt.Color;
import java.util.List;

import tank.Tank;

public class BattleStuff {
	private int x, y;
	private int height, width;
	private Color color;
	public static final int VELOCITY = 1;
	
	public int getX(){
		return this.getX();
	}
	
	public int getY(){
		return this.getY();
	}
	
	public int getWidth(){
		return this.getWidth();
	}
	
	public int getHeight(){
		return this.getHeight();
	}
	
	public void setColor(Color color){
		this.color = color;
	}
	
	//this method determine whether the stuff on the battlefield t1 will be in contact
	//with t2, if so return false, true otherwise.
	public static boolean isPassable(BattleStuff t2, BattleStuff t1, String direction){
		switch(direction){
		case "W":
			if(t2.getY() < t1.getY() + t1.getHeight() && t2.getY() > t1.getY() - t2.getHeight()){
				if(t2.getX()  == t1.getX() + t1.getWidth()){
					return false;
				}
			}
			break;
		case "E":
			if(t2.getY() < t1.getY() + t1.getHeight() && t2.getY() > t1.getY() - t2.getHeight()){
				if(t2.getX() + t2.getWidth() == t1.getX()){
					return false;
				}
			}
			break;
		case "N":
			if(t2.getX() > t1.getX() - t2.getWidth() && t2.getX() < t1.getX() + t1.getWidth()){
				if(t2.getY() == t1.getY() + t1.getHeight()){
					return false;
				}
			}
			break;
		case "S"  :
			if(t2.getX() > t1.getX() - t2.getWidth() && t2.getX() < t1.getX() + t1.getWidth()){
				if(t2.getY() + t2.getHeight() == t1.getY()){
					return false;
				}
			}
			break;
		}
		return true;
	}
	
	//this function will determine whether the tank can continue to move in the current direction
	//it contains two parts: 
	//1. determine whether this tank will collide with borders.
	//2. determine whether this tank will collide with obstacles in the battlefield
	//3. whether this tank will collide with other tanks
	public static boolean isMovable(BattleStuff bs, String direction, BattleModel field){
		boolean borderPass = true;
		boolean obstaclePass = false;
		List<Obstacle> obstacles = field.getObstacles();
		
		for(Obstacle o : obstacles){
			borderPass &= BattleStuff.isPassable(bs, o, direction);
		}
		for(Tank t : field.getTanks()){
			if(!t.isDestroyed()){
				borderPass &= BattleStuff.isPassable(bs, t, direction);
			}	
		}
		borderPass &= BattleStuff.isPassable(bs, field.getPlayerTank(), direction);
		
		
		switch(direction){
		case "W":
			if(bs.getX() - VELOCITY >= 0){
				obstaclePass = true;
			}
			break;
		case "E":
			if(bs.getX() + bs.getWidth() + VELOCITY <= field.getWidth()){
				obstaclePass = true;
			}
			break;
		case "N":
			if(bs.getY() - VELOCITY >= 0){
				obstaclePass = true;
			}
			break;
		case "S"  :
			if(bs.getY() + bs.getHeight() + VELOCITY <= field.getHeight()){
				obstaclePass = true;
			}
			break;
		}
		return obstaclePass && borderPass;
	}
}
