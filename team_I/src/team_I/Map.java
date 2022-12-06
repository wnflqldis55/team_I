package team_I;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;
import java.util.TimerTask;

public class Map extends Canvas implements KeyListener {
	int x = 400;
	int y = 400;
	private Graphics bufferGraphics;
	private Image offScreen;
	private Dimension dim;
	private Timer timer;
	private TimerTask timerTask;
	private boolean jump = false;
	private boolean fall = false;
	
	public Map() {
		this.addKeyListener(this);
	}
	
	public void paint(Graphics g) {
		this.dim = getSize();
		this.offScreen = createImage(dim.width, dim.height);
		this.bufferGraphics = this.offScreen.getGraphics();
		
		bufferGraphics.clearRect(0, 0, dim.width, dim.height);
		bufferGraphics.fillRect(0, 430, 1000, 400);
		bufferGraphics.fillOval(x, y, 30, 30);
		g.drawImage(offScreen, 0, 0, this);
	}

	public void keyTyped(KeyEvent e) {
		
	}

	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == 37) {
			System.out.println("���� Ű ����");
			x -= 5;
		}
		else if (e.getKeyCode() == 38) {
			System.out.println("���� Ű ����");
			jump = true;
			jump();
		}
		else if (e.getKeyCode() == 39) {
			System.out.println("������ Ű ����");
			x += 5;
		}
		else if (e.getKeyCode() == 40) {
			System.out.println("�Ʒ��� Ű ����");
		}
		repaint();
	}
	
	public void jump() {
		int i = y;
		System.out.println("����");
		if (jump == true) {
			jump = false;
			timer = new Timer();
			timerTask = new TimerTask() {
				public void run() {
					y -= 5;
					repaint();
					if (y <= i - 200) {
						fall = true;
						try {
							Thread.sleep(300);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						timer.cancel();
					}
					fall();
				}
			};
			timer.schedule(timerTask, 0, 30);
			
		}
	}
	
	public void fall() {
		int i = y;
		System.out.println("������");
		if (fall == true) {
			timer = new Timer();
			timerTask = new TimerTask() {
				public void run() {
					y += 5;
					repaint();
					if (y >= i + 200) {
						fall = false;
						timer.cancel();
					}
				}
			};
			timer.schedule(timerTask, 0, 30);
		}
	}
	
	public void keyReleased(KeyEvent e) {
		
	}
	
	public void update(Graphics g) {
		paint(g);
	}
}