package team_I;
import java.awt.Point;

public class User {
	private int width = 30; //가로 크기
	private int height = 30; //세로 크기
	Point center = new Point(); //센터 좌표
	int left; //왼쪽 좌표
	int right; //오른쪽 좌표
	int top; //위쪽 좌표
	int bottom; //아래쪽 좌표
	
	public User() {
		
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public Point getCenter() {
		return center;
	}
	
	public void setCenter(int x, int y) {
		this.center.setLocation(x, y);
		this.left = center.x - 15;
		this.right = center.x + 15;
		this.top = center.y - 15;
		this.bottom = center.y + 15;
	}
	
	public int getLeft() {
		return this.left;
	}
	
	public int getRight() {
		return this.right;
	}
	
	public int getTop() {
		return this.top;
	}
	
	public int getBottom() {
		return this.bottom;
	}
}
