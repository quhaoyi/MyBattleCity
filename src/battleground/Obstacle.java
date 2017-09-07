package battleground;

import java.awt.Color;
import java.awt.Graphics;
import battleground.BattleStuff;
import tank.Tank;

//this is the obstacle class which will block all the incoming cannon balls and tanks
public class Obstacle extends BattleStuff{
	//this is the top left corner of the obstacle
	private int x, y;
	private int width, height;
	private Color color;
	
	public Obstacle(int x, int y, int width, int height, Color color){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.color = color;
	}
	public int getX(){
		return this.x;
	}
	
	public int getY(){
		return this.y;
	}
	
	public int getWidth(){
		return this.width;
	}
	
	public int getHeight(){
		return this.height;
	}
	public void setX(int x){
		this.x = x;
	}
	
	public void setY(int y){
		this.y = y;
	}
	
	public void setWidth(int width){
		this.width = width;
	}
	
	public void setHeight(int height){
		this.height = height;
	}
	
	public void setColor(Color color){
		this.color = color;
	}
	
	public void paintObstacle(Graphics g){
		g.setColor(color);
		g.fillRect(x, y, width, height);
	}
	
}
