package projectFinish1;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class GameSuccessFrame extends JFrame implements ActionListener {
	private JLabel gameOverMap;
	private JButton startButton;
	private JLabel gameSuccess;

	public GameSuccessFrame() {
		initData();
		setInitLayout(); 
		addEventListener();
	}

	private void initData() {
		setTitle("팩맨");
		setSize(800, 800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameOverMap = new JLabel(new ImageIcon("images/gameEndMap.jpg"));
		setContentPane(gameOverMap);
		startButton = new JButton("Game Start!");
		gameSuccess = new JLabel(new ImageIcon("images/youWin.png"));
	}

	private void setInitLayout() {
		setVisible(true);
		gameOverMap.add(startButton);
		startButton.setSize(200, 50);
		startButton.setLocation(290, 600);
		startButton.setBorderPainted(false);
		startButton.setBackground(Color.yellow);
		gameSuccess.setSize(800, 200);
		gameSuccess.setLocation(10, 300);
		add(gameSuccess);
	}

	private void addEventListener() { 
		startButton.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton startButton = (JButton) e.getSource();
		new PacManFrame();
		setVisible(false);
	}

	public void paint(Graphics g) {
		super.paint(g);
		Font font1 = new Font("consolas", Font.BOLD, 20);
		g.setFont(font1);
		g.setColor(Color.white);
		g.drawString("Congratulations!", 320, 590);
	}
}
