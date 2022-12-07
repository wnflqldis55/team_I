package team_I;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;
import java.util.TimerTask;

public class Map extends Canvas implements KeyListener {
	int x = 390;
	int y = 390;
	private Graphics bufferGraphics;
	private Image offScreen;
	private Dimension dim;
	private Timer timer;
	private TimerTask timerTask;
	private boolean jump = false;
	private boolean fall = false;
	private int map[][];
	private User user = new User();
	
	public Map() {
		this.addKeyListener(this);
		map = new int[][] {{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
					   {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
					   {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
					   {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
					   {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
					   {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
					   {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
					   {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
					   {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
					   {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
					   {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
					   {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
					   {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
					   {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
					   {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
					   {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
					   {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
					   {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
					   {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
					   {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}};
		user.setCenter(x, y);
	}
	
	public void paint(Graphics g) {
		this.dim = getSize();
		this.offScreen = createImage(dim.width, dim.height);
		this.bufferGraphics = this.offScreen.getGraphics();
		bufferGraphics.clearRect(0, 0, dim.width, dim.height);
		
		for (int i = 0; i < map.length; i++) {
			for (int k = 0; k < map[i].length; k++) {
				if (map[i][k] == 1) {
					bufferGraphics.fillRect(30 * k, 30 * i, 30, 30);
				}
			}
		}
		bufferGraphics.fillOval(user.getCenter().x, user.getCenter().y - 30, user.getWidth(), user.getHeight());
		g.drawImage(offScreen, 0, 0, this);
	}

	public void keyTyped(KeyEvent e) {
		
	}

	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == 37) {
			System.out.println("왼쪽 키 누름");
			x -= 5;
			user.setCenter(x, user.getCenter().y);
		}
		else if (e.getKeyCode() == 38) {
			System.out.println("위쪽 키 누름");
			if (jump != true) {
				jump = true;
				jump();
			}
		}
		else if (e.getKeyCode() == 39) {
			System.out.println("오른쪽 키 누름");
			x += 5;
			user.setCenter(x, user.getCenter().y);
		}
		else if (e.getKeyCode() == 40) {
			System.out.println("아래쪽 키 누름");
		}
		repaint();
	}
	
	public void jump() {
		int i = y;
		System.out.println("점프");
		if (jump == true) {
			timer = new Timer();
			timerTask = new TimerTask() {
				public void run() {
					double g = y * 0.0098;
					y -= g;
					user.setCenter(user.getCenter().x, y);
					repaint();
					if (user.getCenter().y <= i - 200) {
						fall = true;
						try {
							Thread.sleep(200);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						timer.cancel();
					}
					fall();
				}
			};
			timer.schedule(timerTask, 0, 10);
		}
	}
	
	public void fall() {
		int i = y;
		System.out.println("떨어짐");
		if (fall == true) {
			timer = new Timer();
			timerTask = new TimerTask() {
				public void run() {
					double g = y * 0.0098;
					y += g;
					user.setCenter(user.getCenter().x, y);
					repaint();
					if (user.getCenter().y >= i + 200) {
						jump = false;
						fall = false;
						timer.cancel();
					}
				}
			};
			timer.schedule(timerTask, 0, 10);
		}
	}
	
	public void keyReleased(KeyEvent e) {
		
	}
	
	public void update(Graphics g) {
		paint(g);
	}
}
