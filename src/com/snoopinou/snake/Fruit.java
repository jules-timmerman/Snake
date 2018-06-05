package com.snoopinou.snake;

import java.awt.Rectangle;

public class Fruit implements Runnable {
	
	private int x = 0;
	private int y = 0;
	private int size = 0;
	private Rectangle rect;
	public static int fruitEaten = 0;
	
	GamePanel gamePanel;
	
	
	public Fruit(int x, int y, int size) {
		this.x = x;
		this.y = y;
		this.size = size;
		
		this.rect = new Rectangle(x,y,size,size);
	}
	
	private void actuGamePanel() {
		this.gamePanel = Main.fenetre.gamePanel;
	}
	
	public boolean isEaten(Rectangle snake) {
		
		if(this.rect.intersects(snake)) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public void run() {
		try {
			Thread.sleep(1000); // WAIT FOR END OF INITIALISATION
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		
		
		actuGamePanel();
		Part head = gamePanel.parts.get(0);
		while(this.isEaten(head.getRect()) == false) {
			actuGamePanel();
			head = gamePanel.parts.get(0);
			
			if(this.isEaten(head.getRect()) == true) {
				System.out.println("ajajaj");
				fruitEaten++;
				gamePanel.createFruit();
				
				Part tail = gamePanel.parts.get(gamePanel.parts.size()-1);
				int size = tail.getSize();
				switch(tail.getDirection()) {
				case 0: // UP
					int xU = tail.getX();
					int yU = tail.getY() + size;					
					Main.fenetre.gamePanel.parts.add(new Part(xU, yU, size, tail.getDirection()));
					break;
				case 1: // RIGHT
					int xR = tail.getX() - size;
					int yR = tail.getY();
					Main.fenetre.gamePanel.parts.add(new Part(xR, yR, size, tail.getDirection()));
					break;
				case 2: // DOWN
					int xD = tail.getX();
					int yD = tail.getY() - size;
					Main.fenetre.gamePanel.parts.add(new Part(xD, yD, size, tail.getDirection()));
					break;
				case 3: // LEFT
					int xL = tail.getX() + size;
					int yL = tail.getY();
					Main.fenetre.gamePanel.parts.add(new Part(xL, yL, size, tail.getDirection()));
					break;
				}
				
			}
		}
	}

	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}


}
