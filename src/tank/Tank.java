package tank;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import battleground.BattleModel;
import battleground.BattleStuff;

public class Tank extends BattleStuff{
	//starting x,y coordinates of the tank (top-left corner)
	private int x, y;
	//the default length and width of the tank
	private int width, height;
	//color of the tank to discriminate enemy from the player
	private Color color;
	//this class is in charge of controlling stuff on the battlefield
	private BattleModel field;
	//this array contains all the direction a battleStuff can move towards
	private final String[] DIRECTIONS = {"N", "S", "E", "W"};
	//this is the current direction the tank is moving towards
	private String direction;
	//this is the reloadTime of the cannonBall, there is one mod function in the move
	//method to control the firing rate of this tank
	private long lastFireTime;
	private int hp;
	private boolean isDestroyed;
	private boolean isEnemy;
	public static final int RELOADTIME = 700;
	
	//the default direction can be set in the constructor
	public Tank(int x, int y, Color color, int width, int height, BattleModel field, boolean isEnemy){
		this.x = x;
		this.y = y;
		this.color = color;
		this.width = width;
		this.height = height;
		this.field = field;
		this.direction = "W";
		this.lastFireTime = System.currentTimeMillis();
		this.hp = 2;
		this.isDestroyed = false;
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
	//this function can set the color of the tank
	public void setColor(Color color){
		this.color = color;
	}
	
	//this function is in charge of the moving movement of the tank, it can detect whether
	//the tank will collide with obstacles or borders in the next movement. In the meantime,
	//after certain number of movements, the tank will fire a cannon ball.
	public void move(){
		//this is the fire function.
		if(hp > 0){
			if(isEnemy){
				fire();
			}
			//If is movable in the current direction, it will move; else it will try other direction
			if(BattleStuff.isMovable(this, direction, field)){
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
			}else if(isEnemy){
				this.direction = changeDirection();
				move();
			}
			//hp -= isHit();
		}else{
			this.isDestroyed = true;
		}
		
	}
	
	//this function will create a cannon ball class and add it to the battlefield
	public void fire(){
		long currentTime = System.currentTimeMillis();
		if(currentTime - lastFireTime  >= RELOADTIME){
			field.add(new Cannonball(x + width / 2, y + height / 2, 5, 5, 1, direction, field, this.isEnemy));
			lastFireTime = currentTime;
		}
	}
	
	public boolean isEnemy(){
		return this.isEnemy;
	}
	
	
	public boolean isDestroyed(){
		return this.isDestroyed;
	}
	
	//this function will randomly choose other directions to move if current direction is 
	//not movable.
	public String changeDirection(){
		Random r = new Random();
		switch(direction){
		case "W":
			return DIRECTIONS[r.nextInt(2)];
		case "E":
			return DIRECTIONS[r.nextInt(2)];
		case "N":
			return DIRECTIONS[r.nextInt(2) + 2];
		case "S"  :
			return DIRECTIONS[r.nextInt(2) + 2];
		}
		return "S";
	}
	
	public void isHit(){
		this.hp--;
	}
	
	public void setDirection(String direction){
		this.direction = direction;
	}
	
	
//	public boolean isMovable(String direction){
//		boolean borderPass = true;
//		boolean obstaclePass = false;
//		List<Obstacle> obstacles = field.getObstacles();
//		List<Tank> tanks = field.getTanks();
//		for(Obstacle o : obstacles){
//			borderPass &= BattleStuff.isPassable(this, o, direction);
//		}
//		for(Tank t : tanks){
//			if(!t.isDestroyed)
//			borderPass &= BattleStuff.isPassable(this, t, direction);
//		}
//		borderPass &= BattleStuff.isPassable(this, field.getPlayerTank(), direction);
//		
//		switch(direction){
//		case "W":
//			if(this.x - velocity >= 0){
//				obstaclePass = true;
//			}
//			break;
//		case "E":
//			if(this.x + width + velocity <= field.getWidth()){
//				obstaclePass = true;
//			}
//			break;
//		case "N":
//			if(this.y - velocity >= 0){
//				obstaclePass = true;
//			}
//			break;
//		case "S"  :
//			if(this.y + height + velocity <= field.getHeight()){
//				obstaclePass = true;
//			}
//			break;
//		}
//		return obstaclePass && borderPass;
//	}
//	

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
	
	
	//this method will draw the tank and its cannon once called, it can automatically adjust
	//the position of the cannon
	public void paintTank(Graphics g){
		g.setColor(color);
		g.fillRect(x, y, width, height);
		g.setColor(Color.BLACK);
		switch(direction){
		case "W":
			g.fillRect(x - width / 2, y + height / 3, width, height / 3);
			break;
		case "E":
			g.fillRect(x + width / 2, y + height / 3, width, height / 3);
			break;
		case "N":
			g.fillRect(x + width / 3, y - height / 2, width / 3, height);
			break;
		case "S":
			g.fillRect(x + width / 3, y + height / 2, width / 3, height);
			break;
		}
	}
	
}

