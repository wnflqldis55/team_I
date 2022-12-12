package team_I;

import javax.swing.JFrame;
import javax.swing.JLabel;

import javazoom.jl.player.Player;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.awt.event.ActionEvent;

public class MainFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	private Player player;
	
	public MainFrame() {
		initial();
		
		add(startButton()); //���� ��ư
		add(helpButton()); //���� ��ư
		add(settingBotton()); //���� ��ư
		
		add(exitButton()); //���� ��ư
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
	
	private JButton startButton() {
		JButton start = new JButton("�����ϱ�");
		start.addActionListener(new ActionListener() { //��ư ������ ���
			public void actionPerformed(ActionEvent e) {
				new Start(); //Start �ǳ� ����
				setVisible(false); //������ �Ⱥ��̰�
			}
		});
		start.setBounds(369, 374, 91, 23); //��ġ����
		
		return start;
	}
	
	private JButton helpButton() {
		JButton help = new JButton("����");
		help.addActionListener(new ActionListener() { //������ ���
			public void actionPerformed(ActionEvent e) {
				new Help(); //Help�ǳ� ����
				setVisible(false); //���� �ǳ� �Ⱥ��̰�
			}
		});
		help.setBounds(369, 422, 91, 23); //��ġ ����
		
		return help;
	}
	private JButton settingBotton() {
		JButton setting = new JButton("����");
		setting.addActionListener(new ActionListener() { //������ ���
			public void actionPerformed(ActionEvent e) {
				new Setting(); //Setting�ǳ� ����
				setVisible(false); //���� �ǳ� �Ⱥ��̰�
			}
		});
		setting.setBounds(369, 472, 91, 23); //��ġ ����
		
		return setting;
	}
	
	private JButton exitButton() {
		JButton exit = new JButton("�����ϱ�");
		exit.addActionListener(new ActionListener() { //������ ���
			public void actionPerformed(ActionEvent e) {
				System.exit(0); //���α׷� ����
			}
		});
		exit.setBounds(12, 10, 91, 23); //��ġ����
		
		return exit;
	}
}
