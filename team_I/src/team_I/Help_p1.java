package team_I;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Help_p1 extends JFrame {
	private static final long serialVersionUID = 1L;

	public Help_p1() {
		this.initial(); //������ ����
		
		this.add(nextButton()); //���� ��ư
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
                    new JLabel(new ImageIcon("resources/help_p1.png"));
		background.setBounds(0, 0, 800, 600); //�� ��ġ ����
		
		return background; //�� ����
	}
	
	private JButton nextButton() {
		ImageIcon img = new ImageIcon("resources/nextButton.png"); //�̹��� ����
		
		JButton next = new JButton(img); //��ư ����
		next.addActionListener(new ActionListener() { //������ ���
			public void actionPerformed(ActionEvent e) {
				new Help_p2(); //����������
				setVisible(false); //���� ������ �Ⱥ��̰�
			}
		});
		LineBorder boader = new LineBorder(new Color(75, 75, 75));
		next.setBorder(boader);
		next.setPreferredSize(new Dimension(32, 32)); //��ư ũ�� ����
		next.setBounds(745, 554, 32, 32); //��ġ ����W
		
		return next; //��ư ����
	}
	
	private JButton returnButton() {
		ImageIcon img = new ImageIcon("resources/returnButton.png"); //�̹��� ����
		
		JButton returnBotton = new JButton(img); //�̹����� �� ��ư ����
		returnBotton.addActionListener(new ActionListener() { //������ ���
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		returnBotton.setBorderPainted(false); //��ư �׵θ� ��������
		returnBotton.setPreferredSize(new Dimension(91, 23)); //��ư ũ�� ����
		returnBotton.setBounds(9, 9, 91, 23); //��ư ��ġ ����
		
		return returnBotton;
	}
}
