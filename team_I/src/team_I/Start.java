package team_I;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;

public class Start extends JFrame {
	JPanel contentPane;
	 
	public Start() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 600);
		getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("Stage 1");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Stage();
				setVisible(false);
			}
		});
		btnNewButton.setBounds(337, 77, 91, 23);
		getContentPane().add(btnNewButton);
		this.setVisible(true);
		
		JButton back = new JButton("\uB4A4\uB85C\uAC00\uAE30");
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new MainFrame();
				setVisible(false);
			}
		});
		back.setBounds(12, 10, 91, 23);
		getContentPane().add(back);
	}
}
