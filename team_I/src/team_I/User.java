package team_I;
import java.awt.Point;

public class User {
	private int width = 30; //���� ũ��
	private int height = 30; //���� ũ��
	Point center = new Point(); //���� ��ǥ
	int left; //���� ��ǥ
	int right; //������ ��ǥ
	int top; //���� ��ǥ
	int bottom; //�Ʒ��� ��ǥ
	
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
