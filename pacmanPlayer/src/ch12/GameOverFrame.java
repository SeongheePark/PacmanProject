package ch12;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class GameOverFrame extends JFrame implements ActionListener {
	private JLabel gameOverMap;
	private JButton startButton;

	public GameOverFrame() {
		initData();
		setInitLayout();
		addEventListener();
	}

	private void initData() {
		setTitle("팩맨");
		setSize(800, 800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameOverMap = new JLabel(new ImageIcon("images/gameOverMap.jpg"));
		setContentPane(gameOverMap);
		startButton = new JButton("Game Start!");
	}

	private void setInitLayout() {
		setVisible(true);
		gameOverMap.add(startButton);
		startButton.setSize(200, 50);
		startButton.setLocation(290, 530);
		startButton.setBorderPainted(false);
		startButton.setBackground(Color.yellow);
	}

	private void addEventListener() {
		startButton.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton startButton = (JButton) e.getSource();
		new PacManFrame();
		new Score();
		setVisible(false);
	}
	 
	public void paint(Graphics g) {
		super.paint(g);
		Font font = new Font("맑은 고딕", Font.BOLD, 20);
		g.setFont(font);
		g.setColor(Color.white);
		g.drawString("다시 도전해보세요!", 310, 470);
	}
	public static void main(String[] args) {
		new GameOverFrame();
	}
}
