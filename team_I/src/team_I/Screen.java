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

public class Screen extends Canvas implements KeyListener {
	private static final long serialVersionUID = 1L;
	private int x = 0; //ȭ�� x
	private int y = 0; //ȭ�� y
	private int finalX = 0; //ȭ�� x
	private int finalY = 0; //ȭ�� y
	private int pointX = 405; //ȭ�� x
	private int pointY = 375; //ȭ�� y
	private boolean savePoint = false;
	private Graphics bufferGraphics; //������۸� �ӽ� �׷���
	private Image offScreen; //������۸� �̹���
	private Dimension dim; //ȭ�� ������
	private boolean jump = false; //���߿� ���ִ� ����
	private boolean fall = false; //�������� ����
	private int map[][]; //�� �迭
	private User user = null; //����
	private boolean left = false; //���� Ű
	private boolean right = false; //������ Ű
	private boolean up = false; //���� Ű
	private boolean down = false; //�Ʒ��� Ű
	
	public Screen(int[][] map) {
		this.addKeyListener(this); //Ű���� ������
		this.map = map;
		user = new User();
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
		
		if (user != null) {
			bufferGraphics.setColor(new Color(75, 75, 75));
			bufferGraphics.fillOval(user.getLeft(), user.getTop(), user.getWidth(), user.getHeight()); //���� �׸���
		}
		g.drawImage(offScreen, 0, 0, this); //������Ʈ�� �׸���
		
		int i = Math.round(user.getCenter().y / 30); //���� ��ġ�� �� y
 		int k = Math.round((user.getCenter().x - x) / 30); //���� ��ġ�� �� x
 		System.out.println("�ε���(x, y): " + k + ", " + i);
	}

	public void keyTyped(KeyEvent e) {
		
	}
	
