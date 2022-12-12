package team_I;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Setting extends JFrame {
	private static final long serialVersionUID = 1L;

	public Setting() {
		initial();

		add(returnButton());
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
