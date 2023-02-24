package javaPacman;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Player extends JLabel {

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
	// 무적 상태
	private boolean ghost;

	// player 속도 상태
	private final int SPEED = 5;

	public Player() {
		initData();
		setInitLayout();
	}

	public boolean isGhost() {
		return ghost;
	}

	public void setGhost(boolean ghost) {
		this.ghost = ghost;
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
		x = 500;
		y = 500;
		setSize(50, 50);
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
					try {
						Thread.sleep(15);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
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
					try {
						Thread.sleep(15);
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
					try {
						Thread.sleep(15);
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
					try {
						Thread.sleep(15);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
	}

	public void resurrection() {
		this.x = 340;
		this.y = 340;
		setLocation(340, 340);
		setIcon(playerR);
		ghost = false;
		repaint();
	}

} // end of class
