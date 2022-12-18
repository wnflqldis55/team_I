package team_I;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import javazoom.jl.player.Player;

public class BGM extends Thread {
	private Player player; //음악 플레이어
	private FileInputStream fileInputStream;
	
	BGM(boolean bgm) {
		try {
			if (bgm) {
				fileInputStream = 
	                            new FileInputStream("resources/Tongtong.mp3");
	                        BufferedInputStream bufferedInputStream =
	                            new BufferedInputStream(fileInputStream);
	                        player = new Player(bufferedInputStream);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void run() {
        try {
        	while(true) {
    			player.play();
        		FileInputStream fileInputStream =
                           new FileInputStream("resources/Tongtong.mp3");
                        BufferedInputStream bufferedInputStream =
                           new BufferedInputStream(fileInputStream);
                           player = new Player(bufferedInputStream);
        	}
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
