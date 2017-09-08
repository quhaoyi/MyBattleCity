package game;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import battleground.*;
import tank.*;
public class MyBattleCity {
	private static final int viewSize = 1000;      // size of square viewer pane
	  private static final int cyclesToRun = 1000;  // how many cycles to run

	  /**
	   * Create window, viewer, and balls and run simulation
	   */
	  public static void main(String[] args) {
	    // Create the simulation model and add a couple of things to it
	    BattleModel field = new BattleModel(viewSize, viewSize);
	    field.add(new Tank(700, 500, Color.red, 50, 50, field, true));
	    field.add(new Tank(600, 500, Color.green, 40, 40, field, true));
	    field.add(new Obstacle(400, 400 ,100, 200, Color.BLACK));
	    field.add(new Obstacle(100, 200 ,70, 50, Color.BLUE));
	    field.add(new Obstacle(500, 200 ,100, 200, Color.BLACK));
	    field.add(new Obstacle(200, 0 ,100, 200, Color.BLACK));
	    field.addPlayer(new Tank(100, 100, Color.green, 40, 40, field, false));

	    // Create the controller/viewer,  put it in a window, and show it
	    JFrame frame = new JFrame("Battle Field");
	    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	    BattleController controller = new BattleController(field);
	    frame.add(controller);
	    frame.pack();
	    frame.setVisible(true);
	    frame.setResizable(false);

	    // let it run
	    //field.run();
	    // frame.dispose();   // uncomment to have window close when sim done
	  }
}
