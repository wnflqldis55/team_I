package team_I;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Setting extends JFrame {
	private static final long serialVersionUID = 1L;
	private Color selectColor = new Color(75, 75, 75); //초기 색상
	private JButton colorSelectButton = new JButton(); //버튼 생성
	
	public Setting() {
		this.initial(); //프레임 설정
		
		this.add(BGM()); //돌아가기 버튼
		this.add(BGMonoff()); //돌아가기 버튼
		this.add(colorButton()); //돌아가기 버튼
		this.add(colorSelectButton()); //돌아가기 버튼
		this.add(returnButton()); //돌아가기 버튼
		this.add(backGroundImage()); //배경 이미지

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
                    new JLabel(new ImageIcon("resources/settingFrame.png"));
		background.setBounds(0, 0, 800, 600); //라벨 위치 지정
		
		return background; //라벨 리턴
	}
	
	private JButton returnButton() {
		ImageIcon img = new ImageIcon("resources/returnButton.png"); //이미지 생성
		
		JButton returnBotton = new JButton(img); //이미지가 들어간 버튼 생성
		returnBotton.addActionListener(new ActionListener() { //리스너 등록
			public void actionPerformed(ActionEvent e) {
				setVisible(false); //현재 프레임 안보이게
			}
		});
		returnBotton.setBorderPainted(false); //버튼 테두리 설정해제
		returnBotton.setPreferredSize(new Dimension(91, 23)); //버튼 크기 지정
		returnBotton.setBounds(9, 9, 91, 23); //버튼 위치 지정
		
		return returnBotton; //버튼 리턴
	}
	
	private JLabel BGM() {
		JLabel BGM = //배경 이미지 라벨에 추가
                new JLabel(new ImageIcon("resources/BGM.png"));
		BGM.setBounds(55, 95, 114, 22); //라벨 위치 지정
	
		return BGM; //라벨 리턴
	}
	
	private JLabel BGMonoff() {
		JLabel BGM = //배경 이미지 라벨에 추가
                new JLabel(new ImageIcon("resources/On_Off.png"));
		BGM.setBounds(180, 90, 49, 30); //라벨 위치 지정
	
		return BGM; //라벨 리턴
	}
	
	private JButton colorButton() {
		ImageIcon img = new ImageIcon("resources/colorselectButton.png"); //이미지 생성
		
		JButton colorButton = new JButton(img); //이미지가 들어간 버튼 생성
		colorButton.addActionListener(new ActionListener() { //리스너 등록
			public void actionPerformed(ActionEvent e) {
				selectColor = JColorChooser.showDialog(null, "색상 선택", selectColor); //색상 다이얼로그 생성
				colorSelectButton.setBackground(selectColor); //현재 색 바꾸기
			}
		});
		colorButton.setBorderPainted(false); //버튼 테두리 설정해제
		colorButton.setPreferredSize(new Dimension(91, 23)); //버튼 크기 지정
		colorButton.setBounds(54, 145, 91, 23); //버튼 위치 지정
		
		return colorButton; //버튼 리턴
	}
	
	private JButton colorSelectButton() {
		colorSelectButton.setBackground(selectColor); //selectColor 색의 버튼 생성
		colorSelectButton.setBorderPainted(false); //버튼 테두리 설정해제
		colorSelectButton.setPreferredSize(new Dimension(50, 50)); //버튼 크기 지정
		colorSelectButton.setBounds(54, 177, 50, 50); //버튼 위치 지정
		
		return colorSelectButton; //버튼 리턴
	}
}
