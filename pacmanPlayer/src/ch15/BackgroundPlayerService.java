package ch15;

import java.awt.Color;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class BackgroundPlayerService implements Runnable{
	
	private Player player;
	private BufferedImage image;
	
	public BackgroundPlayerService(Player player) {
		this.player = player;
		try {
			image = ImageIO.read(new File("images/backgroundMapService(2).png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		while(true) {
			// 왼쪽 충돌 감지 좌표
			Color leftColor = new Color(image.getRGB(player.getX() - 7, player.getY() + 5));
			Color leftColorX = new Color(image.getRGB(player.getX() - 8, player.getY() + 40));
			// 오른쪽 충돌 감지 좌표
			Color rightColor = new Color(image.getRGB(player.getX() + 42, player.getY() + 45));
			Color rightColorX = new Color(image.getRGB(player.getX() + 45, player.getY() + 10));
			// 위 충돌 감지 좌표
			Color topColor = new Color(image.getRGB(player.getX(), player.getY()));
			Color topColorX = new Color(image.getRGB(player.getX() + 40, player.getY()));
			// 아래 충돌 감지 좌표
			Color bottomColor = new Color(image.getRGB(player.getX() - 3, player.getY() + 50));
			Color bottomColorX = new Color(image.getRGB((player.getX() + 40), (player.getY() + 50)));
			
			if(((rightColor.getRed() == 255 && rightColor.getGreen() == 0 && rightColor.getBlue() == 0) && player.getpWay() == PlayerWay.RIGHT) 
					|| ((rightColorX.getRed() == 255 && rightColorX.getGreen() == 0 && rightColorX.getBlue() == 0) && player.getpWay() == PlayerWay.RIGHT)) {
				player.setRight(false);
				player.setRightWallCrash(true);
			} else if((leftColor.getRed() == 255 && leftColor.getGreen() == 0 && leftColor.getBlue() == 0 && player.getpWay() == PlayerWay.LEFT)
					|| ((leftColorX.getRed() == 255 && leftColorX.getGreen() == 0 && leftColorX.getBlue() == 0) && player.getpWay() == PlayerWay.LEFT)) {
				player.setLeft(false);
				player.setLeftWallCrash(true);
			} else if((topColor.getRed() == 255 && topColor.getGreen() == 0 && topColor.getBlue() == 0 && player.getpWay() == PlayerWay.UP) 
					|| ((topColorX.getRed() == 255 && topColorX.getGreen() == 0 && topColorX.getBlue() == 0) && player.getpWay() == PlayerWay.UP)){
				player.setUp(false);
				player.setTopWallCrash(true);
			} else if ((bottomColor.getRed() == 255 && bottomColor.getGreen() == 0 && bottomColor.getBlue() == 0 && player.getpWay() == PlayerWay.DOWN)
					|| ((bottomColorX.getRed() == 255 && bottomColorX.getGreen() == 0 && bottomColorX.getBlue() == 0) && player.getpWay() == PlayerWay.DOWN)) {
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
	
} // end of class
