package com.snoopinou.snake;

public class DirectionChanger implements Runnable{

	int direction;
	int waitTime = 100; // Vitesse entre deplacement
	GamePanel gamePanel;
	
	public DirectionChanger(int direction) {
		super();
		
		this.direction = direction;
		actuGamePanel();
		
	}
	
	private void actuGamePanel() {
		this.gamePanel = Main.fenetre.gamePanel;
	}
	
	@Override
	public void run() {
		for(int i = 0; i < gamePanel.parts.size(); i++) {
			actuGamePanel();
			gamePanel.parts.get(i).setDirection(this.direction);
			try {
				Thread.sleep(waitTime);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
