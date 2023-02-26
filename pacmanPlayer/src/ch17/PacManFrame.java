package ch17;

import java.awt.Color;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import ch12.GameOverFrame;

public class PacManFrame extends JFrame {
	
	// 팩맨 요소들
	private JLabel backgroundMap;
	private Player player;
	private JLabel[] seed = new JLabel[131];
	private PacManFrame mContext = this;
	private Score score = new Score();
	private ArrayList<Enemy> enemyList = new ArrayList<>();
	// 남은 목숨 이미지로 보여줄 수 있는 이미지
	private JLabel life1;
	private JLabel life2;
	private JLabel life3;
	// 키 동시입력 막는 용도
	private boolean keyPressed;
	// 게임 끝 판단 용도
	private boolean gameOver;
	private boolean gameSuccess;
	// 씨앗 좌표
	private int seedX;
	private int seedY;

	// 생성자
	public PacManFrame() {
		initData();
		setInitLayout();
		addEventListener();
		new Thread(new BackgroundPlayerService(player)).start();
		
		for (int i = 0; i < enemyList.size(); i++) {
			new Thread(new BackgroundEnemyService(enemyList.get(i), this)).start();
		}
	}
	
	// score 화면 표시
	public void paint(Graphics g) {
		super.paint(g);
		Font font = new Font("맑은 고딕", Font.BOLD, 20);
		g.setFont(font);
		g.setColor(Color.white);
		g.drawString("Score", 600, 780);
		g.drawString(score.getScore() + "점", 680, 780); // 6650점 max
		if(score.getScore() == 6400) {
			gameSuccess = true;
			new GameSuccessFrame();
		}
		
	}
	
	public Score getScore() {
		return score;
	}

	public void setScore(Score score) {
		this.score = score;
	}

	public JLabel[] getSeed() {
		return seed;
	}
	
	public JLabel getSeed(int i) {
		return seed[i];
	}

	public void setSeed(JLabel[] seed) {
		this.seed = seed;
	}

	public JLabel getLife1() {
		return life1;
	}

	public void setLife1(JLabel life1) {
		this.life1 = life1;
	}

	public JLabel getLife2() {
		return life2;
	}

	public void setLife2(JLabel life2) {
		this.life2 = life2;
	}

	public JLabel getLife3() {
		return life3;
	}

