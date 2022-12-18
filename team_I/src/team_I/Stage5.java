package team_I;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

public class Stage5 extends JFrame {
	private static final long serialVersionUID = 1L;
	private Map map = new Map();
	private Screen screen = new Screen(map.getMap5());
	
	public Stage5() {
		this.initial(); //������ ����
	
		this.add(returnButton()); //���ư��� ��ư
		this.add(backGroundImage()); //ĵ���� �߰�
		
		this.setVisible(true); //������ ���̰�
	}
	
	private void initial() { //����� ������ ���� �Լ�
		this.setTitle("ESCAPE BALL"); //Ÿ��Ʋ ����
		this.setSize(814, 637); //������ ����
		
		this.setLocationRelativeTo(null); //ȭ�� �����
		this.setResizable(false); //������ ������ ���� �Ұ���
		this.setLayout(null); //�ۼַ�Ʈ ���̾ƿ�
	}
	
	private Screen backGroundImage() { //��� �̹���
		screen.setBounds(0, 0, 800, 600); //ĵ���� ��ġ ����
		
		return screen; //�� ����
	}
	
	private JButton returnButton() {
		ImageIcon img = new ImageIcon("resources/returnButton.png"); //�̹��� ����
		
		JButton returnBotton = new JButton(img); //�̹����� �� ��ư ����
		returnBotton.addActionListener(new ActionListener() { //������ ���
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		returnBotton.setBorderPainted(false); //��ư �׵θ� ��������
		returnBotton.setPreferredSize(new Dimension(91, 23)); //��ư ũ�� ����
		returnBotton.setBounds(9, 9, 91, 23); //��ư ��ġ ����
		
		return returnBotton;
	}
}