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

import javax.swing.JOptionPane;

public class Help_sub extends Canvas implements KeyListener {
	private static final long serialVersionUID = 1L;
	private int pointX = 105; //ȭ�� x
	private int pointY = 285; //ȭ�� y
	private boolean savePoint = false;
	private Graphics bufferGraphics; //������۸� �ӽ� �׷���
	private Image offScreen; //������۸� �̹���
	private Dimension dim; //ȭ�� ������
	private boolean jump = false; //���߿� ���ִ� ����
	private boolean fall = false; //�������� ����
	private int map[][]; //�� �迭
	private User user = new User(); //����
	private boolean left = false; //���� Ű
	private boolean right = false; //������ Ű
	private boolean up = false; //���� Ű
	private boolean down = false; //�Ʒ��� Ű
	
	public Help_sub() {
		this.addKeyListener(this); //Ű���� ������
		//�� ����
		map = new int[][] {{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 1, 0, 0, 3, 0, 0, 4, 0, 0, 5, 0, 0, 6, 0, 0, 8, 0, 0, 0, 0, 0, 0},
			{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0},
			{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
			{0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
			{0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 9, 0, 0, 6, 0, 0, 0, 0, 0, 0},
			{1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
			{1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}};
		user.setCenter(pointX, pointY); //���� ���� ����
		requestFocus(); //Ű�̺�Ʈ�� ���� ������Ʈ�� ����
		setFocusable(true); //Ű �̺�Ʈ �켱 �Է�
	}
	
	public void paint(Graphics g) {
		processKey(); //Ű �̺�Ʈ
		this.dim = getSize(); //��ũ�� ������
		this.offScreen = createImage(dim.width, dim.height); //������۸� ����
		this.bufferGraphics = this.offScreen.getGraphics();
		
		bufferGraphics.clearRect(0, 0, dim.width, dim.height); //ȭ�� �����
		bufferGraphics.setColor(Color.WHITE);
		bufferGraphics.fillRect(0, 0, dim.width, dim.height);
		
		drawMap();
		
		for (int i = 0; i < map.length; i++) { //�ӽ� �׸���
			for (int k = 0; k < map[i].length; k++) {
				if ((i + k) % 2 == 0) {
					bufferGraphics.setColor(Color.BLACK);
					bufferGraphics.drawRect(30 * k, 30 * i, 30, 30);
				}
			}
		}
		
		bufferGraphics.fillOval(user.getLeft(), user.getTop(), user.getWidth(), user.getHeight()); //���� �׸���
		g.drawImage(offScreen, 0, 0, this); //������Ʈ�� �׸���
		
		int i = Math.round(user.getCenter().y / 30); //���� ��ġ�� �� y
 		int k = Math.round(user.getCenter().x / 30); //���� ��ġ�� �� x
 		System.out.println(i + " " + k);
	}

	public void keyTyped(KeyEvent e) {
		
	}
	
	private void processKey() { //Ű �̺�Ʈ �Լ�
		if(left) { //����
			if (LeftCheck() == false && user.getLeft() > 0) { //���ʿ� ��ֹ��� ���� x�� 0���� ����(ȭ���� ���� ���� ���� ���)
				user.setCenter(user.getCenter().x - 5, user.getCenter().y); //������ �������� ������
			}
			if (fall == false && jump == false && bottomCheck() == false) { //������ �ƴϸ� �Ʒ��ʿ� ��ֹ��� ����
				fall = true; //�������� ������ ����
				fall(); //������
			}
		}
		
		if(right) { //������
			if (RightCheck() == false && user.getRight() < 800 - 15) {
				//�����ʿ� ��ֹ��� ���� x-800(ȭ��ũ��)�� ���� ���κ��� ����(ȭ���� ���� ������ ���� ���)
				user.setCenter(user.getCenter().x + 5, user.getCenter().y); //������ ���������� ������
			}
			if (fall == false && jump == false && bottomCheck() == false) { //������ �ƴϸ� �Ʒ��ʿ� ��ֹ��� ����
				fall = true; //�������� ������ ����
				fall(); //������
			}
		}
		
		if(up) { //����
			if (jump == false) { //������ �ƴ� ���
				jump = true; //������ ����
				jump(); //����
			}
		}
		
		if(down) { //�Ʒ���
			
		}
	}
	
	public void keyPressed(KeyEvent e) { //Ű�� ������ ��
		if (e.getKeyCode() == KeyEvent.VK_LEFT) { //���� Ű��
			System.out.println("���� Ű ����"); //���
			left = true; //���� true
		}
		
		if (e.getKeyCode() == KeyEvent.VK_UP) { //���� Ű��
			System.out.println("���� Ű ����"); //���
			up = true; //���� true
		}
		
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) { //������ Ű��
			System.out.println("������ Ű ����"); //���
			right = true; //������ true
		}
		
		if (e.getKeyCode() == KeyEvent.VK_DOWN) { //�Ʒ��� Ű��
			System.out.println("�Ʒ��� Ű ����"); //���
			down = true; //�Ʒ��� true
		}
		
		if (e.getKeyCode() == KeyEvent.VK_SPACE) { //�����̽��ٸ�
			if (map[user.getCenter().y / 30][(user.getCenter().x) / 30] == 2) { //��ġ�� ���� ���
				this.clear(); //Ŭ���� �Լ� ȣ��
			} else if (map[user.getCenter().y / 30][(user.getCenter().x) / 30] == 6) { //��ġ�� ���̺� ����Ʈ�� ���
				this.save(); //Ŭ���� �Լ� ȣ��
			}
		}
		repaint(); //�ٽñ׸���
	}
	
	public void jump() { //����
		int userY = user.getBottom(); //������ �Ʒ� ��ǥ
		System.out.println("����"); //���
		
		if (jump == true) { //������ ���
			final Timer timer = new Timer(); //Ÿ�̸� �����
			TimerTask timerTask = new TimerTask() {
				public void run() {
					user.setCenter(user.getCenter().x, (int)(user.getCenter().y - 5)); //5px�� ���� �ö�
					repaint(); //�ٽñ׸���
					if (TopCheck()) { //���ʿ� ��ֹ��� ���� ���
						try {
							System.out.println("�ְ���"); //���
							repaint(); //�ٽñ׸���
							Thread.sleep(500); //0.5�� ����
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						fall = true; //�������� ������ ����
						timer.cancel(); //Ÿ�̸� ���
					} else if (user.getCenter().y <= userY - 100) { //�ʱ� ��ġ���� 100 ���� �ö��� ���
						try {
							System.out.println("�ְ���"); //���
							repaint(); //�ٽñ׸���
							Thread.sleep(500); //0.5�� ����
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						fall = true; //�������� ������ ����
						timer.cancel(); //Ÿ�̸� ���
					}
					fall(); //�������� �Լ� ȣ��
				}
			};
			timer.schedule(timerTask, 0, 30);
		}
	}
	
	public void fall() { //������
		System.out.println("������");
		
		if (fall == true) { //�������� ���̸�
			final Timer timer = new Timer(); //Ÿ�̸� ����
			TimerTask timerTask = new TimerTask() {
				public void run() {
					user.setCenter(user.getCenter().x, (int)(user.getCenter().y + 5)); //5px�� ������
					repaint(); //�ٽñ׸���
					if (bottomCheck()) { //�Ʒ��ʿ� ��ֹ��� ������
						timer.cancel(); //Ÿ�̸� ���
						System.out.println("bottom: "+bottomCheck()); //���
						fall = false; //�������� ���� �ƴ�
					}
				}
			};
			timer.schedule(timerTask, 0, 30);
		}
	}
	
	public boolean LeftCheck() { //���� �浹 üũ
		int i = Math.round(user.getCenter().y / 30); //���� ��ġ�� �� y
 		int k = Math.round(user.getLeft() / 30); //���� ��ġ�� �� x
 		
 		if (k > 0) {
 			if (i < 20 && i >= 0) {
	 			if (map[i][k] != 0 && map[i][k] != 2 && map[i][k] != 6 && map[i][k] != 7)  { //��ֹ��� ������
	 				reboot(i, k);
	 				fire(i, k);
	 				move(i, k);
					return true; //�浹
				}
	 		}
 		}
 		else if (k <= 0){
 			if (user.getLeft() <= 0) { //������ ������ ��ֹ��� ������
				return true; //�浹
 			}
 		}
 		
		return false; //�浹����
	}
	
	public boolean RightCheck() { //������ �浹 üũ
		int i = Math.round(user.getCenter().y / 30); //���� ��ġ�� �� y
 		int k = Math.round(user.getRight() / 30); //���� ��ġ�� �� x
 		
 		if (map[1].length > k) {
 			if (i < 20 && i >= 0) {
 				if (map[i][k] != 0 && map[i][k] != 2 && map[i][k] != 6 && map[i][k] != 7) { //��ֹ��� ������
 					reboot(i, k);
 					fire(i, k);
 					move(i, k);
					return true; //�浹
		 		}
	 		} 
 		}
 		else if (map[1].length <= k) {
			return true; //�浹
 		}
 		
		return false; //�浹����
	} 
	
	public boolean TopCheck() { //���� �浹 üũ
		int i = Math.round(user.getTop() / 30); //���� ��ġ�� �� y
 		int k = Math.round(user.getCenter().x / 30); //���� ��ġ�� �� x
 		
		if (i < 20 && i >= 0) { //ȭ�� ���̸�
			if (map[i][k] != 0 && map[i][k] != 2 && map[i][k] != 6 && map[i][k] != 7)  { //��ֹ��� ���� ��
				reboot(i, k);
				fire(i, k);
				move(i, k);
				fall = true; //�������� ����
				jump = true; //���� ����
				return true; //�浹
			}
		}
		else {
			return true;
		}
		
		return false; //�浹����
	}
	
	public boolean bottomCheck() { //�Ʒ��� �浹 üũ
		int i = Math.round(user.getBottom() / 30); //���� ��ġ�� �� y
 		int k = Math.round(user.getCenter().x / 30); //���� ��ġ�� �� x
 		
		if (i < 19 && i >= 0) { //ȭ�� ���̸�
			if (map[i][k] != 0 && map[i][k] != 2 && map[i][k] != 6 && map[i][k] != 7)  { //������ �Ʒ��ʿ� ��ֹ��� ���� ��
				reboot(i, k);
				fire(i, k);
				move(i, k);
				fall = false; //�������� ���� �ƴ�
				jump = false; //���� �ƴ�
				return true; //�浹
			}
		}
		else if (i <= 19) {
			over();
		}
		
		return false;  //�浹����
	}
	
	public void keyReleased(KeyEvent e) { //Ű�� ���� ��
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			left = false;
		}
			
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			up = false;
		}
		
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			right = false;
		}
		
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			down = false;
		}
	}
	
