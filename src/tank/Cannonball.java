package tank;

import java.awt.Color;
import java.awt.Graphics;
import battleground.BattleModel;
import battleground.BattleStuff;

//the implementation of this class is similar to the tank class, please find 
//detailed comment there.
public class Cannonball extends BattleStuff{
	private int x,y;
	private int width;
	private int height;
	private String direction;
	private BattleModel field;
	private boolean isEnemy;
	private boolean isValid = true;
	
	public Cannonball(int x, int y, int width, int height, int velocity, String direction, BattleModel field, boolean isEnemy){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.direction = direction;
		this.field = field;
		this.isEnemy = isEnemy;
	}
	//this function returns the x coordinate of the top left corner of the tank
	public int getX(){
		return this.x;
	}
	//this function returns the y coordinate of the top left corner of the tank
	public int getY(){
		return this.y;
	}
	//this function returns the width
	public int getWidth(){
		return this.width;
	}
	//this function returns the height
	public int getHeight(){
		return this.height;
	}
	
	public void move(){
		if(BattleStuff.isMovable(this, direction, field) && isValid){
			switch(direction){
			case "W":
				this.x -= VELOCITY;
				break;
			case "E":
				this.x += VELOCITY;
				break;
			case "N":
				this.y -= VELOCITY;
				break;
			case "S":
				this.y += VELOCITY;
				break;
				
			}
		}else if(isValid){
			isValid = false;
			for(Tank t : field.getTanks()){
				if(!t.isDestroyed() && !BattleStuff.isPassable(this, t, direction) && this.isEnemy() != t.isEnemy()){
					t.isHit();
				}
				
			}
		}
	}
	
//	public boolean isMovable(String direction){
//		boolean borderPass = true;
//		boolean obstaclePass = false;
//		List<Obstacle> obstacles = field.getObstacles();
//		
//		for(Obstacle o : obstacles){
//			borderPass &= BattleStuff.isPassable(this, o, direction);
//		}
//		for(Tank t : field.getTanks()){
//			if(!t.isDestroyed()){
//				borderPass &= BattleStuff.isPassable(this, t, direction);
//			}	
//		}
//		borderPass &= BattleStuff.isPassable(this, field.getPlayerTank(), direction);
//		
//		
//		switch(direction){
//		case "W":
//			if(this.x - VELOCITY >= 0){
//				obstaclePass = true;
//			}
//			break;
//		case "E":
//			if(this.x + width + VELOCITY <= field.getWidth()){
//				obstaclePass = true;
//			}
//			break;
//		case "N":
//			if(this.y - VELOCITY >= 0){
//				obstaclePass = true;
//			}
//			break;
//		case "S"  :
//			if(this.y + height + VELOCITY <= field.getHeight()){
//				obstaclePass = true;
//			}
//			break;
//		}
//		return obstaclePass && borderPass;
//	}
	
//	public boolean isPassable(BattleStuff t, String direction){
//		switch(direction){
//		case "W":
//			if(t.getY() < this.y + this.height && t.getY() > this.y - t.getHeight()){
//				if(t.getX()  == this.x + this.width){
//					return false;
//				}
//			}
//			break;
//		case "E":
//			if(t.getY() < this.y + this.height && t.getY() > this.y - t.getHeight()){
//				if(t.getX() + t.getWidth() == this.x){
//					return false;
//				}
//			}
//			break;
//		case "N":
//			if(t.getX() > this.x - t.getWidth() && t.getX() < this.x + this.width){
//				if(t.getY() == this.y + this.height){
//					return false;
//				}
//			}
//			break;
//		case "S"  :
//			if(t.getX() > this.x - t.getWidth() && t.getX() < this.x + this.width){
//				if(t.getY() + t.getHeight() == this.y){
//					return false;
//				}
//			}
//			break;
//		}
//		return true;
//	}
	
	public boolean isEnemy(){
		return this.isEnemy;
	}
	
	public void setValid(boolean isValid){
		this.isValid = isValid;
	}
	
	public boolean isValid(){
		return isValid;
	}
	
	public void paintCannonball(Graphics g){
		g.setColor(Color.BLACK);
		g.fillOval(x, y, width, height);
	}
	
}
