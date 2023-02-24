package ch12;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Enemy extends JLabel {
	// 위치 찾을 방향
	private int x;
	private int y;
	private int speed;
	private ImageIcon enemy;
	private ImageIcon fastEnemy;

	// 생성 좌표 필요
	public Enemy(int x, int y) {
		setInitData(x, y);
		setInitLayout();
	}

	public ImageIcon getEnemy() {
		return enemy;
	}

	public ImageIcon getFastEnemy() {
		return fastEnemy;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	private void setInitData(int x, int y) {
		this.x = x;
		this.y = y;
		speed = 1;
		enemy = new ImageIcon("images/enemy.png");
		fastEnemy = new ImageIcon("images/superEnemy.png");
	}

	private void setInitLayout() {
		setSize(50, 50);
		setLocation(x, y);
		setIcon(enemy);
	}

}
