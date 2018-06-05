package com.snoopinou.snake;

import javax.swing.JOptionPane;

public class Actualisation implements Runnable{

	boolean alive = true;
	int waitTime = 100; // Vitesse entre deplacement
	GamePanel gamePanel;
	
	public Actualisation(GamePanel gamePanel) {
		super();
		this.gamePanel = gamePanel;
	}
	
	
	private void actuGamePanel() {
		this.gamePanel = Main.fenetre.gamePanel;
	}
	
	private void loose() { // 0 = OUI		1 = NON		-1 = CROIX
		int choix = JOptionPane.showConfirmDialog(null, "Vous avez perdu !\nVotre score est de : "+gamePanel.score, "Defaite", JOptionPane.YES_NO_OPTION);
		
		switch(choix) {
		case -1:
			System.exit(0);
			break;
		case 1:
			System.exit(0);
			break;
		case 0:
			Main.fenetre.gamePanel.reset();
			break;
		}
		
	}
	
	
	@Override
	public void run() {
		
		try {
			Thread.sleep(500); // WAIT FOR END OF INITIALISATION
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		
		while(alive) {
			try {
				Thread.sleep(waitTime);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			actuGamePanel();
			
			
			for(Part p : gamePanel.parts) {
				p.actuXY();
			}
			
			
			
			Part head = gamePanel.parts.get(0);
			if(!head.getRect().intersects(gamePanel.getBounds())) {
				loose();
			}
			
			boolean first = true;
			for(Part p : gamePanel.parts) {
				if(!first) {
					if(head.getRect().intersects(p.getRect())) {
						loose();
					}
				}else {
					first = false;
				}
			}
			
			gamePanel.repaint();
			
		}
	}
}
