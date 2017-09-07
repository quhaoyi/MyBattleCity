package battleground;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.*;
import javax.swing.JButton;
import javax.swing.JPanel;

import tank.Tank;

public class BattleController extends JPanel{
	private static final long serialVersionUID = 1L;
	private BattleModel world;
	private BattleView viewPane;      // the panel displaying the model
	public BattleController(BattleModel world){

		this.world = world;

	    // default layout manager for JPanel is flow layout - we want a border layout
	    setLayout(new BorderLayout());

	    // create paneel with ball display and place it in the center
	    viewPane = new BattleView(world);
	    viewPane.setPreferredSize(new Dimension(world.getWidth(), world.getHeight()));
	    add(viewPane, BorderLayout.CENTER);
	    // register the view with the model
	    world.addView(viewPane);



	    // create panel with buttons and add it at the bottom
	    JButton start = new JButton("start");
	    JButton pause = new JButton("pause");
	    JButton resume = new JButton("resume");
	    JButton stop = new JButton("stop");
	    JPanel buttons = new JPanel();
	    buttons.add(start);
	    buttons.add(pause);
	    buttons.add(resume);
	    buttons.add(stop);
	    start.setBackground(Color.orange);
	    pause.setBackground(Color.yellow);
	    resume.setBackground(Color.green);
	    stop.setBackground(Color.red);
	    buttons.setBackground(Color.lightGray);
	    viewPane.addKeyListener(new KeyMonitor());
	    add(buttons, BorderLayout.SOUTH);
	    // set up listener for the buttons
	    SimButtonListener buttonListener = new SimButtonListener();
	    start.addActionListener(buttonListener);
	    pause.addActionListener(buttonListener);
	    resume.addActionListener(buttonListener);
	    stop.addActionListener(buttonListener);
	    viewPane.setFocusable(true);
	    
	  }


	/////////////// Inner Classes /////////////////


	  /**
	   * Handle button clicks for the BallSimControl window.
	   */
	  class SimButtonListener implements ActionListener {

	    /**
	     * Process button clicks by turning the simulation on and off
	     * @param e the event created by the button click
	     */
	    public void actionPerformed(ActionEvent e) {

	      switch(e.getActionCommand()) {
	      	case "start":
	      		viewPane.requestFocus();
	      		break;
	      	case "pause":
	      		world.pause();
	      		break;
	      	case "resume":
	      		world.resume();
	      		break;
	      	case "stop":
	      		world.restart();
	      		break;
	      }
	    }
	  }
	  
	  class KeyMonitor implements KeyListener{
	    	@Override
	    	public void keyPressed(KeyEvent e){
	    		Tank player = world.getPlayerTank();
	    		viewPane.requestFocus();
	    		switch(e.getKeyCode()){
	    		case KeyEvent.VK_UP:
	    			world.playerMove[0] = true;
	    			break;
	    		case KeyEvent.VK_LEFT:
	    			player.setDirection("W");
	    			world.playerMove[1] = true;
	    			break;
	    		case KeyEvent.VK_DOWN:
	    			player.setDirection("S");
	    			world.playerMove[2] = true;
	    			break;
	    		case KeyEvent.VK_RIGHT:
	    			player.setDirection("E");
	    			world.playerMove[3] = true;
	    			break;
	    		case KeyEvent.VK_J:
	    			player.fire();
	    			break;
	    		}
	    	}
	    	@Override
	    	public void keyReleased(KeyEvent e){
	    		viewPane.requestFocus();

		    	switch(e.getKeyCode()){
		    	case KeyEvent.VK_UP:
	    			world.playerMove[0] = false;
	    			break;
	    		case KeyEvent.VK_LEFT:
	    			world.playerMove[1] = false;
	    			break;
	    		case KeyEvent.VK_DOWN:
	    			world.playerMove[2] = false;
	    			break;
	    		case KeyEvent.VK_RIGHT:
	    			world.playerMove[3] = false;
	    			break;
	    		}
	    	}
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
	    	
	    }

}
