package ch01;

import java.awt.Rectangle;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Player extends JLabel {

	PacManFrame mContext;

	int score = 0;

	// player 좌표
	private int x;
	private int y;

	// player 이미지
	private ImageIcon playerR;
	private ImageIcon playerL;
	private ImageIcon playerU;
	private ImageIcon playerD;

	// player 방향 상태
	private boolean left;
	private boolean right;
	private boolean up;
	private boolean down;

	// player 속도 상태
	private final int SPEED = 5;

	public Player(PacManFrame mContext) {
		this.mContext = mContext;
		initData();
		setInitLayout();
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public boolean isLeft() {
		return left;
	}

	public void setLeft(boolean left) {
		this.left = left;
	}

	public boolean isRight() {
		return right;
	}

	public void setRight(boolean right) {
		this.right = right;
	}

	public boolean isUp() {
		return up;
	}

	public void setUp(boolean up) {
		this.up = up;
	}

	public boolean isDown() {
		return down;
	}

	public void setDown(boolean down) {
		this.down = down;
	}

	private void initData() {
		playerR = new ImageIcon("images/PacmanR.png");
		playerL = new ImageIcon("images/PacmanL.png");
		playerU = new ImageIcon("images/PacmanUp.png");
		playerD = new ImageIcon("images/PacmanDown.png");
		left = false;
		right = false;
		up = false;
		down = false;
	}

	private void setInitLayout() {
		x = 300;
		y = 300;
		setSize(100, 100);
		setLocation(x, y);
		setIcon(playerR);
	}

	public void left() {
		left = true;
		setIcon(playerL);
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (left) {
					x = x - SPEED;
					setLocation(x, y);
					mContext.eatSeed();
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				} // end of while
			}
		}).start();

	}

	public void right() {
		right = true;
		setIcon(playerR);
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (right) {
					x = x + SPEED;
					setLocation(x, y);
					mContext.eatSeed();
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
	}

	public void up() {
		up = true;
		setIcon(playerU);
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (up) {
					y = y - SPEED;
					setLocation(x, y);
					mContext.eatSeed();
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
	}

	public void down() {
		down = true;
		setIcon(playerD);
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (down) {
					y = y + SPEED;
					setLocation(x, y);
					mContext.eatSeed();
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
	}

} // end of class