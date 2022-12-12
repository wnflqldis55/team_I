package team_I;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Help extends JFrame {
	private static final long serialVersionUID = 1L;

	public Help() {
		initial();
		
		add(returnButton()); //돌아가기 버튼
		add(title()); //타이틀
		add(helpImage());
		add(backGround());
		
		setResizable(false);
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
	
	private JLabel helpImage() {
		ImageIcon im = new ImageIcon("src/Image/help.png");
		Image image = im.getImage();
		Image imageB = image.getScaledInstance(800, 300, java.awt.Image.SCALE_SMOOTH);
		
		JLabel helpImage = new JLabel(new ImageIcon(imageB));
		helpImage.setBounds(0, 300, 800, 300);
		
		return helpImage;
	}
	
	private JPanel backGround() {
		JPanel background = new JPanel();
		background.setBounds(0, 0, 800, 600);
		background.setBackground(Color.WHITE);
		
		return background;
	}
}