	public void setLife3(JLabel life3) {
		this.life3 = life3;
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
	

	public boolean isGameSuccess() {
		return gameSuccess;
	}

	public void setGameSuccess(boolean gameSuccess) {
		this.gameSuccess = gameSuccess;
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
		life1 = new JLabel(new ImageIcon("images/life.png"));
		life2 = new JLabel(new ImageIcon("images/life.png"));
		life3 = new JLabel(new ImageIcon("images/life.png"));
		life1.setSize(50, 50);
		life2.setSize(50, 50);
		life3.setSize(50, 50);
		keyPressed = false;
		seedX = 55;
		seedY = 45;
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
		add(life1);
		add(life2);
		add(life3);
		life1.setLocation(400, 715);
		life2.setLocation(450, 715);
		life3.setLocation(500, 715);

		for (int i = 0; i < enemyList.size(); i++) {
			add(enemyList.get(i));
		}
		// seed 1번 지점 x 6개찍기
		for (int i = 0; i < 6; i++) {
			add(seed[i]);
			seed[i].setLocation(seedX, seedY);
			seedX += 50;
		}
		seedX = 425;
		// seed 6번지점 x 6개찍기
		for (int i = 6; i < 12; i++) {
			add(seed[i]);
			seed[i].setLocation(seedX, seedY);
			seedX += 50;
		}
		seedX = 55;
		seedY = 670;
		// seed 96번지점 x 6개 찍기
		for (int i = 14; i < 20; i++) {
			add(seed[i]);
			seed[i].setLocation(seedX, seedY);
			seedX += 50;
		}
		seedX = 425;
		// seed 101번지점 x 6개찍기
		for (int i = 20; i < 26; i++) {
			add(seed[i]);
			seed[i].setLocation(seedX, seedY);
			seedX += 50;
		}
		seedX = 55;
		seedY = 105;
		// seed 1번 지점 y 11개 찍기
		for (int i = 26; i < 37; i++) {
			add(seed[i]);
			seed[i].setLocation(seedX, seedY);
			seedY += 50;
		}
		seedX = 680;
		seedY = 105;
		// seed 10번 지점 y 11개 찍기
		for (int i = 37; i < 48; i++) {
			add(seed[i]);
			seed[i].setLocation(seedX, seedY);
			seedY += 50;
		}
		seedX = 170;
		seedY = 140;
		// seed 16번 지점 x 4개찍기
		for (int i = 48; i < 52; i++) {
			add(seed[i]);
			seed[i].setLocation(seedX, seedY);
			seedX += 50;
		}
		// seed 16번 지점 y 9개 찍기
		seedX = 170;
		for (int i = 52; i < 61; i++) {
			add(seed[i]);
			seed[i].setLocation(seedX, seedY);
			seedY += 50;
		} // seed 17번 지점 y 9개 찍기
		seedX = 220;
		seedY = 140;
		for (int i = 61; i < 70; i++) {
			add(seed[i]);
			seed[i].setLocation(seedX, seedY);
			seedY += 50;
		}
		seedX = 170;
		seedY = 140;
		// seed 19번 지점 x 4개 찍기
		for (int i = 70; i < 74; i++) {
			add(seed[i]);
			seed[i].setLocation(seedX, seedY);
			seedX += 50;
		}
		seedX = 270;
		seedY = 190;
		// seed 25번 지점 x 2개 찍기
		for (int i = 74; i < 76; i++) {
			add(seed[i]);
			seed[i].setLocation(seedX, seedY);
			seedX += 50;
		}
		seedX = 270;
		seedY = 240;
		// seed 33번 지점 x 7개 찍기
		for (int i = 76; i < 83; i++) {
			add(seed[i]);
			seed[i].setLocation(seedX, seedY);
			seedX += 50;
		}
		// seed 19번 지점 x 4개
		seedX = 420;
		seedY = 140;
		for (int i = 83; i < 87; i++) {
			add(seed[i]);
			seed[i].setLocation(seedX, seedY);
			seedX += 50;
		}
		seedX = 420;
		seedY = 190;
		// seed 27번 지점 x 4개
		for (int i = 87; i < 91; i++) {
			add(seed[i]);
			seed[i].setLocation(seedX, seedY);
			seedX += 50;
		}
		seedX = 520;
		seedY = 290;
		// seed 44번 지점 y 6개
		for (int i = 91; i < 97; i++) {
			add(seed[i]);
			seed[i].setLocation(seedX, seedY);
			seedY += 50;
		}
		seedX = 570;
		seedY = 290;
		// seed 45번 지점 y 6개
		for (int i = 97; i < 103; i++) {
			add(seed[i]);
			seed[i].setLocation(seedX, seedY);
			seedY += 50;
		}
		seedX = 270;
		seedY = 340;
		// seed 52번 지점 x 5개
		for (int i = 103; i < 108; i++) {
			add(seed[i]);
			seed[i].setLocation(seedX, seedY);
			seedX += 50;
		}
		seedX = 320;
		seedY = 470;
		// seed 70번 지점 y 4개
		for (int i = 108; i < 112; i++) {
			add(seed[i]);
			seed[i].setLocation(seedX, seedY);
			seedY += 50;
		}
		seedX = 270;
		seedY = 470;
		// seed 69번 지점 y 3개
		for (int i = 112; i < 115; i++) {
			add(seed[i]);
			seed[i].setLocation(seedX, seedY);
			seedY += 50;
		}
		seedX = 420;
		seedY = 470;
		// seed 72번 지점 y 4개
		for (int i = 115; i < 119; i++) {
			add(seed[i]);
			seed[i].setLocation(seedX, seedY);
			seedY += 50;
		}
		seedX = 470;
		seedY = 470;
		// seed 73번 지점 y 3개
		for (int i = 119; i < 122; i++) {
			add(seed[i]);
			seed[i].setLocation(seedX, seedY);
			seedY += 50;
		}
		seedX = 105;
		seedY = 300;
		// seed 41번 지점 y 3개
		for (int i = 122; i < 125; i++) {
			add(seed[i]);
			seed[i].setLocation(seedX, seedY);
			seedY += 50;
		}
		seedX = 625;
		seedY = 300;
		// seed 46번 지점 y 3개
		for (int i = 125; i < 128; i++) {
			add(seed[i]);
			seed[i].setLocation(seedX, seedY);
			seedY += 50;
		}
		// seed 12번 지점
		add(seed[129]);
		seed[129].setLocation(320, 90);
		// seed 13번 지점
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
					if (!player.isLeft() && !player.isLeftWallCrash() && !player.isDie() && !keyPressed) {
						keyPressed = true;
						player.setLeft(true);
						player.initThread();
					}
					break;
				case KeyEvent.VK_RIGHT:
					if (!player.isRight() && !player.isRightWallCrash() && !player.isDie() && !keyPressed) {
						keyPressed = true;
						player.setRight(true);
						player.initThread();
					}
					break;
				case KeyEvent.VK_UP:
					if (!player.isUp() && !player.isTopWallCrash() && !player.isDie() && !keyPressed) {
						keyPressed = true;
						player.setUp(true);
						player.initThread();
					}
					break;
				case KeyEvent.VK_DOWN:
					if (!player.isDown() && !player.isBottomWallCrash() && !player.isDie() && !keyPressed) {
						keyPressed = true;
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
					keyPressed = false;
					player.setLeft(false);
					break;
				case KeyEvent.VK_RIGHT:
					keyPressed = false;
					player.setRight(false);
					break;
				case KeyEvent.VK_UP:
					keyPressed = false;
					player.setUp(false);
					break;
				case KeyEvent.VK_DOWN:
					keyPressed = false;
					player.setDown(false);
					break;
				}
			}
		});
	}
} // end of class