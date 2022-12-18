package team_I;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Help_p2 extends JFrame {
	private static final long serialVersionUID = 1L;
	Help_sub help_sub = new Help_sub(); //조작을 위한 서브 캔버스

	public Help_p2() {
		this.initial(); //프레임 설정
		
		this.add(prevButton()); //이전 버튼
		this.add(nextButton()); //다음 버튼
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
	
	private Help_sub backGroundImage() { //배경 이미지
		help_sub.setBounds(0, 0, 800, 600); //캔버스 위치 지정
		
		return help_sub; //라벨 리턴
	}
	
	private JButton prevButton() {
		ImageIcon img = new ImageIcon("resources/prevButton.png"); //이미지 생성
		
		JButton prev = new JButton(img); //버튼 생성
		prev.addActionListener(new ActionListener() { //리스너 등록
			public void actionPerformed(ActionEvent e) {
				new Help_p1(); //다음페이지
				setVisible(false); //현재 프레임 안보이게
			}
		});
		LineBorder boader = new LineBorder(new Color(75, 75, 75));
		prev.setBorder(boader);
		prev.setPreferredSize(new Dimension(32, 32)); //버튼 크기 지정
		prev.setBounds(710, 554, 32, 32); //위치 지정W
		
		return prev; //버튼 리턴
	}
	
	private JButton nextButton() {
		ImageIcon img = new ImageIcon("resources/nextButton.png"); //이미지 생성
		
		JButton next = new JButton(img); //버튼 생성
		next.addActionListener(new ActionListener() { //리스너 등록
			public void actionPerformed(ActionEvent e) {
				new Help_p3(); //다음페이지
				setVisible(false); //현재 프레임 안보이게
			}
		});
		LineBorder boader = new LineBorder(new Color(75, 75, 75));
		next.setBorder(boader);
		next.setPreferredSize(new Dimension(32, 32)); //버튼 크기 지정
		next.setBounds(745, 554, 32, 32); //위치 지정W
		
		return next; //버튼 리턴
	}
	
	private JButton returnButton() {
		ImageIcon img = new ImageIcon("resources/returnButton.png"); //이미지 생성
		
		JButton returnBotton = new JButton(img); //이미지가 들어간 버튼 생성
		returnBotton.addActionListener(new ActionListener() { //리스너 등록
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		returnBotton.setBorderPainted(false); //버튼 테두리 설정해제
		returnBotton.setPreferredSize(new Dimension(91, 23)); //버튼 크기 지정
		returnBotton.setBounds(9, 9, 91, 23); //버튼 위치 지정
		
		return returnBotton;
	}
}
