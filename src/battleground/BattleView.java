package battleground;

import java.awt.*;

import javax.swing.JPanel;

import tank.Tank;
import tank.Cannonball;

public class BattleView extends JPanel {
	private BattleModel model;
	
	public BattleView(BattleModel model){
		this.model = model;
	}
	
	//Once being notified, the battlefield will be repainted.=
	public void notifyView(){
		repaint(); 
	}
	
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Rectangle bounds = getBounds();
		g.clearRect(0, 0, bounds.width, bounds.height);
//		for(int i = 0; i < model.playerMove.length ;i ++)
//		g.drawString(model.playerMove[i] +"", 200 + i*50, 300);
//		g.drawString(model.getPlayerTank().reloadTime +"", 100, 500);
		model.setHeight(bounds.height);
		model.setWidth(bounds.width);
		for(Tank t: model.getTanks()){
			if(!t.isDestroyed())
			t.paintTank(g);
		}
		model.getPlayerTank().paintTank(g);
		for(Obstacle o : model.getObstacles()){
			o.paintObstacle(g);
		}
		
		for(Cannonball c : model.getCannonballs()){
			if(c.isValid())
				c.paintCannonball(g);
		}
		
	}
}
