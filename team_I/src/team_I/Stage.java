package team_I;

import javax.swing.JFrame;

public class Stage extends JFrame {
	private Map map = new Map();

	public Stage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 600);
		this.add(map);
		this.setVisible(true);
	}
}
