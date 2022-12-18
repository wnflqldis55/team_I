package team_I;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Setting extends JFrame {
	private static final long serialVersionUID = 1L;
	private Color selectColor = new Color(75, 75, 75); //�ʱ� ����
	private JButton colorSelectButton = new JButton(); //��ư ����
	
	public Setting() {
		this.initial(); //������ ����
		
		this.add(BGM()); //���ư��� ��ư
		this.add(BGMonoff()); //���ư��� ��ư
		this.add(colorButton()); //���ư��� ��ư
		this.add(colorSelectButton()); //���ư��� ��ư
		this.add(returnButton()); //���ư��� ��ư
		this.add(backGroundImage()); //��� �̹���

		this.setVisible(true); //������ ���̰�
	}
	
	private void initial() { //����� ������ ���� �Լ�
		this.setTitle("ESCAPE BALL"); //Ÿ��Ʋ ����
		this.setSize(814, 637); //������ ����
		
		this.setLocationRelativeTo(null); //ȭ�� �����
		this.setResizable(false); //������ ������ ���� �Ұ���
		this.setLayout(null); //�ۼַ�Ʈ ���̾ƿ�
	}
	
	private JLabel backGroundImage() { //��� �̹���
		JLabel background = //��� �̹��� �󺧿� �߰�
                    new JLabel(new ImageIcon("resources/settingFrame.png"));
		background.setBounds(0, 0, 800, 600); //�� ��ġ ����
		
		return background; //�� ����
	}
	
	private JButton returnButton() {
		ImageIcon img = new ImageIcon("resources/returnButton.png"); //�̹��� ����
		
		JButton returnBotton = new JButton(img); //�̹����� �� ��ư ����
		returnBotton.addActionListener(new ActionListener() { //������ ���
			public void actionPerformed(ActionEvent e) {
				setVisible(false); //���� ������ �Ⱥ��̰�
			}
		});
		returnBotton.setBorderPainted(false); //��ư �׵θ� ��������
		returnBotton.setPreferredSize(new Dimension(91, 23)); //��ư ũ�� ����
		returnBotton.setBounds(9, 9, 91, 23); //��ư ��ġ ����
		
		return returnBotton; //��ư ����
	}
	
	private JLabel BGM() {
		JLabel BGM = //��� �̹��� �󺧿� �߰�
                new JLabel(new ImageIcon("resources/BGM.png"));
		BGM.setBounds(55, 95, 114, 22); //�� ��ġ ����
	
		return BGM; //�� ����
	}
	
	private JLabel BGMonoff() {
		JLabel BGM = //��� �̹��� �󺧿� �߰�
                new JLabel(new ImageIcon("resources/On_Off.png"));
		BGM.setBounds(180, 90, 49, 30); //�� ��ġ ����
	
		return BGM; //�� ����
	}
	
	private JButton colorButton() {
		ImageIcon img = new ImageIcon("resources/colorselectButton.png"); //�̹��� ����
		
		JButton colorButton = new JButton(img); //�̹����� �� ��ư ����
		colorButton.addActionListener(new ActionListener() { //������ ���
			public void actionPerformed(ActionEvent e) {
				selectColor = JColorChooser.showDialog(null, "���� ����", selectColor); //���� ���̾�α� ����
				colorSelectButton.setBackground(selectColor); //���� �� �ٲٱ�
			}
		});
		colorButton.setBorderPainted(false); //��ư �׵θ� ��������
		colorButton.setPreferredSize(new Dimension(91, 23)); //��ư ũ�� ����
		colorButton.setBounds(54, 145, 91, 23); //��ư ��ġ ����
		
		return colorButton; //��ư ����
	}
	
	private JButton colorSelectButton() {
		colorSelectButton.setBackground(selectColor); //selectColor ���� ��ư ����
		colorSelectButton.setBorderPainted(false); //��ư �׵θ� ��������
		colorSelectButton.setPreferredSize(new Dimension(50, 50)); //��ư ũ�� ����
		colorSelectButton.setBounds(54, 177, 50, 50); //��ư ��ġ ����
		
		return colorSelectButton; //��ư ����
	}
}
