package ch12;

import java.awt.Color;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class PacManFrame extends JFrame {

	private JLabel backgroundMap;
	private Player player;
	private JLabel[] seed = new JLabel[131];
	private PacManFrame mContext = this;
	private Score score = new Score();
	private ArrayList<Enemy> enemyList = new ArrayList<>();
	private GameOverFrame gameOverFrame;

	private boolean gameOver;
	private int coinX = 55;
	private int coinY = 45;

	public void paint(Graphics g) {
		super.paint(g);
		Font font = new Font("강원교육모두 가늘게", Font.BOLD, 20);
		g.setFont(font);
		g.setColor(Color.white);
		g.drawString("Score", 600, 780);
		g.drawString(score.getScore() + "점", 680, 780);	//6650점 max

	}
	
	public Player getPlayer() {
		return player;
	}
	
	public boolean getGameOver() {
		return gameOver;
	}

	public void setGameOver(boolean gameOver) { 
		this.gameOver = gameOver;
	}

	public PacManFrame() {
		initData();
		setInitLayout();
		addEventListener();
		new Thread(new BackgroundPlayerService(player)).start();
		
		for (int i = 0; i < enemyList.size(); i++) {
			new Thread(new BackgroundEnemyService(enemyList.get(i), this)).start();
		}
	}

	public void initData() {
		setTitle("팩맨");
		setSize(800, 800);
		backgroundMap = new JLabel(new ImageIcon("images/backgroundMap4.png"));
		setContentPane(backgroundMap);
		player = new Player(mContext);
		for (int i = 0; i < seed.length; i++) {
			seed[i] = new JLabel(new ImageIcon("images/seed.png"));
			seed[i].setSize(50, 50);
		}
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		makeEnemies();
	}
	
	// 유령 생성
	public void makeEnemies() {
		enemyList.add(new Enemy(50, 50));
		enemyList.add(new Enemy(50, 670));
		enemyList.add(new Enemy(690, 50));
		enemyList.add(new Enemy(690, 670));

		System.out.println(enemyList.size());
	}

	public void setInitLayout() {
		setLayout(null);
		add(player);
		
		for (int i = 0; i < enemyList.size(); i++) {
			add(enemyList.get(i));
		}
		// coin 1번 지점 x 6개찍기
		for (int i = 0; i < 6; i++) {
			add(seed[i]);
			seed[i].setLocation(coinX, coinY);
			coinX += 50;
		}
		coinX = 425;
		// coin 6번지점 x 6개찍기
		for (int i = 6; i < 12; i++) {
			add(seed[i]);
			seed[i].setLocation(coinX, coinY);
			coinX += 50;
		}
		coinX = 55;
		coinY = 670;
		// coin 96번지점 x 6개 찍기
		for (int i = 14; i < 20; i++) {
			add(seed[i]);
			seed[i].setLocation(coinX, coinY);
			coinX += 50;
		}
		coinX = 425;
		// coin 101번지점 x 6개찍기
		for (int i = 20; i < 26; i++) {
			add(seed[i]);
			seed[i].setLocation(coinX, coinY);
			coinX += 50;
		}
		coinX = 55;
		coinY = 105;
		// coin 1번 지점 y 11개 찍기
		for (int i = 26; i < 37; i++) {
			add(seed[i]);
			seed[i].setLocation(coinX, coinY);
			coinY += 50;
		}
		coinX = 680;
		coinY = 105;
		// coin 10번 지점 y 11개 찍기
		for (int i = 37; i < 48; i++) {
			add(seed[i]);
			seed[i].setLocation(coinX, coinY);
			coinY += 50;
		}
		coinX = 170;
		coinY = 140;
		// coin 16번 지점 x 4개찍기
		for (int i = 48; i < 52; i++) {
			add(seed[i]);
			seed[i].setLocation(coinX, coinY);
			coinX += 50;
		}
		// coin 16번 지점 y 9개 찍기
		coinX = 170;
		for (int i = 52; i < 61; i++) {
			add(seed[i]);
			seed[i].setLocation(coinX, coinY);
			coinY += 50;
		} // coin 17번 지점 y 9개 찍기
		coinX = 220;
		coinY = 140;
		for (int i = 61; i < 70; i++) {
			add(seed[i]);
			seed[i].setLocation(coinX, coinY);
			coinY += 50;
		}
		coinX = 170;
		coinY = 140;
		// coin 19번 지점 x 4개 찍기
		for (int i = 70; i < 74; i++) {
			add(seed[i]);
			seed[i].setLocation(coinX, coinY);
			coinX += 50;
		}
		coinX = 270;
		coinY = 190;
		// coin 25번 지점 x 2개 찍기
		for (int i = 74; i < 76; i++) {
			add(seed[i]);
			seed[i].setLocation(coinX, coinY);
			coinX += 50;
		}
		coinX = 270;
		coinY = 240;
		// coin 33번 지점 x 7개 찍기
		for (int i = 76; i < 83; i++) {
			add(seed[i]);
			seed[i].setLocation(coinX, coinY);
			coinX += 50;
		}
		// coin 19번 지점 x 4개
		coinX = 420;
		coinY = 140;
		for (int i = 83; i < 87; i++) {
			add(seed[i]);
			seed[i].setLocation(coinX, coinY);
			coinX += 50;
		}
		coinX = 420;
		coinY = 190;
		// coin 27번 지점 x 4개
		for (int i = 87; i < 91; i++) {
			add(seed[i]);
			seed[i].setLocation(coinX, coinY);
			coinX += 50;
		}
		coinX = 520;
		coinY = 290;
		// coin 44번 지점 y 6개
		for (int i = 91; i < 97; i++) {
			add(seed[i]);
			seed[i].setLocation(coinX, coinY);
			coinY += 50;
		}
		coinX = 570;
		coinY = 290;
		// coin 45번 지점 y 6개
		for (int i = 97; i < 103; i++) {
			add(seed[i]);
			seed[i].setLocation(coinX, coinY);
			coinY += 50;
		}
		coinX = 270;
		coinY = 340;
		// coin 52번 지점 x 5개
		for (int i = 103; i < 108; i++) {
			add(seed[i]);
			seed[i].setLocation(coinX, coinY);
			coinX += 50;
		}
		coinX = 320;
		coinY = 470;
		// coin 70번 지점 y 4개
		for (int i = 108; i < 112; i++) {
			add(seed[i]);
			seed[i].setLocation(coinX, coinY);
			coinY += 50;
		}
		coinX = 270;
		coinY = 470;
		// coin 69번 지점 y 3개
		for (int i = 112; i < 115; i++) {
			add(seed[i]);
			seed[i].setLocation(coinX, coinY);
			coinY += 50;
		}
		coinX = 420;
		coinY = 470;
		// coin 72번 지점 y 4개
		for (int i = 115; i < 119; i++) {
			add(seed[i]);
			seed[i].setLocation(coinX, coinY);
			coinY += 50;
		}
		coinX = 470;
		coinY = 470;
		// coin 73번 지점 y 3개
		for (int i = 119; i < 122; i++) {
			add(seed[i]);
			seed[i].setLocation(coinX, coinY);
			coinY += 50;
		}
		coinX = 105;
		coinY = 300;
		// coin 41번 지점 y 3개
		for (int i = 122; i < 125; i++) {
			add(seed[i]);
			seed[i].setLocation(coinX, coinY);
			coinY += 50;
		}
		coinX = 625;
		coinY = 300;
		// coin 46번 지점 y 3개
		for (int i = 125; i < 128; i++) {
			add(seed[i]);
			seed[i].setLocation(coinX, coinY);
			coinY += 50;
		}
		// coin 12번 지점
		add(seed[129]);
		seed[129].setLocation(320, 90);
		// coin 13번 지점
		add(seed[130]);
		seed[130].setLocation(420, 90);

		setResizable(false);
		setVisible(true);
	}

	public void addEventListener() {
		this.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyCode()) {
				case KeyEvent.VK_LEFT:
					if(!player.isLeft() && !player.isLeftWallCrash()) {
						player.setLeft(true);
						player.initThread();
					}
					break;
				case KeyEvent.VK_RIGHT:
					if(!player.isRight() && !player.isRightWallCrash()) {
						player.setRight(true);
						player.initThread();
					}
					break;
				case KeyEvent.VK_UP:
					if(!player.isUp() && !player.isTopWallCrash()) {
						player.setUp(true);
						player.initThread();
					}
					break;
				case KeyEvent.VK_DOWN:
					if(!player.isDown() && !player.isBottomWallCrash()) {
						player.setDown(true);
						player.initThread();
					}
					break;
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				switch (e.getKeyCode()) {
				case KeyEvent.VK_LEFT:
					player.setLeft(false);
					break;
				case KeyEvent.VK_RIGHT:
					player.setRight(false);
					break;
				case KeyEvent.VK_UP:
					player.setUp(false);
					break;
				case KeyEvent.VK_DOWN:
					player.setDown(false);
					break;
				}
			}
		});
	}

	public void eatSeed() {
		if (player.isLeft()) {
			for (int i = 0; i < seed.length; i++) {
				if (Math.abs(player.getX() - seed[i].getX()) < 15
						&& Math.abs(player.getY() - seed[i].getY()) < 15) {
					seed[i].setIcon(null);
					score.scoreUp();
					seed[i].setLocation(0, 0);
				}
			}
			repaint();
		} // end of isLeft
		
		if (player.isRight()) {
			for (int i = 0; i < seed.length; i++) {
				if (Math.abs(player.getX() - seed[i].getX()) < 15
						&& Math.abs(player.getY() - seed[i].getY()) < 15) {
					seed[i].setIcon(null);
					score.scoreUp();
					seed[i].setLocation(0, 0);
				}
			}
			repaint();
		} // end of isRight

		if (player.isUp()) {
			for (int i = 0; i < seed.length; i++) {
				if (Math.abs(player.getX() - seed[i].getX()) < 15
						&& Math.abs(player.getY() - seed[i].getY()) < 15) {
					seed[i].setIcon(null);
					score.scoreUp();
					seed[i].setLocation(0, 0);
				}
			}
			repaint();
		} // end of isUp

		if (player.isDown()) {
			for (int i = 0; i < seed.length; i++) {
				if (Math.abs(player.getX() - seed[i].getX()) < 15
						&& Math.abs(player.getY() - seed[i].getY()) < 15) {
					seed[i].setIcon(null);
					score.scoreUp();
					seed[i].setLocation(0, 0);
				}
			}
			repaint();
		} // end of isDown
		
		//게임 종료
		if(gameOver) {
			new GameOverFrame();
		}
	}// end of for

}