	public void clear() { //Ŭ���� ��
		System.out.println("clear");
		String[] answer = {"Ȯ��", "�ٽ��ϱ�"}; //���̾�α� ��ư ����
		int result = JOptionPane.showOptionDialog(this, " * clear * ", "clear" //����, Ÿ��Ʋ ����
				,JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, answer, null );
		if (result == 1) { //Ȯ�� ���� ��
			pointX = 105; //ȭ�� x
			pointY = 285; //ȭ�� y
			user.setCenter(pointX, pointY);
		}
		else {
			
		}
	}
	
	public void over() {
		if (savePoint) {
			String[] answer = {"��", "ó������"}; //���̾�α� ��ư ����
			int choice = JOptionPane.showOptionDialog(this, " ����� ��ġ���� �����Ͻðڽ��ϱ�? ", "game over" //����, Ÿ��Ʋ ����
					,JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, answer, null );
			if (choice == 0) {
				user.setCenter(pointX, pointY);
			} else {
				int index[] = find(7);
				for (int num = 0; num < index.length; num += 2) {
					if (index[num] == 0 && index[num + 1] == 0) {
						break;
					}
					map[index[num]][index[num + 1]] = 6;
				}
				pointX = 105; //ȭ�� x
				pointY = 280; //ȭ�� y
				user.setCenter(pointX, pointY);
			}
		}
		else {
			String[] answer = {"Ȯ��", "�ٽ��ϱ�"}; //���̾�α� ��ư ����
			JOptionPane.showOptionDialog(this, " ~ game over ~ ", "game over" //����, Ÿ��Ʋ ����
					,JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, answer, null );
			if (true) { //Ȯ�� ���� ��
				pointX = 105; //ȭ�� x
				pointY = 285; //ȭ�� y
				user.setCenter(pointX, pointY);
			}
		}
	}
	
