package javaPacman;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

public class BackgroundEnemyService implements Runnable {
	private Enemy enemy;
	private BufferedImage map;
	private Random randomDirection;
	private PacManFrame mContext;
	private int CHANGETIME = 600;

	private boolean fastMode;
	private int time;
	private int direction = 2;
	private int x;
	private int y;

	public BackgroundEnemyService(Enemy enemy, PacManFrame mContext) {
		this.enemy = enemy;
		this.mContext = mContext;
		this.x = enemy.getX();
		this.y = enemy.getY();
		randomDirection = new Random();

		try {
			map = ImageIO.read(new File("images/back.png"));
		} catch (IOException e) {
			System.out.println("백그라운드 플레이어 서비스 객체에 사용하는 이미지 경로 및 파일명 확인!");
		}
	}

	public boolean canMove() {
		Color leftColor = new Color(map.getRGB(enemy.getX(), enemy.getY() + 25));
		Color rightColor = new Color(map.getRGB(enemy.getX() + 40, enemy.getY() + 25));
		Color bottomColor = new Color(map.getRGB(enemy.getX() + 25, enemy.getY() + 45));
		Color topColor = new Color(map.getRGB(enemy.getX() + 25, enemy.getY() + 5));

		// 동서남북 검사
		if (direction == 0 && rightColor.getRed() == 255 && rightColor.getGreen() == 0 && rightColor.getBlue() == 0) {
			return false;
		} else if (direction == 1 && leftColor.getRed() == 255 && leftColor.getGreen() == 0
				&& leftColor.getBlue() == 0) {
			return false;
		} else if (direction == 2 && bottomColor.getRed() == 255 && bottomColor.getGreen() == 0
				&& bottomColor.getBlue() == 0) {
			return false;
		} else if (direction == 3 && topColor.getRed() == 255 && topColor.getGreen() == 0 && topColor.getBlue() == 0) {
			return false;
		}

		return true;
	}

	public boolean crash() {
		// 충돌범위 안으로 왔다면
		if (Math.abs(this.enemy.getX() - this.mContext.getPlayer().getX()) < 50
				&& Math.abs(this.enemy.getY() - this.mContext.getPlayer().getY()) < 50) {
			System.out.println("충돌함");
			return true;
		}
		return false;
	}

	public void changeMode() {
		if (time == CHANGETIME) {
			if (fastMode) {
				enemy.setIcon(enemy.getEnemy());
				enemy.setSpeed(1);
				fastMode = false;
			} else {
				enemy.setIcon(enemy.getFastEnemy());
				enemy.setSpeed(2);
				fastMode = true;
			}
			time = 0;
		}

		time++;
	}

	@Override
	public void run() {
		while (true) {
			// 플레이어 충돌 검사
			if (crash() && !mContext.getPlayer().isGhost()) {
				// mContext.remove(mContext.getPlayer());
				mContext.getPlayer().setGhost(true);
				mContext.getPlayer().resurrection();
				mContext.repaint();
				mContext.setGameOver(true); // 게임오버
				break;
			}

			// 에너미 상하좌우 이동 변경
			if (direction == 0) {
				if (canMove()) {
					x = x + enemy.getSpeed();
					enemy.setLocation(x, y);
				} else {
					direction = randomDirection.nextInt(4);
				}
			} else if (direction == 1) {
				if (canMove()) {
					x = x - enemy.getSpeed();
					enemy.setLocation(x, y);
				} else {
					direction = randomDirection.nextInt(4);
				}
			} else if (direction == 2) {
				if (canMove()) {
					y = y + enemy.getSpeed();
					enemy.setLocation(x, y);
				} else {
					direction = randomDirection.nextInt(4);
				}
			} else if (direction == 3) {
				if (canMove()) {
					y = y - enemy.getSpeed();
					enemy.setLocation(x, y);
				} else {
					direction = randomDirection.nextInt(4);
				}
			}

			// 게임 오버 시에 종료
			if (mContext.getGameOver() == true) {
				break;
			}

			// 에너미 모드 변경
			changeMode();

			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			// break;
		}

	}
}
