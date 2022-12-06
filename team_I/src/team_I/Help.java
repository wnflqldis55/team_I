package team_I;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextPane;
import java.awt.Font;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Help extends JFrame {
	 JPanel contentPane;
	
	public Help() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 600);
		this.setVisible(true);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton back = new JButton("\uB4A4\uB85C\uAC00\uAE30");
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new MainFrame();
				setVisible(false);
			}
		});
		back.setBounds(12, 10, 91, 23);
		contentPane.add(back);
		
		JTextPane help = new JTextPane();
		help.setBackground(SystemColor.control);
		help.setBounds(152, 40, 481, 54);
		help.setFont(new Font("µ¸¿ò", Font.BOLD, 20));
		help.setText("\uB3C4\uC6C0\uB9D0\uB3C4\uC6C0\uB9D0\uB3C4\uC6C0\uB9D0\uB3C4\uC6C0\uB9D0\uB3C4\uC6C0\uB9D0\uB3C4\uC6C0\uB9D0\uB3C4\uC6C0\uB9D0\uB3C4\uC6C0\r\n\uB9D0\uB3C4\uC6C0\uB9D0\uB3C4\uC6C0\uB9D0\uB3C4\uC6C0\uB9D0\uB3C4\uC6C0\uB9D0");
		help.setEnabled(false);
		contentPane.add(help);
		
		JTextPane help_content1 = new JTextPane();
		help_content1.setBackground(SystemColor.control);
		help_content1.setBounds(152, 135, 481, 54);
		help_content1.setText("\uB3C4\uC6C0\uB9D0\uB3C4\uC6C0\uB9D0\uB3C4\uC6C0\uB9D0\uB3C4\uC6C0\uB9D0\uB3C4\uC6C0\uB9D0\uB3C4\uC6C0\uB9D0\uB3C4\uC6C0\uB9D0\uB3C4\uC6C0\r\n\uB9D0\uB3C4\uC6C0\uB9D0\uB3C4\uC6C0\uB9D0\uB3C4\uC6C0\uB9D0\uB3C4\uC6C0\uB9D0");
		help_content1.setFont(new Font("µ¸¿ò", Font.BOLD, 20));
		help_content1.setEnabled(false);
		contentPane.add(help_content1);
		
		JTextPane help_content2 = new JTextPane();
		help_content2.setBackground(SystemColor.control);
		help_content2.setText("\uB3C4\uC6C0\uB9D0\uB3C4\uC6C0\uB9D0\uB3C4\uC6C0\uB9D0\uB3C4\uC6C0\uB9D0\uB3C4\uC6C0\uB9D0\uB3C4\uC6C0\uB9D0\uB3C4\uC6C0\uB9D0\uB3C4\uC6C0\r\n\uB9D0\uB3C4\uC6C0\uB9D0\uB3C4\uC6C0\uB9D0\uB3C4\uC6C0\uB9D0\uB3C4\uC6C0\uB9D0");
		help_content2.setFont(new Font("µ¸¿ò", Font.BOLD, 20));
		help_content2.setEnabled(false);
		help_content2.setBounds(152, 234, 481, 54);
		contentPane.add(help_content2);
	}
}