	public void reboot(int i, int k) {
		if (map[i][k] == 4) {
			user.setCenter(105, 285);
			System.out.println("ó������");
		}
	}
	
	public void fire(int i, int k) {
		if (map[i][k] == 5) {
			over();
		}
	}
	
	public void save() {
		System.out.println("save"); //���
		int i = Math.round(user.getCenter().y / 30); //���� ��ġ�� �� y
 		int k = Math.round(user.getCenter().x / 30); //���� ��ġ�� �� x
 		
		pointX = user.getCenter().x;
		pointY = user.getCenter().y - 5;
		int index[] = find(7);
		for (int num = 0; num < index.length; num += 2) {
			if (index[num] == 0 && index[num + 1] == 0) {
				break;
			}
			map[index[num]][index[num + 1]] = 6;
		}
		map[i][k] = 7;
		System.out.println(map[i][k]);
		this.savePoint = true;
	}
	
	public void move(int i, int k) {
		if (map[i][k] == 8) {
			int b = (int) (Math.random() * map.length);
			int a = (int) (Math.random() * map[1].length - 1);
			
			System.out.println("a:" + a + " " + "b:" + b);
			if (map[b][a] != 1 && map[b][a] != 3 && map[b][a] != 4 && map[b][a] != 5) {
				user.setCenter(a * 30 + 15, b * 30 + 15);
			} else {
				move(i, k);
			}
		}
	}
	
