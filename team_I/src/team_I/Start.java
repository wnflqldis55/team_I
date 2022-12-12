package team_I;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Start extends JFrame {
	private static final long serialVersionUID = 1L;

	public Start() {
		initial();
		
		add(StageButton("Stage 1")); //스테이지1 버튼
		add(returnButton()); //돌아가기 버튼
		
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
	
	private JButton StageButton(String name) {
		JButton stageButton = new JButton(name);
		stageButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Stage();
				setVisible(false);
			}
		});
		stageButton.setBounds(337, 77, 91, 23);
		
		return stageButton;
	}
	
	private JButton returnButton() {
		JButton back = new JButton("돌아가기");
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new MainFrame();
				setVisible(false);
			}
		});
		back.setBounds(12, 10, 91, 23);
		
		return back;
	}
}
