package team_I;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MainFrame extends JFrame implements KeyListener, MouseListener {
	private static final long serialVersionUID = 1L;
	
	public MainFrame(boolean bgm) {
		this.initial(); //프레임 설정
		
		this.add(settingButton()); //설정 버튼
		this.add(helpButton()); //도움말 버튼
		this.add(exitButton()); //게임종료 버튼
		this.add(backGroundImage()); //배경 이미지

		this.setVisible(true); //프레임 보이게
		BGM setBGM = new BGM(bgm);
		setBGM.run(); //배경음악 재생
	}
	
	private void initial() { //공통된 프레임 설정 함수
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("ESCAPE BALL"); //타이틀 지정
		this.setSize(814, 637); //사이즈 설정
		
		this.setLocationRelativeTo(null); //화면 가운데로
		this.setResizable(false); //프레임 사이즈 변경 불가능
		this.setLayout(null); //앱솔루트 레이아웃
		
		this.addKeyListener(this); //키보드 리스너
		this.addMouseListener(this); //마우스 리스너
		this.requestFocus(); //키이벤트를 받을 컴포넌트로 설정
		this.setFocusable(true); //키 이벤트 우선 입력
	}
	
	private JLabel backGroundImage() { //배경 이미지
		JLabel background = //배경 이미지 라벨에 추가
                    new JLabel(new ImageIcon(getClass().getClassLoader().getResource("mainFrame.png")));
		background.setBounds(0, 0, 800, 600); //라벨 위치 지정
		
		return background; //라벨 리턴
	}

	private JButton settingButton() {
		ImageIcon img = new ImageIcon(getClass().getClassLoader().getResource("settingButton.png")); //이미지 생성
		
		JButton settingBotton = new JButton(img); //이미지가 들어간 버튼 생성
		settingBotton.addActionListener(new ActionListener() { //리스너 등록
			public void actionPerformed(ActionEvent e) {
				new Setting(); //Setting 프레임 생성
			}
		});
		settingBotton.setBorderPainted(false); //버튼 테두리 설정해제
		settingBotton.setPreferredSize(new Dimension(40, 40)); //버튼 크기 지정
		settingBotton.setBounds(738, 543, 40, 40); //버튼 위치 지정
		
		return settingBotton; //버튼 리턴
	}
	
	private JButton helpButton() {
		ImageIcon img = new ImageIcon(getClass().getClassLoader().getResource("helpButton.png")); //이미지 생성
		
		JButton helpButton = new JButton(img); //이미지가 들어간 버튼 생성
		helpButton.addActionListener(new ActionListener() { //리스너 등록
			public void actionPerformed(ActionEvent e) {
				new Help_p1(); //Help_p1 프레임 생성
			}
		});
		helpButton.setBorderPainted(false); //버튼 테두리 설정해제
		helpButton.setPreferredSize(new Dimension(50, 51)); //버튼 크기 지정
		helpButton.setBounds(658, 536, 50, 51); //버튼 위치 지정
		
		return helpButton; //버튼 리턴
	}
	
	private JButton exitButton() {
		ImageIcon img = new ImageIcon(getClass().getClassLoader().getResource("exitButton.png")); //이미지 생성
		
		JButton exitButton = new JButton(img); //이미지가 들어간 버튼 생성
		exitButton.addActionListener(new ActionListener() { //리스너 등록
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		exitButton.setBorderPainted(false); //버튼 테두리 설정해제
		exitButton.setPreferredSize(new Dimension(91, 23)); //버튼 크기 지정
		exitButton.setBounds(9, 9, 91, 23); //버튼 위치 지정
		
		return exitButton; //버튼 리턴
	}

	public void keyTyped(KeyEvent e) {
		
	}

	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			new Start();
			setVisible(false);
		}
	}

	public void keyReleased(KeyEvent e) {
		
	}

	public void mouseClicked(MouseEvent e) {
		new Start();
		setVisible(false);
	}

	public void mousePressed(MouseEvent e) {
		
	}

	public void mouseReleased(MouseEvent e) {
		
	}

	public void mouseEntered(MouseEvent e) {
		
	}

	public void mouseExited(MouseEvent e) {
		
	}
}