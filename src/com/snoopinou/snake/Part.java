package com.snoopinou.snake;

import java.awt.Rectangle;

public class Part {

	private int x;
	private int y;
	private int size;
	private int direction; // 0 = NORD		1 = EST		2 = SUD		3 = OUEST
	private Rectangle rect;
	
	public Part(int x, int y, int size, int direction) {
		this.x = x;
		this.y = y;
		this.size = size; 
		this.direction = direction;
		
		this.rect = new Rectangle(x,y,size,size);
	}
	
	
	public void actuXY() {
		switch (this.direction) {
		case 0:		// NORD
			this.setX(this.getX());
			this.setY(this.getY() - this.size);
			break;
		case 1:		// EST
			this.setX(this.getX()  + this.size);
			this.setY(this.getY());
			break;
		case 2:		// SUD
			this.setX(this.getX());
			this.setY(this.getY() + this.size);
			break;
		case 3:		// OUEST
			this.setX(this.getX() - this.size);
			this.setY(this.getY());
			break;
		}
		
		this.rect.setBounds(this.getX(), this.getY(), this.getSize(), this.getSize());
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
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public int getDirection() {
		return direction;
	}
	public void setDirection(int direction) {
		this.direction = direction;
	}


	public Rectangle getRect() {
		return rect;
	}


	public void setRect(Rectangle rect) {
		this.rect = rect;
	}
	
	
}
