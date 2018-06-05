package com.snoopinou.snake;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Fenetre extends JFrame{
	
	GamePanel gamePanel;
	

	public Fenetre() {
		this.setTitle("Snake");
		this.setSize(400, 400);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.setResizable(false);
		
		initComp();
		
		this.setVisible(true);
	}
	
	private void initComp() {
		
		gamePanel = new GamePanel();
		
		this.setContentPane(gamePanel);
		this.getContentPane().setFocusable(true);
	}
	
}
