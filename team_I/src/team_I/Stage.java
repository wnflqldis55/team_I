package team_I;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Stage extends JFrame {
	private static final long serialVersionUID = 1L;
	Map map = new Map();

	public Stage() {
		initial();
	
		add(returnButton()); //���ư��� ��ư
		
		map.setBounds(0, 0, 800, 600);
		this.add(map);
		
		this.setVisible(true);
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
				new Start(); //�������� ��������
				setVisible(false);
			}
		});
		exit.setBounds(12, 10, 91, 23); //��ġ����
		
		return exit;
	}
}