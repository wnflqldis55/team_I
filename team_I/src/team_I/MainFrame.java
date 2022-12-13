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
		
		this.addKeyListener(this); //Ű���� ������
		requestFocus(); //Ű�̺�Ʈ�� ���� ������Ʈ�� ����
		setFocusable(true); //Ű �̺�Ʈ �켱 �Է�

		add(start());
		add(backGroundImage()); //��� �̹���
		
		setVisible(true); //������ ���̰�
		bgmPlay(); //������� ���
	}
	
	private void initial() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("ESCAPE BALL"); //Ÿ��Ʋ ����
		setSize(800, 600);
		setLocationRelativeTo(null); //ȭ�� �����
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
		if (e.getKeyCode() == KeyEvent.VK_SPACE) { //�����̽��ٸ�
			new Home();
			this.setVisible(false);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {

	}
}
