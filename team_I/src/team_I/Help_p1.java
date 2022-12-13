package team_I;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextPane;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Help_p1 extends JFrame implements KeyListener {
	private static final long serialVersionUID = 1L;

	public Help_p1() {
		initial();
		
		this.addKeyListener(this); //키보드 리스너
		requestFocus(); //키이벤트를 받을 컴포넌트로 설정
		setFocusable(true); //키 이벤트 우선 입력
		
		nextButton(); //다음 버튼
		add(returnButton()); //돌아가기 버튼
		add(title()); //타이틀

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
	
	private JButton nextButton() {
		JButton next = new JButton(">");
		next.addActionListener(new ActionListener() { //리스너 등록
			public void actionPerformed(ActionEvent e) {
				new Help_p2(); //다음페이지
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

	public void keyTyped(KeyEvent e) {
		
	}

	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			new Help_p2(); //다음페이지
			setVisible(false);
		}
	}

	public void keyReleased(KeyEvent e) {
		
	}
}
