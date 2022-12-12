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
		setTitle("ESCAPE BALL"); //Ÿ��Ʋ ����
		setSize(800, 600);
		setLocationRelativeTo(null); //ȭ�� �����
		setResizable(false);
		setLayout(null);
	}
	
	private JButton returnButton() {
		JButton exit = new JButton("���ư���");
		exit.addActionListener(new ActionListener() { //������ ���
			public void actionPerformed(ActionEvent e) {
				new Start(); //���α׷� ����
				setVisible(false);
			}
		});
		exit.setBounds(12, 10, 91, 23); //��ġ����
		
		return exit;
	}
}