	public void drawMap() { //�ʱ׸���
		for (int i = 0; i < map.length; i++) { //�ӽ� �׸���
			for (int k = 0; k < map[i].length; k++) {
				if (map[i][k] == 1) { //1�̸� ��
					bufferGraphics.setColor(Color.BLACK);
					bufferGraphics.fillRect(30 * k, 30 * i, 30, 30);
				}
				else if (map[i][k] == 2) { //2�� ��
					bufferGraphics.setColor(Color.BLACK);
					if (map[i-1][k] != 2) {
						bufferGraphics.fillOval(30 * k, 30 * i + 15, 30, 30);
					}
					else {
						bufferGraphics.fillRect(30 * k, 30 * i, 30, 30);
					}
				}
				else if (map[i][k] == 3) { //4�� �ʱ� ��ġ��
					bufferGraphics.setColor(Color.lightGray);
					bufferGraphics.drawRect(30 * k, 30 * i, 30, 30);
					
				}
				else if (map[i][k] == 4) { //4�� �ʱ� ��ġ��
					bufferGraphics.setColor(Color.magenta);
					bufferGraphics.fillRect(30 * k, 30 * i, 30, 30);
					
				}
				else if (map[i][k] == 5) { //5�� ��
					bufferGraphics.setColor(Color.RED);
					bufferGraphics.fillRect(30 * k, 30 * i, 30, 30);
				}
				else if (map[i][k] == 6) { //6�̸� ���̺� ����Ʈ
					bufferGraphics.setColor(Color.GREEN);
					bufferGraphics.fillRect(30 * k, 30 * i, 30, 30);
				}
				else if (map[i][k] == 7) { //7�̸� 7�̸� ������ ���̺�
					bufferGraphics.setColor(Color.BLUE);
					bufferGraphics.fillRect(30 * k, 30 * i, 30, 30);
				}
				else if (map[i][k] == 8) { //8�̸� ��������
					bufferGraphics.setColor(Color.YELLOW);
					bufferGraphics.fillRect(30 * k, 30 * i, 30, 30);
				}
				else if (map[i][k] == 8) { //9�� �̵��ӵ� ����
					bufferGraphics.setColor(Color.cyan);
					bufferGraphics.fillRect(30 * k, 30 * i, 30, 30);
				}
			}
		}
	}
	
	public int[] find(int num) { //�ش� �� ��ȣ ã��
		int[] index = new int[20];
		int a = 0;
		
		for (int i = 0; i < map.length; i++) { //�ӽ� �׸���
			for (int k = 0; k < map[i].length; k++) {
				if (map[i][k] == num) { //1�̸� ��
					bufferGraphics.setColor(Color.BLACK);
					bufferGraphics.fillRect(30 * k, 30 * i, 30, 30);
					index[a] = i;
					index[a+1] = k;
					a += 2;
				}
				else if (map[i][k] == num) { //2�� ��
					bufferGraphics.setColor(Color.BLACK);
					if (map[i-1][k] != num) {
						bufferGraphics.fillOval(30 * k, 30 * i + 15, 30, 30);
					}
					else {
						bufferGraphics.fillRect(30 * k, 30 * i, 30, 30);
					}
				}
				else if (map[i][k] == num) { //4�� �ʱ� ��ġ��
					bufferGraphics.setColor(Color.lightGray);
					bufferGraphics.drawRect(30 * k, 30 * i, 30, 30);
					index[a] = i;
					index[a+1] = k;
					a += 2;
				}
				else if (map[i][k] == num) { //4�� �ʱ� ��ġ��
					bufferGraphics.setColor(Color.magenta);
					bufferGraphics.fillRect(30 * k, 30 * i, 30, 30);
					index[a] = i;
					index[a+1] = k;
					a += 2;
				}
				else if (map[i][k] == num) { //5�� ��
					bufferGraphics.setColor(Color.RED);
					bufferGraphics.fillRect(30 * k, 30 * i, 30, 30);
					index[a] = i;
					index[a+1] = k;
					a += 2;
				}
				else if (map[i][k] == num) { //6�̸� ���̺� ����Ʈ
					bufferGraphics.setColor(Color.GREEN);
					bufferGraphics.fillRect(30 * k, 30 * i, 30, 30);
					index[a] = i;
					index[a+1] = k;
					a += 2;
				}
				else if (map[i][k] == num) { //7�̸� ��������
					bufferGraphics.setColor(Color.YELLOW);
					bufferGraphics.fillRect(30 * k, 30 * i, 30, 30);
					index[a] = i;
					index[a+1] = k;
					a += 2;
				}
			}
		}
		return index;
	}
	
	public void update(Graphics g) {
		paint(g);
	}
}
