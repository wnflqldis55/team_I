package team_I;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.Dimension;
import java.awt.event.ActionEvent;

public class Start extends JFrame {
	private static final long serialVersionUID = 1L;
	
	public Start() {
		this.initial(); //������ ����
		
		this.add(StageButton("stage1Button", 1)); //��������1 ��ư
		this.add(StageButton("stage2Button", 2)); //��������2 ��ư
		this.add(StageButton("stage3Button", 3)); //��������3 ��ư
		this.add(StageButton("stage4Button", 4)); //��������4 ��ư
		this.add(StageButton("stage5Button", 5)); //��������5 ��ư
		this.add(StageButton("stage6Button", 6)); //��������6 ��ư
		this.add(StageButton("stage7Button", 7)); //��������7 ��ư
		this.add(StageButton("stage8Button", 8)); //��������8 ��ư
		this.add(StageButton("stage9Button", 9)); //��������9 ��ư
		this.add(StageButton("stage10Button", 10)); //��������10 ��ư
		this.add(returnButton()); //���ư��� ��ư
		this.add(backGroundImage()); //��� �̹���
		
		this.setVisible(true); //������ ���̰�
	}
	
	private void initial() { //����� ������ ���� �Լ�
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("ESCAPE BALL"); //Ÿ��Ʋ ����
		this.setSize(814, 637); //������ ����
		
		this.setLocationRelativeTo(null); //ȭ�� �����
		this.setResizable(false); //������ ������ ���� �Ұ���
		this.setLayout(null); //�ۼַ�Ʈ ���̾ƿ�
	}
	
	private JLabel backGroundImage() { //��� �̹���
		JLabel background = //��� �̹��� �󺧿� �߰�
                    new JLabel(new ImageIcon("resources/startFrame.png"));
		background.setBounds(0, 0, 800, 600); //�� ��ġ ����
		
		return background; //�� ����
	}
	
	private JButton StageButton(String name, int num) {
		String path = "resources/" + name + ".png"; //�̹��� ���
		ImageIcon img = new ImageIcon(path); //�̹��� ����
		
		JButton stageButton = new JButton(img); //�̹����� �� ��ư ����
		stageButton.addActionListener(new ActionListener() { //������ ���
			public void actionPerformed(ActionEvent e) {
				switch (num) {
					case 1:
						new Stage1(); //Stage1 ����
						break;
					case 2:
						new Stage2(); //Stage2 ����
						break;
					case 3:
						new Stage3(); //Stage3 ����
						break;
					case 4:
						new Stage4(); //Stage3 ����
						break;
					case 5:
						new Stage5(); //Stage3 ����
						break;
					case 6:
						new Stage6(); //Stage3 ����
						break;
					case 7:
						new Stage6(); //Stage3 ����
						break;
					case 8:
						new Stage6(); //Stage3 ����
						break;
					case 9:
						new Stage6(); //Stage3 ����
						break;
					case 10:
						new Stage6(); //Stage3 ����
						break;
				}
			}
		});
		int y = (num - 1) * 40; //���� ��ġ ���
		stageButton.setBounds(337, 120 + y, 91, 23); //��ġ ����
		
		return stageButton; //��ư ����
	}
	
	private JButton returnButton() {
		ImageIcon img = new ImageIcon("resources/titleButton.png"); //�̹��� ����
		
		JButton exitButton = new JButton(img); //�̹����� �� ��ư ����
		exitButton.addActionListener(new ActionListener() { //������ ���
			public void actionPerformed(ActionEvent e) {
				new MainFrame(false); //MainFrame ������ ����
				setVisible(false); //���� ������ �Ⱥ��̰�
			}
		});
		exitButton.setBorderPainted(false); //��ư �׵θ� ��������
		exitButton.setPreferredSize(new Dimension(91, 23)); //��ư ũ�� ����
		exitButton.setBounds(9, 9, 91, 23); //��ư ��ġ ����
		
		return exitButton; //��ư ����
	}
}