	private void processKey() { //Ű �̺�Ʈ �Լ�
		if(left) { //����
			if (x < 0 && LeftCheck() == false && user.getCenter().x == 405) {
				//x�� 0���� �۰�, ���ʿ� ��ֹ��� ������ ������ ȭ���� �����(����Ʈ)
				x += 5; //ȭ���� x�� ���������� �о� �������� ���Բ�
				user.setCenter(user.getCenter().x, user.getCenter().y); //���� �ٽ� �׸���
			}
			else if (LeftCheck() == false && x <= 0) { //���ʿ� ��ֹ��� ���� x�� 0���� ����(ȭ���� ���� ���� ���� ���)
				user.setCenter(user.getCenter().x - 5, user.getCenter().y); //������ �������� ������
			}
			else if (LeftCheck() == false && user.getCenter().x != 405) {
				//���ʿ� ��ֹ��� ���� ������ ����� �ƴ�(ȭ���� ������ ���̰� ������ ����� �ƴ� ���)
				user.setCenter(user.getCenter().x - 5, user.getCenter().y); //������ �������� ������
			}
			if (fall == false && jump == false && bottomCheck() == false) { //������ �ƴϸ� �Ʒ��ʿ� ��ֹ��� ����
				fall = true; //�������� ������ ����
				fall(); //������
			}
		}
		
		if(right) { //������
			if ((-1 * map[1].length * 30) < x - 810 && RightCheck() == false && user.getCenter().x == 405) {
				//x-800(ȭ��ũ��)�� ���� ũ���� �������� �۰� �����ʿ� ��ֹ��� ������ ������ �����(����Ʈ)
				x -= 5; //ȭ���� x�� �������� �о� ���������� ���Բ�
				user.setCenter(user.getCenter().x, user.getCenter().y); //���� �ٽ� �׸���
			}
			else if (RightCheck() == false &&  map[1].length * 30 > x - 810) {
				//�����ʿ� ��ֹ��� ���� x-800(ȭ��ũ��)�� ���� ���κ��� ����(ȭ���� ���� ������ ���� ���)
				user.setCenter(user.getCenter().x + 5, user.getCenter().y); //������ ���������� ������
			}
			else if (RightCheck() == false && user.getCenter().x != 405) {
				//�����ʿ� ��ֹ��� ������ ������ ȭ���� ����� �ƴ�(ȭ���� ���� ���̰� ������ ����� �ƴ� ���)
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
			if (jump != false) { //������ �ƴ� ���
				up = false;
				allDown();
			}
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
			if (map[user.getCenter().y / 30][(user.getCenter().x - x) / 30] == 2) { //��ġ�� ���� ���
				System.out.println("clear"); //���
				this.clear(); //Ŭ���� �Լ� ȣ��
			} else if (map[user.getCenter().y / 30][(user.getCenter().x - x) / 30] == 6) { //��ġ�� ���� ���
				System.out.println("save"); //���
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
	
	public void allDown() {
		int i = Math.round(user.getCenter().y / 30); //���� ��ġ�� �� y
 		int k = Math.round((user.getCenter().x - x) / 30); //���� ��ġ�� �� x
 		
 		for (; i < map.length; i++) {
 			if (map[i][k] == 1 || map[i][k] == 3 || map[i][k] == 4 || map[i][k] == 5 || map[i][k] == 8) {
 				break;
 			}
 		}
 		if (i >= map.length) {
 			i = map.length;
 		}
 		user.setCenter(user.getCenter().x, i * 30 - 20);
	}
	
	public boolean LeftCheck() { //���� �浹 üũ
		int i = Math.round(user.getCenter().y / 30); //���� ��ġ�� �� y
 		int k = Math.round((user.getLeft() - x) / 30); //���� ��ġ�� �� x
 		
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
 		int k = Math.round((user.getRight() - x) / 30); //���� ��ġ�� �� x
 		
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
 		int k = Math.round((user.getCenter().x - x) / 30); //���� ��ġ�� �� x
 		
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
 		int k = Math.round((user.getCenter().x - x) / 30); //���� ��ġ�� �� x
 		
		if (i < 20 && i >= 0) { //ȭ�� ���̸�
			if (map[i][k] != 0 && map[i][k] != 2 && map[i][k] != 6 && map[i][k] != 7)  { //������ �Ʒ��ʿ� ��ֹ��� ���� ��
				reboot(i, k);
				fire(i, k);
				move(i, k);
				fall = false; //�������� ���� �ƴ�
				jump = false; //���� �ƴ�
				return true; //�浹
			}
		}
		else if (i == 20) {
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
		System.out.println("clear"); //���
		String[] answer = {"Ȯ��", "�ٽ��ϱ�"}; //���̾�α� ��ư ����
		int result = JOptionPane.showOptionDialog(this, " * clear * ", "clear" //����, Ÿ��Ʋ ����
				,JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, answer, null );
		
		if (result == 1) { //�ٽ��ϱ� ���� ��
			x = 0;
			y = 0;
			finalX = 0;
			finalY = 0;
			pointX = 405;
			pointY = 375;
		}
		else {
			
		}
	}
	
	public void over() { //���� ���� ��
		System.out.println("game over"); //���
		if (savePoint) {
			String[] answer = {"��", "ó������"}; //���̾�α� ��ư ����
			int choice = JOptionPane.showOptionDialog(this, " ����� ��ġ���� �����Ͻðڽ��ϱ�? ", "game over" //����, Ÿ��Ʋ ����
					,JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, answer, null );
			if (choice == 0) {
				x = finalX;
				y = finalY;
				user.setCenter(pointX, pointY);
			} else {
				int index[] = find(7);
				for (int num = 0; num < index.length; num += 2) {
					if (index[num] == 0 && index[num + 1] == 0) {
						break;
					}
					map[index[num]][index[num + 1]] = 6;
				}
				x = 0;
				y = 0;
				finalX = 0;
				finalY = 0;
				pointX = 405;
				pointY = 370;
				user.setCenter(pointX, pointY);
			}
		}
		else {
			String[] answer = {"�ٽ��ϱ�"}; //���̾�α� ��ư ����
			JOptionPane.showOptionDialog(this, " ~ game over ~ ", "game over" //����, Ÿ��Ʋ ����
					,JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, answer, null );
			x = 0;
			y = 0;
			finalX = 0;
			finalY = 0;
			pointX = 405;
			pointY = 370;
			user.setCenter(pointX, pointY);
		}
	}
	
	public void reboot(int i, int k) {
		if (map[i][k] == 4) {
			x = 0;
			y = 0;
			user.setCenter(405, 375);
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
 		int k = Math.round((user.getCenter().x - x) / 30); //���� ��ġ�� �� x
 		
 		finalX = x;
		finalY = y;
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
		this.savePoint = true;
	}
	
	public void move(int i, int k) {
		if (map[i][k] == 8) {
			int b = (int) (Math.random() * 20);
			int a = (int) (Math.random() * 94);
			
			if (map[b][a] != 1 && map[b][a] != 3 && map[b][a] != 4 && map[b][a] != 5) {
				if(a < 13) {
					x = 0;
					user.setCenter(a * 30 + 15, b * 30 + 15);
				}
				else if (a > 81) {
					x = (-1 * map[1].length * 30) + 810;
					user.setCenter((a - 81) * 30 + 405, b * 30 + 15);
				}
				else if(x >= 13 && b <= 81) {
					x = (a - 13) * 30 * -1;
					user.setCenter(405, b * 30 + 15);
				}
			} else {
				move(i, k);
			}
		}
	}
	
	public void drawMap() { //�ʱ׸���
		for (int i = 0; i < map.length; i++) { //�ӽ� �׸���
			for (int k = 0; k < map[i].length; k++) {
				if (map[i][k] == 1) { //1�̸� ��
					bufferGraphics.setColor(new Color(75, 75, 75));
					bufferGraphics.fillRect(30 * k + x, 30 * i + y, 30, 30);
				}
				else if (map[i][k] == 2) { //2�� ��
					bufferGraphics.setColor(new Color(75, 75, 75));
					if (map[i-1][k] != 2) {
						bufferGraphics.fillOval(30 * k + x, 30 * i + y + 15, 30, 30);
					}
					else {
						bufferGraphics.fillRect(30 * k + x, 30 * i + y, 30, 30);
					}
				}
				else if (map[i][k] == 3) { //3�̸� ���� ��

				}
				else if (map[i][k] == 4) { //4�� �ʱ� ��ġ��
					bufferGraphics.setColor(Color.magenta);
					bufferGraphics.fillRect(30 * k + x, 30 * i + y, 30, 30);
				}
				else if (map[i][k] == 5) { //5�� ��
					bufferGraphics.setColor(Color.RED);
					bufferGraphics.fillRect(30 * k + x, 30 * i + y, 30, 30);
				}
				else if (map[i][k] == 6) { //6�̸� ���̺� ����Ʈ
					bufferGraphics.setColor(Color.GREEN);
					bufferGraphics.fillRect(30 * k + x, 30 * i + y, 30, 30);
				}
				else if (map[i][k] == 7) { //7�̸� 7�̸� ������ ���̺�
					bufferGraphics.setColor(Color.BLUE);
					bufferGraphics.fillRect(30 * k + x, 30 * i + y, 30, 30);
				}
				else if (map[i][k] == 8) { //8�̸� ��������
					bufferGraphics.setColor(Color.YELLOW);
					bufferGraphics.fillRect(30 * k + x, 30 * i + y, 30, 30);
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
		
		int b;
		for (b = 0; b < index.length; b += 2) {
			if (index[b] == 0 && index[b + 1] == 0) {
				break;
			}
		}
		
		int[] result = new int[b];
		
		for (int c = 0; c < result.length; c++) {
			result[c] = index[c];
		}
		
		System.out.println(index.length);
		System.out.println(result.length);
		return result;
	}
	
	public void update(Graphics g) {
		paint(g);
	}
}
