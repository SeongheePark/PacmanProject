package ch02;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class PacManFrame extends JFrame{
	
	private Player player;
	private JLabel background;
	
	public PacManFrame() {
		initDaya();
		setInitLayout();
		addEventListener();
		new Thread(new BackgroundPlayerService(player)).start();
	}
	
	public void initDaya() {
		background = new JLabel(new ImageIcon("images/background5.png"));
		setTitle("팩맨");
		setContentPane(background);
		setSize(800, 800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		player = new Player();
	}
	
	public void setInitLayout() {
		setLayout(null);
		setResizable(false);
		setVisible(true);
		add(player);
	}
	
	public void addEventListener() { 
		this.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyCode()) {
				case KeyEvent.VK_LEFT:
					if(!player.isLeft() && !player.isLeftWallCrash()) {
						player.left();
					}
					break;
				case KeyEvent.VK_RIGHT:
					if(!player.isRight() && !player.isRightWallCrash()) {
						player.right();
					}
					break;
				case KeyEvent.VK_UP:
					if(!player.isUp() && !player.isTopWallCrash()) {
						player.up();
					}
					break;
				case KeyEvent.VK_DOWN:
					if(!player.isDown() && !player.isBottomWallCrash()) {
						player.down();
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
	
	// 코드 테스트
	public static void main(String[] args) {
		new PacManFrame();
	}
	
}
