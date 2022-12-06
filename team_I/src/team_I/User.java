package team_I;

import java.awt.Point;

public class User {
	private int width = 30;
	private int height = 30;
	Point center = new Point();
	Point under = new Point();
	
	public User() {
		center.setLocation(0, 0);
		getUnder();
		under.setLocation(center.x, center.y+15);
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public Point getUnder() {
		return under;
	}
	
	public void setUnder(int under) {
		this.under.y = under;
	}
	
	public Point getCenter() {
		return center;
	}
	
	public void setCenter(int x, int y) {
		this.center.setLocation(x, y);
	}
}
