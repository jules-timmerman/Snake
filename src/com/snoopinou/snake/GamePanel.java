package com.snoopinou.snake;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;

public class GamePanel extends JPanel{
	
	public int score = 0;
	private int size = 5;
	ArrayList<Part> parts = new ArrayList<Part>();
	
	Fruit f;
	
	Thread tActu;
	Thread tFruit;
	
	public GamePanel() {
		super();
		
		this.setPreferredSize(new Dimension(350,350));
		
		for(int i = 0; i < 3; i++) {
			parts.add(new Part(200+(i*size),200,size, 3));
		}
		
		tActu = new Thread(new Actualisation(this));
		tActu.start();
		
		this.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				switch(e.getKeyCode()) {
				case KeyEvent.VK_UP:
					DirectionChanger dcN = new DirectionChanger(0);
					Thread tN = new Thread(dcN);
					tN.start();
					break;
				case KeyEvent.VK_RIGHT:
					DirectionChanger dcR = new DirectionChanger(1);
					Thread tR = new Thread(dcR);
					tR.start();
					break;	
				case KeyEvent.VK_DOWN:
					DirectionChanger dcD = new DirectionChanger(2);
					Thread tD = new Thread(dcD);
					tD.start();
					break;
				case KeyEvent.VK_LEFT:
					DirectionChanger dcL = new DirectionChanger(3);
					Thread tL = new Thread(dcL);
					tL.start();
					break;
					
				}
			}
		});
		
		createFruit();
	}	
	
	
	public void createFruit() {
		
		Random r = new Random();
		
		int x = r.nextInt((int)this.getPreferredSize().getWidth() - (size*2))+size;
		int y = r.nextInt((int)this.getPreferredSize().getHeight() - (size*2))+size;
		
		boolean okay = false;
		
		while(!okay) {
			int i = 0;
			for(Part p : parts) {
				if(p.getX() == y && p.getY() == y) { // SI LOCATION FRUIT == PARTIE DU SNAKE
					x = r.nextInt(this.getWidth() - (size*2))+size;
					y = r.nextInt(this.getHeight() - (size*2))+size; // On reroll Les coords
					break; // Recommence tout les tests
				}else {
					i++;
				}
				
				if(i == parts.size()-1) { // Si tous part != coord fruit
					okay = true;
				}
			}
		}
		
		f = new Fruit(x,y,size);
		
		tFruit = new Thread(f);
		tFruit.start();
	}
	
	public void paintComponent(Graphics g) {
		
		score = Fruit.fruitEaten;
		
		g.setColor(Color.white);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		
		g.setColor(Color.blue);
		g.setFont(new Font("Arial", Font.BOLD, 16));
		g.drawString(String.valueOf(score), 16, 16); // Le score
		
		g.setColor(Color.black);
		for(Part p : parts) {
			g.fillRect(p.getX(), p.getY(), p.getSize(), p.getSize()); // Les parties du snake
		}
		
		g.setColor(Color.RED);
		g.fillRect(f.getX(), f.getY(), size, size); // Pour le fruit
		
	}
	
	public void reset() {
		parts.clear();
		
		for(int i = 0; i < 3; i++) {
			parts.add(new Part(200+(i*size),200,size, 3));
		}
		
		createFruit();
	}
}





















