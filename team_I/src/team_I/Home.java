package team_I;

import javax.swing.JFrame;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.ActionEvent;

public class Home extends JFrame implements KeyListener {
	private static final long serialVersionUID = 1L;
	
	public Home() {
		initial();
		
		this.addKeyListener(this); //키보드 리스너
		requestFocus(); //키이벤트를 받을 컴포넌트로 설정
		setFocusable(true); //키 이벤트 우선 입력
		
		add(startButton()); //시작 버튼
		add(helpButton()); //도움말 버튼
		add(settingBotton()); //설정 버튼
		
		add(exitButton()); //종료 버튼
		
		setVisible(true); //프레임 보이게
	}
	
	private void initial() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("ESCAPE BALL"); //타이틀 지정
		setSize(800, 600);
		setLocationRelativeTo(null); //화면 가운데로
		setResizable(false);
		setLayout(null);
	}
	
	private JButton startButton() {
		JButton start = new JButton("시작하기");
		start.addActionListener(new ActionListener() { //버튼 리스너 등록
			public void actionPerformed(ActionEvent e) {
				new Start(); //Start 판넬 생성
				setVisible(false); //프레임 안보이게
			}
		});
		start.setBounds(369, 374, 91, 23); //위치지정
		
		return start;
	}
	
	private JButton helpButton() {
		JButton help = new JButton("도움말");
		help.addActionListener(new ActionListener() { //리스너 등록
			public void actionPerformed(ActionEvent e) {
				new Help_p1(); //Help판넬 생성
				setVisible(false); //현재 판넬 안보이게
			}
		});
		help.setBounds(369, 422, 91, 23); //위치 지정
		
		return help;
	}
	private JButton settingBotton() {
		JButton setting = new JButton("설정");
		setting.addActionListener(new ActionListener() { //리스너 등록
			public void actionPerformed(ActionEvent e) {
				new Setting(); //Setting판넬 생성
				setVisible(false); //현재 판넬 안보이게
			}
		});
		setting.setBounds(369, 472, 91, 23); //위치 지정
		
		return setting;
	}
	
	private JButton exitButton() {
		JButton exit = new JButton("종료하기");
		exit.addActionListener(new ActionListener() { //리스너 등록
			public void actionPerformed(ActionEvent e) {
				System.exit(0); //프로그램 종료
			}
		});
		exit.setBounds(12, 10, 91, 23); //위치지정
		
		return exit;
	}

	public void keyTyped(KeyEvent e) {
		
	}

	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_SPACE) { //스페이스바면
			new Start(); //Start 판넬 생성
			setVisible(false); //프레임 안보이게
		}
	}

	public void keyReleased(KeyEvent e) {
		
	}
}
