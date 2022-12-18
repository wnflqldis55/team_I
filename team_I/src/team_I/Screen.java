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
	private User user = null; //유저
	private boolean left = false; //왼쪽 키
	private boolean right = false; //오른쪽 키
	private boolean up = false; //위쪽 키
	private boolean down = false; //아래쪽 키
	
	public Screen(int[][] map) {
		this.addKeyListener(this); //키보드 리스너
		this.map = map;
		user = new User();
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
		
		drawMap();
		
		if (user != null) {
			bufferGraphics.setColor(new Color(75, 75, 75));
			bufferGraphics.fillOval(user.getLeft(), user.getTop(), user.getWidth(), user.getHeight()); //유저 그리기
		}
		g.drawImage(offScreen, 0, 0, this); //컴포넌트에 그리기
		
		int i = Math.round(user.getCenter().y / 30); //현재 위치한 블럭 y
 		int k = Math.round((user.getCenter().x - x) / 30); //현재 위치한 블럭 x
 		System.out.println("인덱스(x, y): " + k + ", " + i);
	}

	public void keyTyped(KeyEvent e) {
		
	}
	
	private void processKey() { //키 이벤트 함수
		if(left) { //왼쪽
			if (x < 0 && LeftCheck() == false && user.getCenter().x == 405) {
				//x가 0보다 작고, 왼쪽에 장애물이 없으며 유저가 화면의 가운데임(디폴트)
				x += 5; //화면의 x를 오른쪽으로 밀어 왼쪽으로 가게끔
				user.setCenter(user.getCenter().x, user.getCenter().y); //유저 다시 그리기
			}
			else if (LeftCheck() == false && x <= 0) { //왼쪽에 장애물이 없고 x가 0보다 작음(화면이 맵의 왼쪽 끝일 경우)
				user.setCenter(user.getCenter().x - 5, user.getCenter().y); //유저가 왼쪽으로 움직임
			}
			else if (LeftCheck() == false && user.getCenter().x != 405) {
				//왼쪽에 장애물이 없고 유저가 가운데가 아님(화면이 오른쪽 끝이고 유저가 가운데가 아닐 경우)
				user.setCenter(user.getCenter().x - 5, user.getCenter().y); //유저를 왼쪽으로 움직임
			}
			if (fall == false && jump == false && bottomCheck() == false) { //공중이 아니며 아래쪽에 장애물이 없음
				fall = true; //떨어지는 중으로 설정
				fall(); //떨어짐
			}
		}
		
		if(right) { //오른쪽
			if ((-1 * map[1].length * 30) < x - 810 && RightCheck() == false && user.getCenter().x == 405) {
				//x-800(화면크기)이 맵의 크기의 음수보다 작고 오른쪽에 장애물이 없으며 유저가 가운데임(디폴트)
				x -= 5; //화면의 x를 왼쪽으로 밀어 오른쪽으로 가게끔
				user.setCenter(user.getCenter().x, user.getCenter().y); //유저 다시 그리기
			}
			else if (RightCheck() == false &&  map[1].length * 30 > x - 810) {
				//오른쪽에 장애물이 없고 x-800(화면크기)이 맵의 가로보다 작음(화면이 맵의 오른쪽 끝일 경우)
				user.setCenter(user.getCenter().x + 5, user.getCenter().y); //유저가 오른쪽으로 움직임
			}
			else if (RightCheck() == false && user.getCenter().x != 405) {
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
			if (jump != false) { //공중이 아닐 경우
				up = false;
				allDown();
			}
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
	
	public void allDown() {
		int i = Math.round(user.getCenter().y / 30); //현재 위치한 블럭 y
 		int k = Math.round((user.getCenter().x - x) / 30); //현재 위치한 블럭 x
 		
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
	
	public boolean LeftCheck() { //왼쪽 충돌 체크
		int i = Math.round(user.getCenter().y / 30); //현재 위치한 블럭 y
 		int k = Math.round((user.getLeft() - x) / 30); //현재 위치한 블럭 x
 		
 		if (k > 0) {
 			if (i < 20 && i >= 0) {
	 			if (map[i][k] != 0 && map[i][k] != 2 && map[i][k] != 6 && map[i][k] != 7)  { //장애물이 있을때
	 				reboot(i, k);
	 				fire(i, k);
	 				move(i, k);
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
 				if (map[i][k] != 0 && map[i][k] != 2 && map[i][k] != 6 && map[i][k] != 7) { //장애물이 있을때
 					reboot(i, k);
 					fire(i, k);
 					move(i, k);
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
			if (map[i][k] != 0 && map[i][k] != 2 && map[i][k] != 6 && map[i][k] != 7)  { //장애물이 있을 때
				reboot(i, k);
				fire(i, k);
				move(i, k);
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
			if (map[i][k] != 0 && map[i][k] != 2 && map[i][k] != 6 && map[i][k] != 7)  { //유저의 아래쪽에 장애물이 있을 때
				reboot(i, k);
				fire(i, k);
				move(i, k);
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
		System.out.println("clear"); //출력
		String[] answer = {"확인", "다시하기"}; //다이얼로그 버튼 설정
		int result = JOptionPane.showOptionDialog(this, " * clear * ", "clear" //내용, 타이틀 설정
				,JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, answer, null );
		
		if (result == 1) { //다시하기 선택 시
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
	
	public void over() { //게임 오버 시
		System.out.println("game over"); //출력
		if (savePoint) {
			String[] answer = {"예", "처음부터"}; //다이얼로그 버튼 설정
			int choice = JOptionPane.showOptionDialog(this, " 저장된 위치에서 시작하시겠습니까? ", "game over" //내용, 타이틀 설정
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
			String[] answer = {"다시하기"}; //다이얼로그 버튼 설정
			JOptionPane.showOptionDialog(this, " ~ game over ~ ", "game over" //내용, 타이틀 설정
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
			System.out.println("처음으로");
		}
	}
	
	public void fire(int i, int k) {
		if (map[i][k] == 5) {
			over();
		}
	}
	
	public void save() {
		System.out.println("save"); //출력
		int i = Math.round(user.getCenter().y / 30); //현재 위치한 블럭 y
 		int k = Math.round((user.getCenter().x - x) / 30); //현재 위치한 블럭 x
 		
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
	
	public void drawMap() { //맵그리기
		for (int i = 0; i < map.length; i++) { //임시 그리드
			for (int k = 0; k < map[i].length; k++) {
				if (map[i][k] == 1) { //1이면 땅
					bufferGraphics.setColor(new Color(75, 75, 75));
					bufferGraphics.fillRect(30 * k + x, 30 * i + y, 30, 30);
				}
				else if (map[i][k] == 2) { //2면 문
					bufferGraphics.setColor(new Color(75, 75, 75));
					if (map[i-1][k] != 2) {
						bufferGraphics.fillOval(30 * k + x, 30 * i + y + 15, 30, 30);
					}
					else {
						bufferGraphics.fillRect(30 * k + x, 30 * i + y, 30, 30);
					}
				}
				else if (map[i][k] == 3) { //3이면 투명 블럭

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
				else if (map[i][k] == 7) { //7이면 7이면 마지막 세이브
					bufferGraphics.setColor(Color.BLUE);
					bufferGraphics.fillRect(30 * k + x, 30 * i + y, 30, 30);
				}
				else if (map[i][k] == 8) { //8이면 랜덤워프
					bufferGraphics.setColor(Color.YELLOW);
					bufferGraphics.fillRect(30 * k + x, 30 * i + y, 30, 30);
				}
			}
		}
	}
	
	public int[] find(int num) { //해당 블럭 번호 찾기
		int[] index = new int[20];
		int a = 0;
		
		for (int i = 0; i < map.length; i++) { //임시 그리드
			for (int k = 0; k < map[i].length; k++) {
				if (map[i][k] == num) { //1이면 땅
					bufferGraphics.setColor(Color.BLACK);
					bufferGraphics.fillRect(30 * k, 30 * i, 30, 30);
					index[a] = i;
					index[a+1] = k;
					a += 2;
				}
				else if (map[i][k] == num) { //2면 문
					bufferGraphics.setColor(Color.BLACK);
					if (map[i-1][k] != num) {
						bufferGraphics.fillOval(30 * k, 30 * i + 15, 30, 30);
					}
					else {
						bufferGraphics.fillRect(30 * k, 30 * i, 30, 30);
					}
				}
				else if (map[i][k] == num) { //4면 초기 위치로
					bufferGraphics.setColor(Color.lightGray);
					bufferGraphics.drawRect(30 * k, 30 * i, 30, 30);
					index[a] = i;
					index[a+1] = k;
					a += 2;
				}
				else if (map[i][k] == num) { //4면 초기 위치로
					bufferGraphics.setColor(Color.magenta);
					bufferGraphics.fillRect(30 * k, 30 * i, 30, 30);
					index[a] = i;
					index[a+1] = k;
					a += 2;
				}
				else if (map[i][k] == num) { //5면 불
					bufferGraphics.setColor(Color.RED);
					bufferGraphics.fillRect(30 * k, 30 * i, 30, 30);
					index[a] = i;
					index[a+1] = k;
					a += 2;
				}
				else if (map[i][k] == num) { //6이면 세이브 포인트
					bufferGraphics.setColor(Color.GREEN);
					bufferGraphics.fillRect(30 * k, 30 * i, 30, 30);
					index[a] = i;
					index[a+1] = k;
					a += 2;
				}
				else if (map[i][k] == num) { //7이면 랜덤워프
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
