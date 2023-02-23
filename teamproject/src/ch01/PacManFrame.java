package ch01;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

public class PacManFrame extends JFrame{
	
	private Player player;
	
	public PacManFrame() {
		initDaya();
		setInitLayout();
		addEventListener();
	}
	
	public void initDaya() {
		setTitle("팩맨");
		setSize(600, 600);
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
					if(!player.isLeft()) {
						player.left();
					}
					break;
				case KeyEvent.VK_RIGHT:
					if(!player.isRight()) {
						player.right();
					}
					break;
				case KeyEvent.VK_UP:
					if(!player.isUp()) {
						player.up();
					}
					break;
				case KeyEvent.VK_DOWN:
					if(!player.isDown()) {
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
