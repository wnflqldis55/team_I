package team_I;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.Dimension;
import java.awt.event.ActionEvent;

public class Start extends JFrame {
	private static final long serialVersionUID = 1L;
	
	public Start() {
		this.initial(); //프레임 설정
		
		this.add(StageButton("stage1Button", 1)); //스테이지1 버튼
		this.add(StageButton("stage2Button", 2)); //스테이지2 버튼
		this.add(StageButton("stage3Button", 3)); //스테이지3 버튼
		this.add(StageButton("stage4Button", 4)); //스테이지4 버튼
		this.add(StageButton("stage5Button", 5)); //스테이지5 버튼
		this.add(StageButton("stage6Button", 6)); //스테이지6 버튼
		this.add(StageButton("stage7Button", 7)); //스테이지7 버튼
		this.add(StageButton("stage8Button", 8)); //스테이지8 버튼
		this.add(StageButton("stage9Button", 9)); //스테이지9 버튼
		this.add(StageButton("stage10Button", 10)); //스테이지10 버튼
		this.add(returnButton()); //돌아가기 버튼
		this.add(backGroundImage()); //배경 이미지
		
		this.setVisible(true); //프레임 보이게
	}
	
	private void initial() { //공통된 프레임 설정 함수
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("ESCAPE BALL"); //타이틀 지정
		this.setSize(814, 637); //사이즈 설정
		
		this.setLocationRelativeTo(null); //화면 가운데로
		this.setResizable(false); //프레임 사이즈 변경 불가능
		this.setLayout(null); //앱솔루트 레이아웃
	}
	
	private JLabel backGroundImage() { //배경 이미지
		JLabel background = //배경 이미지 라벨에 추가
                    new JLabel(new ImageIcon("resources/startFrame.png"));
		background.setBounds(0, 0, 800, 600); //라벨 위치 지정
		
		return background; //라벨 리턴
	}
	
	private JButton StageButton(String name, int num) {
		String path = "resources/" + name + ".png"; //이미지 경로
		ImageIcon img = new ImageIcon(path); //이미지 생성
		
		JButton stageButton = new JButton(img); //이미지가 들어간 버튼 생성
		stageButton.addActionListener(new ActionListener() { //리스너 등록
			public void actionPerformed(ActionEvent e) {
				switch (num) {
					case 1:
						new Stage1(); //Stage1 생성
						break;
					case 2:
						new Stage2(); //Stage2 생성
						break;
					case 3:
						new Stage3(); //Stage3 생성
						break;
					case 4:
						new Stage4(); //Stage3 생성
						break;
					case 5:
						new Stage5(); //Stage3 생성
						break;
					case 6:
						new Stage6(); //Stage3 생성
						break;
					case 7:
						new Stage6(); //Stage3 생성
						break;
					case 8:
						new Stage6(); //Stage3 생성
						break;
					case 9:
						new Stage6(); //Stage3 생성
						break;
					case 10:
						new Stage6(); //Stage3 생성
						break;
				}
			}
		});
		int y = (num - 1) * 40; //세로 위치 계산
		stageButton.setBounds(337, 120 + y, 91, 23); //위치 지정
		
		return stageButton; //버튼 리턴
	}
	
	private JButton returnButton() {
		ImageIcon img = new ImageIcon("resources/titleButton.png"); //이미지 생성
		
		JButton exitButton = new JButton(img); //이미지가 들어간 버튼 생성
		exitButton.addActionListener(new ActionListener() { //리스너 등록
			public void actionPerformed(ActionEvent e) {
				new MainFrame(false); //MainFrame 프레임 생성
				setVisible(false); //현재 프레임 안보이게
			}
		});
		exitButton.setBorderPainted(false); //버튼 테두리 설정해제
		exitButton.setPreferredSize(new Dimension(91, 23)); //버튼 크기 지정
		exitButton.setBounds(9, 9, 91, 23); //버튼 위치 지정
		
		return exitButton; //버튼 리턴
	}
}
