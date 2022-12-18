package team_I;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Help_p2 extends JFrame {
	private static final long serialVersionUID = 1L;
	Help_sub help_sub = new Help_sub(); //������ ���� ���� ĵ����

	public Help_p2() {
		this.initial(); //������ ����
		
		this.add(prevButton()); //���� ��ư
		this.add(nextButton()); //���� ��ư
		this.add(returnButton()); //���ư��� ��ư
		this.add(backGroundImage()); //ĵ���� �߰�
		
		this.setVisible(true); //������ ���̰�
	}
	
	private void initial() { //����� ������ ���� �Լ�
		this.setTitle("ESCAPE BALL"); //Ÿ��Ʋ ����
		this.setSize(814, 637); //������ ����
		
		this.setLocationRelativeTo(null); //ȭ�� �����
		this.setResizable(false); //������ ������ ���� �Ұ���
		this.setLayout(null); //�ۼַ�Ʈ ���̾ƿ�
	}
	
	private Help_sub backGroundImage() { //��� �̹���
		help_sub.setBounds(0, 0, 800, 600); //ĵ���� ��ġ ����
		
		return help_sub; //�� ����
	}
	
	private JButton prevButton() {
		ImageIcon img = new ImageIcon("resources/prevButton.png"); //�̹��� ����
		
		JButton prev = new JButton(img); //��ư ����
		prev.addActionListener(new ActionListener() { //������ ���
			public void actionPerformed(ActionEvent e) {
				new Help_p1(); //����������
				setVisible(false); //���� ������ �Ⱥ��̰�
			}
		});
		LineBorder boader = new LineBorder(new Color(75, 75, 75));
		prev.setBorder(boader);
		prev.setPreferredSize(new Dimension(32, 32)); //��ư ũ�� ����
		prev.setBounds(710, 554, 32, 32); //��ġ ����W
		
		return prev; //��ư ����
	}
	
	private JButton nextButton() {
		ImageIcon img = new ImageIcon("resources/nextButton.png"); //�̹��� ����
		
		JButton next = new JButton(img); //��ư ����
		next.addActionListener(new ActionListener() { //������ ���
			public void actionPerformed(ActionEvent e) {
				new Help_p3(); //����������
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
