package team_I;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Stage extends JFrame {
	Map map = new Map();
	private static final long serialVersionUID = 1L;

	public Stage() {
		initial();
	
		add(returnButton()); //돌아가기 버튼
		
		map.setBounds(0, 0, 800, 600);
		this.add(map);
		
		this.setVisible(true);
	}
	
	private void initial() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("ESCAPE BALL"); //타이틀 지정
		setSize(800, 600);
		setLocationRelativeTo(null); //화면 가운데로
		setResizable(false);
		setLayout(null);
	}
	
	private JButton returnButton() {
		JButton exit = new JButton("돌아가기");
		exit.addActionListener(new ActionListener() { //리스너 등록
			public void actionPerformed(ActionEvent e) {
				new Start(); //프로그램 종료
				setVisible(false);
			}
		});
		exit.setBounds(12, 10, 91, 23); //위치지정
		
		return exit;
	}
}