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
		
		this.addKeyListener(this); //Ű���� ������
		requestFocus(); //Ű�̺�Ʈ�� ���� ������Ʈ�� ����
		setFocusable(true); //Ű �̺�Ʈ �켱 �Է�
		
		add(startButton()); //���� ��ư
		add(helpButton()); //���� ��ư
		add(settingBotton()); //���� ��ư
		
		add(exitButton()); //���� ��ư
		
		setVisible(true); //������ ���̰�
	}
	
	private void initial() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("ESCAPE BALL"); //Ÿ��Ʋ ����
		setSize(800, 600);
		setLocationRelativeTo(null); //ȭ�� �����
		setResizable(false);
		setLayout(null);
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
				new Help_p1(); //Help�ǳ� ����
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

	public void keyTyped(KeyEvent e) {
		
	}

	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_SPACE) { //�����̽��ٸ�
			new Start(); //Start �ǳ� ����
			setVisible(false); //������ �Ⱥ��̰�
		}
	}

	public void keyReleased(KeyEvent e) {
		
	}
}
