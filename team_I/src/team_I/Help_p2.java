package team_I;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextPane;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Help_p2 extends JFrame {
	private static final long serialVersionUID = 1L;
	Help_sub help_sub = new Help_sub();

	public Help_p2() {
		initial();
		
		prevButton();
		nextButton();
		add(returnButton()); //���ư��� ��ư
		add(title()); //Ÿ��Ʋ
		
		help_sub.setBounds(0, 0, 800, 600);
		add(help_sub);
		
		setVisible(true);
	}
	
	private void initial() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("ESCAPE BALL"); //Ÿ��Ʋ ����
		setSize(800, 600);
		setLocationRelativeTo(null); //ȭ�� �����
		setResizable(false);
		setLayout(null);
	}
	
	private JButton prevButton() {
		JButton prev = new JButton("<");
		prev.addActionListener(new ActionListener() { //������ ���
			public void actionPerformed(ActionEvent e) {
				new Help_p1(); //���� ������
				setVisible(false);
			}
		});
		prev.setBounds(680, 513, 41, 40);
		add(prev);
		
		return prev;
	}
	
	private JButton nextButton() {
		JButton next = new JButton(">");
		next.addActionListener(new ActionListener() { //������ ���
			public void actionPerformed(ActionEvent e) {
				new Help_p3(); //����������
				setVisible(false);
			}
		});
		next.setBounds(733, 513, 41, 40);
		add(next);
		
		return next;
	}
	
	private JButton returnButton() {
		JButton exit = new JButton("���ư���");
		exit.addActionListener(new ActionListener() { //������ ���
			public void actionPerformed(ActionEvent e) {
				new Home(); //Ȩ����
				setVisible(false);
			}
		});
		exit.setBounds(12, 10, 91, 23); //��ġ����
		
		return exit;
	}
	
	private JTextPane title() {
		JTextPane title = new JTextPane();
		title.setBackground(Color.white);
		title.setForeground(Color.BLACK);
		title.setBounds(270, 20, 246, 54);
		title.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 40));
		title.setText("ESCAPE BALL");
		title.setEnabled(false);
		
		return title;
	}
}
