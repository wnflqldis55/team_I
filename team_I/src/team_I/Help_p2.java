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
		add(returnButton()); //돌아가기 버튼
		add(title()); //타이틀
		
		help_sub.setBounds(0, 0, 800, 600);
		add(help_sub);
		
		setVisible(true);
	}
	
	private void initial() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("ESCAPE BALL"); //타이틀 지정
		setSize(800, 600);
		setLocationRelativeTo(null); //화면 가운데로
		setResizable(false);
		setLayout(null);
	}
	
	private JButton prevButton() {
		JButton prev = new JButton("<");
		prev.addActionListener(new ActionListener() { //리스너 등록
			public void actionPerformed(ActionEvent e) {
				new Help_p1(); //이전 페이지
				setVisible(false);
			}
		});
		prev.setBounds(680, 513, 41, 40);
		add(prev);
		
		return prev;
	}
	
	private JButton nextButton() {
		JButton next = new JButton(">");
		next.addActionListener(new ActionListener() { //리스너 등록
			public void actionPerformed(ActionEvent e) {
				new Help_p3(); //다음페이지
				setVisible(false);
			}
		});
		next.setBounds(733, 513, 41, 40);
		add(next);
		
		return next;
	}
	
	private JButton returnButton() {
		JButton exit = new JButton("돌아가기");
		exit.addActionListener(new ActionListener() { //리스너 등록
			public void actionPerformed(ActionEvent e) {
				new Home(); //홈으로
				setVisible(false);
			}
		});
		exit.setBounds(12, 10, 91, 23); //위치지정
		
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
