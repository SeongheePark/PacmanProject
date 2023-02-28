package projectFinish;

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

	// 팩맨 요소들
	private JLabel backgroundMap;
	private Player player;
	private JLabel[] seed = new JLabel[131];
	private PacManFrame mContext = this;
	private Score score = new Score();
	private ArrayList<JLabel> itemList = new ArrayList<>();
	private ArrayList<Enemy> enemyList = new ArrayList<>();
	// 남은 생명 이미지로 보여줄 수 있는 이미지
	private JLabel[] life = new JLabel[3];
	// 생명 좌표
	private int lifeX;
	private int lifeY;
	// 키 동시입력 막는 용도
	private boolean keyPressed;
	// 게임 끝 판단 용도
	private boolean gameOver;
	// 씨앗 좌표
	private int seedX;
	private int seedY;
	// 인게임 bgm
	private InGameBGM gameBGM;

	// 생성자
	public PacManFrame() {
		initData();
		setInitLayout();
		addEventListener();
		new Thread(new BackgroundPlayerService(player, this)).start();
		for (int i = 0; i < enemyList.size(); i++) {
			new Thread(new BackgroundEnemyService(enemyList.get(i), this)).start();
		}
		gameBGM = new InGameBGM();
	}

	// score 화면 표시
	public void paint(Graphics g) {
		super.paint(g);
		Font font1 = new Font("맑은 고딕", Font.BOLD, 20);
		g.setFont(font1);
		g.setColor(Color.white);
		g.drawString("Score", 600, 780);
		g.drawString(score.getScore() + "점", 680, 780);
		// warning 화면 표시
		if (enemyList.get(0).isFastMode()) {
			super.paint(g);
			Font font = new Font("consolas", Font.BOLD, 50);
			g.setFont(font);
			g.setColor(Color.red);
			g.drawString("Warning", 300, 430);
		}
	}

	public ArrayList<JLabel> getMarbleList() {
		return itemList;
	}

	public void setMarbleList(ArrayList<JLabel> itemList) {
		this.itemList = itemList;
	}

	public item getMarble(int i) {
		return (item) itemList.get(i);
	}

	public InGameBGM getGameBGM() {
		return gameBGM;
	}

	public void setGameBGM(InGameBGM gameBGM) {
		this.gameBGM = gameBGM;
	}

	public Score getScore() {
		return score;
	}

	public int getScoreN() {
		return score.getScore();
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

	public JLabel[] getLife() {
		return life;
	}

	public JLabel getLife(int i) {
		return life[i];
	}

	public void setLife(JLabel[] life) {
		this.life = life;
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

	public void initData() {
		setTitle("팩맨");
		setSize(800, 800);
		backgroundMap = new JLabel(new ImageIcon("images/backgroundMap.png"));
		setContentPane(backgroundMap);
		player = new Player(mContext);
		for (int i = 0; i < seed.length; i++) {
			seed[i] = new JLabel(new ImageIcon("images/seed.png"));
			seed[i].setSize(50, 50);
		}
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		makeEnemies();
		makeMarble();
		for (int i = 0; i < life.length; i++) {
			life[i] = new JLabel(new ImageIcon("images/life.png"));
			life[i].setSize(50, 50);
		}
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
	}

	public void makeMarble() {
		itemList.add(new item(55, 204));
		itemList.add(new item(55, 516));
		itemList.add(new item(680, 204));
		itemList.add(new item(680, 516));
	}

	public void setInitLayout() {
		setLayout(null);
		add(player);
		lifeX = 400;
		lifeY = 715;
		for (int i = 0; i < life.length; i++) {
			add(life[i]);
			life[i].setLocation(lifeX, lifeY);
			lifeX += 50;
		}

		// 유령
		for (int i = 0; i < enemyList.size(); i++) {
			add(enemyList.get(i));
		}
		// 구슬
		for (int i = 0; i < itemList.size(); i++) {
			add(itemList.get(i));
		}
		// seed 1번 지점 x 6개찍기
		for (int i = 0; i < 6; i++) {
			add(seed[i]);
			seed[i].setLocation(seedX, seedY);
			seedX += 53;
		}
		seedX = 420;
		// seed 6번지점 x 6개찍기
		for (int i = 6; i < 12; i++) {
			add(seed[i]);
			seed[i].setLocation(seedX, seedY);
			seedX += 52;
		}
		seedX = 55;
		seedY = 670;
		// seed 96번지점 x 6개 찍기
		for (int i = 14; i < 20; i++) {
			add(seed[i]);
			seed[i].setLocation(seedX, seedY);
			seedX += 53;
		}
		seedX = 420;
		// seed 101번지점 x 6개찍기
		for (int i = 20; i < 26; i++) {
			add(seed[i]);
			seed[i].setLocation(seedX, seedY);
			seedX += 52;
		}
		seedX = 55;
		seedY = 100;
		// seed 1번 지점 y 11개 찍기
		for (int i = 26; i < 37; i++) {
			// 아이템 부분 씨앗 없애기
			if (i == 28) {
				seedY = 256;
				continue;
			} else if (i == 34) {
				seedY = 568;
				continue;
			}
			add(seed[i]);
			seed[i].setLocation(seedX, seedY);
			seedY += 52;
		}
		seedX = 680;
		seedY = 100;
		// seed 10번 지점 y 11개 찍기
		for (int i = 37; i < 48; i++) {
			// 아이템 부분 씨앗 없애기
			if (i == 39) {
				seedY = 256;
				continue;
			} else if (i == 45) {
				seedY = 568;
				continue;
			}
			add(seed[i]);
			seed[i].setLocation(seedX, seedY);
			seedY += 52;
		}
		// seed 16번 지점 y 9개 찍기
		seedX = 160;
		seedY = 140;
		for (int i = 48; i < 57; i++) {
			add(seed[i]);
			seed[i].setLocation(seedX, seedY);
			seedY += 53;
		} // seed 17번 지점 y 9개 찍기
		seedX = 215;
		seedY = 140;
		for (int i = 57; i < 66; i++) {
			add(seed[i]);
			seed[i].setLocation(seedX, seedY);
			seedY += 53;
		}
		seedX = 270;
		seedY = 190;
		// seed 25번 지점 x 2개 찍기
		for (int i = 66; i < 68; i++) {
			add(seed[i]);
			seed[i].setLocation(seedX, seedY);
			seedX += 50;
		}
		seedX = 270;
		seedY = 140;
		// seed 17번 지점 x 2개 찍기
		for (int i = 70; i < 72; i++) {
			add(seed[i]);
			seed[i].setLocation(seedX, seedY);
			seedX += 50;
		}
		seedX = 270;
		seedY = 245;
		// seed 33번 지점 x 5개 찍기
		for (int i = 72; i < 77; i++) {
			add(seed[i]);
			seed[i].setLocation(seedX, seedY);
			seedX += 50;
		}
		// seed 19번 지점 x 2개
		seedX = 420;
		seedY = 140;
		for (int i = 77; i < 79; i++) {
			add(seed[i]);
			seed[i].setLocation(seedX, seedY);
			seedX += 50;
		}
		seedX = 420;
		seedY = 190;
		// seed 27번 지점 x 2개
		for (int i = 79; i < 81; i++) {
			add(seed[i]);
			seed[i].setLocation(seedX, seedY);
			seedX += 50;
		}
		seedX = 520;
		seedY = 140;
		// seed 44번 지점 y 9개
		for (int i = 81; i < 90; i++) {
			add(seed[i]);
			seed[i].setLocation(seedX, seedY);
			seedY += 53;
		}
		seedX = 570;
		seedY = 140;
		// seed 45번 지점 y 9개
		for (int i = 90; i < 99; i++) {
			add(seed[i]);
			seed[i].setLocation(seedX, seedY);
			seedY += 53;
		}
		seedX = 270;
		seedY = 350;
		// seed 52번 지점 x 5개
		for (int i = 99; i < 104; i++) {
			add(seed[i]);
			seed[i].setLocation(seedX, seedY);
			seedX += 50;
		}
		seedX = 320;
		seedY = 460;
		// seed 70번 지점 y 4개
		for (int i = 104; i < 108; i++) {
			add(seed[i]);
			seed[i].setLocation(seedX, seedY);
			seedY += 53;
		}
		seedX = 270;
		seedY = 460;
		// seed 69번 지점 y 3개
		for (int i = 108; i < 111; i++) {
			add(seed[i]);
			seed[i].setLocation(seedX, seedY);
			seedY += 53;
		}
		seedX = 420;
		seedY = 460;
		// seed 72번 지점 y 4개
		for (int i = 111; i < 115; i++) {
			add(seed[i]);
			seed[i].setLocation(seedX, seedY);
			seedY += 53;
		}
		seedX = 470;
		seedY = 460;
		// seed 73번 지점 y 3개
		for (int i = 115; i < 118; i++) {
			add(seed[i]);
			seed[i].setLocation(seedX, seedY);
			seedY += 53;
		}
		seedX = 105;
		seedY = 300;
		// seed 41번 지점 y 3개
		for (int i = 118; i < 121; i++) {
			add(seed[i]);
			seed[i].setLocation(seedX, seedY);
			seedY += 50;
		}
		seedX = 625;
		seedY = 300;
		// seed 46번 지점 y 3개
		for (int i = 121; i < 124; i++) {
			add(seed[i]);
			seed[i].setLocation(seedX, seedY);
			seedY += 50;
		}
		// seed 12번 지점
		add(seed[125]);
		seed[125].setLocation(320, 90);
		// seed 13번 지점
		add(seed[126]);
		seed[126].setLocation(420, 90);
		// seed 13번 지점
		add(seed[127]);
		seed[127].setLocation(370, 460);

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