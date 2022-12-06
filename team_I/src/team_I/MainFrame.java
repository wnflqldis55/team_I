package team_I;

import javax.swing.JFrame;
import javax.swing.JPanel;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainFrame extends JFrame {
	private JPanel home;

	public MainFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("super Mario");
		setSize(800, 600);
		setVisible(true);
		
		home = new JPanel();
		home.setVisible(true);

		setContentPane(home);
		home.setLayout(null);
		
		JButton start = new JButton("\uC2DC\uC791");
		
		start.setBounds(369, 374, 91, 23);
		start.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Start();
				setVisible(false);
			}
		});
		home.add(start);
		
		JButton help = new JButton("\uB3C4\uC6C0\uB9D0");
		help.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Help();
				setVisible(false);
			}
		});
		help.setBounds(369, 422, 91, 23);
		home.add(help);
		
		JButton setting = new JButton("\uC124\uC815");
		setting.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Setting();
				setVisible(false);
			}
		});
		setting.setBounds(369, 472, 91, 23);
		home.add(setting);
		
		JButton btnNewButton = new JButton("\uB05D\uB0B4\uAE30");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnNewButton.setBounds(12, 10, 91, 23);
		home.add(btnNewButton);
	}
}
