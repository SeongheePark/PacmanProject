package ch02;

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

	// player 속도 상태
	private final int SPEED = 5;
	
	// 벽 충돌 상태
	private boolean leftWallCrash;
	private boolean rightWallCrash;
	private boolean topWallCrash;
	private boolean bottomWallCrash;
	
	// player가 보는 방향
	PlayerWay pWay;

	public Player() {
		initData();
		setInitLayout();
	}

	public PlayerWay getpWay() {
		return pWay;
	}

	public boolean isLeftWallCrash() {
		return leftWallCrash;
	}

	public void setLeftWallCrash(boolean leftWallCrash) {
		this.leftWallCrash = leftWallCrash;
	}

	public boolean isRightWallCrash() {
		return rightWallCrash;
	}

	public void setRightWallCrash(boolean rightWallCrash) {
		this.rightWallCrash = rightWallCrash;
	}

	public boolean isTopWallCrash() {
		return topWallCrash;
	}

	public void setTopWallCrash(boolean topWallCrash) {
		this.topWallCrash = topWallCrash;
	}

	public boolean isBottomWallCrash() {
		return bottomWallCrash;
	}

	public void setBottomWallCrash(boolean bottomWallCrash) {
		this.bottomWallCrash = bottomWallCrash;
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
		playerR = new ImageIcon("images/player_icon.png");
		playerL = new ImageIcon("images/player_icon.png");
		playerU = new ImageIcon("images/player_icon.png");
		playerD = new ImageIcon("images/player_icon.png");
		left = false;
		right = false;
		up = false;
		down = false;
		leftWallCrash = false;
		rightWallCrash = false;
		topWallCrash = false;
		bottomWallCrash = false;
		pWay = PlayerWay.RIGHT;
	}

	private void setInitLayout() {
		x = 75;
		y = 315;
		setSize(40, 40);
		setLocation(x, y);
		setIcon(playerR);
	}

	public void left() {
		pWay = PlayerWay.LEFT;
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
		pWay = PlayerWay.RIGHT;
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
		pWay = PlayerWay.UP;
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
		pWay = PlayerWay.DOWN;
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

} // end of class
