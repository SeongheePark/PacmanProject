package ch01;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class BackgroundPlayerService implements Runnable{
	
	private Player player;
	private BufferedImage image;
	
	public BackgroundPlayerService(Player player) {
		this.player = player;
		try {
			image = ImageIO.read(new File("images/background4.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		while(true) {
			Color leftColor = new Color(image.getRGB(player.getX() - 3, player.getY()));
			Color rightColor = new Color(image.getRGB(player.getX() + 40, player.getY()));
			Color topColor = new Color(image.getRGB(player.getX(), player.getY() - 3));
			Color bottomColor = new Color(image.getRGB(player.getX(), player.getY() + 50));
//			System.out.println(image.getRGB(player.getX(), player.getY()));
			System.out.println("오른쪽 " + rightColor);
			System.out.println("위 " + topColor);
			System.out.println("왼쪽 " + leftColor);
			System.out.println("아래" +bottomColor);
			
			if(rightColor.getRed() == 255 && rightColor.getGreen() == 0 && rightColor.getBlue() == 0 && player.getpWay() == PlayerWay.RIGHT) {
				player.setRight(false);
				player.setRightWallCrash(true);
				System.out.println("오른쪽 벽에 부딪힘");
			} else if(leftColor.getRed() == 255 && leftColor.getGreen() == 0 && leftColor.getBlue() == 0 && player.getpWay() == PlayerWay.LEFT) {
				player.setLeft(false);
				player.setLeftWallCrash(true);
				System.out.println("왼쪽 벽에 부딪힘");
			} else if(topColor.getRed() == 255 && topColor.getGreen() == 0 && topColor.getBlue() == 0 && player.getpWay() == PlayerWay.UP){
				player.setUp(false);
				player.setTopWallCrash(true);
				System.out.println("위쪽에 부딪힘");
			} else if (bottomColor.getRed() == 255 && bottomColor.getGreen() == 0 && bottomColor.getBlue() == 0 && player.getpWay() == PlayerWay.DOWN) {
				player.setDown(false);
				player.setBottomWallCrash(true);
			} else {
				player.setRightWallCrash(false);
				player.setLeftWallCrash(false);
				player.setTopWallCrash(false);
				player.setBottomWallCrash(false);
			}
			try {
				Thread.sleep(3);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
}
