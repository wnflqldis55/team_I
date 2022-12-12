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

public class Map extends Canvas implements KeyListener {
	private static final long serialVersionUID = 1L;
	private int x = 0; //화면 x
	private int y = 0; //화면 y
	private int finalX = 0; //화면 x
	private int finalY = 0; //화면 y
	private int pointX = 405; //화면 x
	private int pointY = 375; //화면 y
	private boolean savePoint = false;
	private Graphics bufferGraphics; //더블버퍼링 임시 그래픽
	private Image offScreen; //더블버퍼링 이미지
	private Dimension dim; //화면 사이즈
	private boolean jump = false; //공중에 떠있는 상태
	private boolean fall = false; //떨어지는 상태
	private int map[][]; //맵 배열
	private User user = new User(); //유저
	private boolean left = false; //왼쪽 키
	private boolean right = false; //오른쪽 키
	private boolean up = false; //위쪽 키
	private boolean down = false; //아래쪽 키
	
	public Map() {
		this.addKeyListener(this); //키보드 리스너
		//맵 생성
		map = new int[][] {{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
					   	   {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
					       {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
					       {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
					       {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
					       {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
					       {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
					       {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
					       {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
					       {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
					       {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
					       {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 0, 0, 1, 0, 0, 0, 0, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0},
					       {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 5, 0, 0, 0, 0, 6, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0},
					       {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
					       {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
					       {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
					       {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
					       {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
					       {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
					       {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}};
		user.setCenter(pointX, pointY); //유저 센터 지정
		requestFocus(); //키이벤트를 받을 컴포넌트로 설정
		setFocusable(true); //키 이벤트 우선 입력
	}
	
	public void paint(Graphics g) {
		processKey(); //키 이벤트
		this.dim = getSize(); //스크린 사이즈
		this.offScreen = createImage(dim.width, dim.height); //더블버퍼링 지정
		this.bufferGraphics = this.offScreen.getGraphics();
		
		bufferGraphics.clearRect(0, 0, dim.width, dim.height); //화면 지우기
		bufferGraphics.setColor(Color.WHITE);
		bufferGraphics.fillRect(0, 0, dim.width, dim.height);
		
		for (int i = 0; i < map.length; i++) { //맵 배열에 따라 앱 생성
			for (int k = 0; k < map[i].length; k++) {
				if (map[i][k] == 1) { //1이면 땅
					bufferGraphics.setColor(Color.BLACK);
					bufferGraphics.fillRect(30 * k + x, 30 * i + y, 30, 30);
				}
				else if (map[i][k] == 2) { //2면 문
					bufferGraphics.setColor(Color.BLACK);
					if (map[i-1][k] != 2) {
						bufferGraphics.fillOval(30 * k + x, 30 * i + y + 15, 30, 30);
					}
					else {
						bufferGraphics.fillRect(30 * k + x, 30 * i + y, 30, 30);
					}
				}
				else if (map[i][k] == 4) { //4면 초기 위치로
					bufferGraphics.setColor(Color.magenta);
					bufferGraphics.fillRect(30 * k + x, 30 * i + y, 30, 30);
					
				}
				else if (map[i][k] == 5) { //5면 불
					bufferGraphics.setColor(Color.RED);
					bufferGraphics.fillRect(30 * k + x, 30 * i + y, 30, 30);
				}
				else if (map[i][k] == 6) { //6이면 세이브 포인트
					bufferGraphics.setColor(Color.GREEN);
					bufferGraphics.fillRect(30 * k + x, 30 * i + y, 30, 30);
				}
			}
		}
		
		for (int i = 0; i < map.length; i++) { //임시 그리드
			for (int k = 0; k < map[i].length; k++) {
				if ((i + k) % 2 == 0) {
					bufferGraphics.setColor(Color.BLACK);
					bufferGraphics.drawRect(30 * k + x, 30 * i + y, 30, 30);
				}
			}
		}
		
		bufferGraphics.fillOval(user.getLeft(), user.getTop(), user.getWidth(), user.getHeight()); //유저 그리기
		g.drawImage(offScreen, 0, 0, this); //컴포넌트에 그리기
		
		int i = Math.round(user.getCenter().y / 30); //현재 위치한 블럭 y
 		int k = Math.round((user.getCenter().x - x) / 30); //현재 위치한 블럭 x
 		System.out.println(i + " " + k);
	}

	public void keyTyped(KeyEvent e) {
		
	}
	
	private void processKey() { //키 이벤트 함수
		if(left) { //왼쪽
			if (x < 0 && LeftCheck() == false && user.getCenter().x == pointX) {
				//x가 0보다 작고, 왼쪽에 장애물이 없으며 유저가 화면의 가운데임(디폴트)
				x += 5; //화면의 x를 오른쪽으로 밀어 왼쪽으로 가게끔
				user.setCenter(user.getCenter().x, user.getCenter().y); //유저 다시 그리기
			}
			else if (LeftCheck() == false && x <= 0) { //왼쪽에 장애물이 없고 x가 0보다 작음(화면이 맵의 왼쪽 끝일 경우)
				user.setCenter(user.getCenter().x - 5, user.getCenter().y); //유저가 왼쪽으로 움직임
			}
			else if (LeftCheck() == false && user.getCenter().x != pointX) {
				//왼쪽에 장애물이 없고 유저가 가운데가 아님(화면이 오른쪽 끝이고 유저가 가운데가 아닐 경우)
				user.setCenter(user.getCenter().x - 5, user.getCenter().y); //유저를 왼쪽으로 움직임
			}
			if (fall == false && jump == false && bottomCheck() == false) { //공중이 아니며 아래쪽에 장애물이 없음
				fall = true; //떨어지는 중으로 설정
				fall(); //떨어짐
			}
		}
		
		if(right) { //오른쪽
			if ((-1 * map[1].length * 30) < x - 800 && RightCheck() == false && user.getCenter().x == pointX) {
				//x-800(화면크기)이 맵의 크기의 음수보다 작고 오른쪽에 장애물이 없으며 유저가 가운데임(디폴트)
				x -= 5; //화면의 x를 왼쪽으로 밀어 오른쪽으로 가게끔
				user.setCenter(user.getCenter().x, user.getCenter().y); //유저 다시 그리기
			}
			else if (RightCheck() == false &&  map[1].length * 30 > x - 800) {
				//오른쪽에 장애물이 없고 x-800(화면크기)이 맵의 가로보다 작음(화면이 맵의 오른쪽 끝일 경우)
				user.setCenter(user.getCenter().x + 5, user.getCenter().y); //유저가 오른쪽으로 움직임
			}
			else if (RightCheck() == false && user.getCenter().x != pointX) {
				//오른쪽에 장애물이 없으며 유저가 화면의 가운데가 아님(화면이 왼쪽 끝이고 유저가 가운데가 아닐 경우)
				user.setCenter(user.getCenter().x + 5, user.getCenter().y); //유저가 오른쪽으로 움직임
			}
			if (fall == false && jump == false && bottomCheck() == false) { //공중이 아니며 아래쪽에 장애물이 없음
				fall = true; //떨어지는 중으로 설정
				fall(); //떨어짐
			}
		}
		
		if(up) { //위쪽
			if (jump == false) { //공중이 아닐 경우
				jump = true; //공중인 상태
				jump(); //점프
			}
		}
		
		if(down) { //아래쪽
			
		}
	}
	
	public void keyPressed(KeyEvent e) { //키를 눌렀을 때
		if (e.getKeyCode() == KeyEvent.VK_LEFT) { //왼쪽 키면
			System.out.println("왼쪽 키 누름"); //출력
			left = true; //왼쪽 true
		}
		
		if (e.getKeyCode() == KeyEvent.VK_UP) { //위쪽 키면
			System.out.println("위쪽 키 누름"); //출력
			up = true; //위쪽 true
		}
		
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) { //오른쪽 키면
			System.out.println("오른쪽 키 누름"); //출력
			right = true; //오른쪽 true
		}
		
		if (e.getKeyCode() == KeyEvent.VK_DOWN) { //아래쪽 키면
			System.out.println("아래쪽 키 누름"); //출력
			down = true; //아래쪽 true
		}
		
		if (e.getKeyCode() == KeyEvent.VK_SPACE) { //스페이스바면
			if (map[user.getCenter().y / 30][(user.getCenter().x - x) / 30] == 2) { //위치가 문일 경우
				System.out.println("clear"); //출력
				this.clear(); //클리어 함수 호출
			} else if (map[user.getCenter().y / 30][(user.getCenter().x - x) / 30] == 6) { //위치가 문일 경우
				System.out.println("save"); //출력
				this.save(); //클리어 함수 호출
			}
		}
		repaint(); //다시그리기
	}
	
	public void jump() { //점프
		int userY = user.getBottom(); //유저의 아래 좌표
		System.out.println("점프"); //출력
		
		if (jump == true) { //공중일 경우
			final Timer timer = new Timer(); //타이머 만들기
			TimerTask timerTask = new TimerTask() {
				public void run() {
					user.setCenter(user.getCenter().x, (int)(user.getCenter().y - 5)); //5px씩 위로 올라감
					repaint(); //다시그리기
					if (TopCheck()) { //위쪽에 장애물이 있을 경우
						try {
							System.out.println("최고점"); //출력
							repaint(); //다시그리기
							Thread.sleep(500); //0.5초 멈춤
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						fall = true; //떨어지는 중으로 설정
						timer.cancel(); //타이머 취소
					} else if (user.getCenter().y <= userY - 100) { //초기 위치에서 100 위로 올라갔을 경우
						try {
							System.out.println("최고점"); //출력
							repaint(); //다시그리기
							Thread.sleep(500); //0.5초 멈춤
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						fall = true; //떨어지는 중으로 설정
						timer.cancel(); //타이며 취소
					}
					fall(); //떨어지는 함수 호출
				}
			};
			timer.schedule(timerTask, 0, 30);
		}
	}
	
	public void fall() { //떨어짐
		System.out.println("떨어짐");
		
		if (fall == true) { //떨어지는 중이면
			final Timer timer = new Timer(); //타이머 생성
			TimerTask timerTask = new TimerTask() {
				public void run() {
					user.setCenter(user.getCenter().x, (int)(user.getCenter().y + 5)); //5px씩 떨어짐
					repaint(); //다시그리기
					if (bottomCheck()) { //아래쪽에 장애물이 있으면
						timer.cancel(); //타이머 취소
						System.out.println("bottom: "+bottomCheck()); //출력
						fall = false; //떨어지는 중이 아님
					}
				}
			};
			timer.schedule(timerTask, 0, 30);
		}
	}
	
	public boolean LeftCheck() { //왼쪽 충돌 체크
		int i = Math.round(user.getCenter().y / 30); //현재 위치한 블럭 y
 		int k = Math.round((user.getLeft() - x) / 30); //현재 위치한 블럭 x
 		
 		if (k > 0) {
 			if (i < 20 && i >= 0) {
	 			if (map[i][k] != 0 && map[i][k] != 2 && map[i][k] != 6)  { //장애물이 있을때
	 				reboot(i, k);
	 				fire(i, k);
	 				save();
					return true; //충돌
				}
	 		}
 		}
 		else if (k <= 0){
 			if (user.getLeft() <= 0) { //유저의 왼쪽이 장애물에 닿으면
				return true; //충돌
 			}
 		}
 		
		return false; //충돌안함
	}
	
	public boolean RightCheck() { //오른쪽 충돌 체크
		int i = Math.round(user.getCenter().y / 30); //현재 위치한 블럭 y
 		int k = Math.round((user.getRight() - x) / 30); //현재 위치한 블럭 x
 		
 		if (map[1].length > k) {
 			if (i < 20 && i >= 0) {
 				if (map[i][k] != 0 && map[i][k] != 2 && map[i][k] != 6) { //장애물이 있을때
 					reboot(i, k);
 					fire(i, k);
 					save();
					return true; //충돌
		 		}
	 		} 
 		}
 		else if (map[1].length <= k) {
			return true; //충돌
 		}
 		
		return false; //충돌안함
	} 
	
	public boolean TopCheck() { //위쪽 충돌 체크
		int i = Math.round(user.getTop() / 30); //현재 위치한 블럭 y
 		int k = Math.round((user.getCenter().x - x) / 30); //현재 위치한 블럭 x
 		
		if (i < 20 && i >= 0) { //화면 안이면
			if (map[i][k] != 0 && map[i][k] != 2 && map[i][k] != 6)  { //장애물이 있을 때
				reboot(i, k);
				fire(i, k);
				save();
				fall = true; //떨어지는 상태
				jump = true; //공중 상태
				return true; //충돌
			}
		}
		else {
			return true;
		}
		
		return false; //충돌안함
	}
	
	public boolean bottomCheck() { //아래쪽 충돌 체크
		int i = Math.round(user.getBottom() / 30); //현재 위치한 블럭 y
 		int k = Math.round((user.getCenter().x - x) / 30); //현재 위치한 블럭 x
 		
		if (i < 20 && i >= 0) { //화면 안이면
			if (map[i][k] != 0 && map[i][k] != 2 && map[i][k] != 6)  { //유저의 아래쪽에 장애물이 있을 때
				reboot(i, k);
				fire(i, k);
				save();
				fall = false; //떨어지는 상태 아님
				jump = false; //공중 아님
				return true; //충돌
			}
		}
		else if (i == 20) {
			over();
		}
		
		return false;  //충돌안함
	}
	
	public void keyReleased(KeyEvent e) { //키를 뗏을 때
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
	
	public void clear() { //클리어 시
		String[] answer = {"스테이지로", "다시하기"}; //다이얼로그 버튼 설정
		int result = JOptionPane.showOptionDialog(this, " * clear * ", "clear" //내용, 타이틀 설정
				,JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, answer, null );
		
		if (result == 0) //스테이지로 선택 시
			new Start(); //스테이지 선택창으로
		else //다시하기 선택 시
			new Stage(); //새 스테이지로
	}
	
	public void over() { //클리어 시
		if (savePoint) {
			x = finalX;
			y = finalY;
			user.setCenter(pointX, pointY);
		}
		else {
			String[] answer = {"스테이지로", "다시하기"}; //다이얼로그 버튼 설정
			int result = JOptionPane.showOptionDialog(this, " ~ game over ~ ", "game over" //내용, 타이틀 설정
					,JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, answer, null );
			
			if (result == 0) //스테이지로 선택 시
				new Start(); //스테이지 선택창으로
			else if (result == 1) //다시하기 선택 시
				new Stage(); //새 스테이지로
			else
				new Start();
			
			x = 0;
			y = 0;
			pointX = 405;
			pointY = 375;
			user.setCenter(pointX, pointY);
		}
	}
	
	public void reboot(int i, int k) {
		if (map[i][k] == 4) {
			x = 0;
			y = 0;
			user.setCenter(pointX, pointY);
			System.out.println("처음으로");
		}
	}
	
	public void save() {
		int i = Math.round(user.getCenter().y / 30); //현재 위치한 블럭 y
 		int k = Math.round((user.getCenter().x - x) / 30); //현재 위치한 블럭 x
 		
		if (map[i][k] == 6) {
			finalX = x;
			finalY = y;
			pointX = user.getCenter().x - 15;
			pointY = user.getCenter().y - 15;
			this.savePoint = true;
			System.out.println("저장");
		}
	}
	
	public void fire(int i, int k) {
		if (map[i][k] == 5) {
			over();
		}
	}
	
	public void update(Graphics g) {
		paint(g);
	}
}
