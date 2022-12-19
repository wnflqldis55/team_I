package team_I;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Help_p4 extends JFrame {
	private static final long serialVersionUID = 1L;

	public Help_p4() {
		this.initial(); //프레임 설정
		
		this.add(prevButton()); //이전 버튼
		this.add(returnButton()); //돌아가기 버튼
		this.add(backGroundImage()); //캔버스 추가
		
		this.setVisible(true); //프레임 보이게
	}
	
	private void initial() { //공통된 프레임 설정 함수
		this.setTitle("ESCAPE BALL"); //타이틀 지정
		this.setSize(814, 637); //사이즈 설정
		
		this.setLocationRelativeTo(null); //화면 가운데로
		this.setResizable(false); //프레임 사이즈 변경 불가능
		this.setLayout(null); //앱솔루트 레이아웃
	}
	
	private JLabel backGroundImage() { //배경 이미지
		JLabel background = //배경 이미지 라벨에 추가
                    new JLabel(new ImageIcon(getClass().getClassLoader().getResource("help_p4.png"))); //임시
		background.setBounds(0, 0, 800, 600); //라벨 위치 지정
		
		return background; //라벨 리턴
	}
	
	private JButton prevButton() {
		ImageIcon img = new ImageIcon(getClass().getClassLoader().getResource("prevButton.png")); //이미지 생성
		
		JButton prev = new JButton(img); //버튼 생성
		prev.addActionListener(new ActionListener() { //리스너 등록
			public void actionPerformed(ActionEvent e) {
				new Help_p3(); //이전 페이지
				dispose(); //현재 프레임 안보이게
			}
		});
		LineBorder boader = new LineBorder(new Color(75, 75, 75));
		prev.setBorder(boader);
		prev.setPreferredSize(new Dimension(32, 32)); //버튼 크기 지정
		prev.setBounds(710, 554, 32, 32); //위치 지정W
		
		return prev; //버튼 리턴
	}
	
	private JButton returnButton() {
		ImageIcon img = new ImageIcon(getClass().getClassLoader().getResource("returnButton.png")); //이미지 생성
		
		JButton returnBotton = new JButton(img); //이미지가 들어간 버튼 생성
		returnBotton.addActionListener(new ActionListener() { //리스너 등록
			public void actionPerformed(ActionEvent e) {
				dispose(); //현재 프레임 안보이게
			}
		});
		returnBotton.setBorderPainted(false); //버튼 테두리 설정해제
		returnBotton.setPreferredSize(new Dimension(91, 23)); //버튼 크기 지정
		returnBotton.setBounds(9, 9, 91, 23); //버튼 위치 지정
		
		return returnBotton;
	}
}
