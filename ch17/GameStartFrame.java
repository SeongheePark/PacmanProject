package ch17;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class GameStartFrame extends JFrame implements ActionListener {
	private JLabel startMap;
	private JButton startButton;

	public GameStartFrame() {
		initData();
		setInitLayout();
		addEventListener();
	}

	private void initData() {
		setTitle("팩맨");
		setSize(800, 800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		startMap = new JLabel(new ImageIcon("images/startMap.jpg"));
		setContentPane(startMap);
		startButton = new JButton("Game Start");
	}

	private void setInitLayout() {
		setVisible(true);
		startMap.add(startButton);
		startButton.setSize(230, 50);
		startButton.setLocation(270, 600);
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
		setVisible(false);
	}

	public void paint(Graphics g) {
		super.paint(g);
		Font startFont = new Font("consolas", Font.BOLD, 50);
		g.setFont(startFont);
		g.setColor(Color.white);
		g.drawString("Let's Play!", 250, 590);
		Font projectName = new Font("consolas", Font.BOLD, 15);
		g.setFont(projectName);
		g.setColor(Color.white);
		g.drawString("made by ProjectC", 620, 770);
	}

	public static void main(String[] args) {
		new GameStartFrame();
	}

}
