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
		this.initial(); //������ ����
		
		this.add(settingButton()); //���� ��ư
		this.add(helpButton()); //���� ��ư
		this.add(exitButton()); //�������� ��ư
		this.add(backGroundImage()); //��� �̹���

		this.setVisible(true); //������ ���̰�
		BGM setBGM = new BGM(bgm);
		setBGM.run(); //������� ���
	}
	
	private void initial() { //����� ������ ���� �Լ�
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("ESCAPE BALL"); //Ÿ��Ʋ ����
		this.setSize(814, 637); //������ ����
		
		this.setLocationRelativeTo(null); //ȭ�� �����
		this.setResizable(false); //������ ������ ���� �Ұ���
		this.setLayout(null); //�ۼַ�Ʈ ���̾ƿ�
		
		this.addKeyListener(this); //Ű���� ������
		this.addMouseListener(this); //���콺 ������
		this.requestFocus(); //Ű�̺�Ʈ�� ���� ������Ʈ�� ����
		this.setFocusable(true); //Ű �̺�Ʈ �켱 �Է�
	}
	
	private JLabel backGroundImage() { //��� �̹���
		JLabel background = //��� �̹��� �󺧿� �߰�
                    new JLabel(new ImageIcon(getClass().getClassLoader().getResource("mainFrame.png")));
		background.setBounds(0, 0, 800, 600); //�� ��ġ ����
		
		return background; //�� ����
	}

	private JButton settingButton() {
		ImageIcon img = new ImageIcon(getClass().getClassLoader().getResource("settingButton.png")); //�̹��� ����
		
		JButton settingBotton = new JButton(img); //�̹����� �� ��ư ����
		settingBotton.addActionListener(new ActionListener() { //������ ���
			public void actionPerformed(ActionEvent e) {
				new Setting(); //Setting ������ ����
			}
		});
		settingBotton.setBorderPainted(false); //��ư �׵θ� ��������
		settingBotton.setPreferredSize(new Dimension(40, 40)); //��ư ũ�� ����
		settingBotton.setBounds(738, 543, 40, 40); //��ư ��ġ ����
		
		return settingBotton; //��ư ����
	}
	
	private JButton helpButton() {
		ImageIcon img = new ImageIcon(getClass().getClassLoader().getResource("helpButton.png")); //�̹��� ����
		
		JButton helpButton = new JButton(img); //�̹����� �� ��ư ����
		helpButton.addActionListener(new ActionListener() { //������ ���
			public void actionPerformed(ActionEvent e) {
				new Help_p1(); //Help_p1 ������ ����
			}
		});
		helpButton.setBorderPainted(false); //��ư �׵θ� ��������
		helpButton.setPreferredSize(new Dimension(50, 51)); //��ư ũ�� ����
		helpButton.setBounds(658, 536, 50, 51); //��ư ��ġ ����
		
		return helpButton; //��ư ����
	}
	
	private JButton exitButton() {
		ImageIcon img = new ImageIcon(getClass().getClassLoader().getResource("exitButton.png")); //�̹��� ����
		
		JButton exitButton = new JButton(img); //�̹����� �� ��ư ����
		exitButton.addActionListener(new ActionListener() { //������ ���
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		exitButton.setBorderPainted(false); //��ư �׵θ� ��������
		exitButton.setPreferredSize(new Dimension(91, 23)); //��ư ũ�� ����
		exitButton.setBounds(9, 9, 91, 23); //��ư ��ġ ����
		
		return exitButton; //��ư ����
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