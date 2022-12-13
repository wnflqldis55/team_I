package team_I;

import javax.swing.JFrame;
import javax.swing.JLabel;

import javazoom.jl.player.Player;

import javax.swing.ImageIcon;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedInputStream;
import java.io.FileInputStream;

public class MainFrame extends JFrame implements KeyListener {
	private static final long serialVersionUID = 1L;
	private Player player;
	
	public MainFrame() {
		initial();
		
		this.addKeyListener(this); //키보드 리스너
		requestFocus(); //키이벤트를 받을 컴포넌트로 설정
		setFocusable(true); //키 이벤트 우선 입력

		add(start());
		add(backGroundImage()); //배경 이미지
		
		setVisible(true); //프레임 보이게
		bgmPlay(); //배경음악 재생
	}
	
	private void initial() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("ESCAPE BALL"); //타이틀 지정
		setSize(800, 600);
		setLocationRelativeTo(null); //화면 가운데로
		setResizable(false);
		setLayout(null);
	}
	
	private void bgmPlay() {
		try {
			FileInputStream fileInputStream = new FileInputStream("resources/Tongtong.mp3");
            BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
            player = new Player(bufferedInputStream);
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
        
        new Thread() {
            public void run() {
                try {
                	do {
                		player.play();
                		FileInputStream fileInputStream = new FileInputStream("resources/Tongtong.mp3");
                        BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
                        player = new Player(bufferedInputStream);
                	} while(true);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }.start();
    }
	
	private JLabel backGroundImage() {
		JLabel background = new JLabel(new ImageIcon("resources/mainImage.png"));
		background.setBounds(0, 0, 800, 600);
		
		return background;
	}
	
	private JLabel start() {
		JLabel start = new JLabel(new ImageIcon("resources/start.png"));
		start.setBounds(270, 350, 247, 100);
		
		return start;
	}

	public void keyTyped(KeyEvent e) {

	}

	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_SPACE) { //스페이스바면
			new Home();
			this.setVisible(false);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {

	}
}
