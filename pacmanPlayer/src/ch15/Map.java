package ch15;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Map extends JLabel {
	private int width;
	private int height;
	private JLabel backgroundMap;
	private BufferedImage map;

	public Map(String mapPath) {
		try {
			map = ImageIO.read(new File(mapPath));
			// 이미지 사이즈 수정 코드
//			Image mapImage = ImageIO.read(new File(mapPath));
//			Image resizeMap = mapImage.getScaledInstance(800, 800, Image.SCALE_SMOOTH);
//			BufferedImage map = new BufferedImage(800, 800, BufferedImage.TYPE_INT_RGB);
//			Graphics g = map.getGraphics();
//			g.drawImage(resizeMap,0,0,null);

			backgroundMap = new JLabel(new ImageIcon(map));

			this.width = map.getWidth();
			this.height = map.getHeight();
			backgroundMap.setSize(785, 770);
			backgroundMap.setLocation(0, 0);

		} catch (IOException e) {
			System.out.println("오류");
			e.printStackTrace();
		}
	}

	public JLabel getBackgroundMap() {
		return backgroundMap;
	}

	public void setBackgroundMap(JLabel backgroundMap) {
		this.backgroundMap = backgroundMap;
	}

}
