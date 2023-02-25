package ch15;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class GameStartFrame extends JFrame implements ActionListener{
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
		startButton = new JButton("Game Start!"); 
	}

	private void setInitLayout() {
		setVisible(true);
		startMap.add(startButton);
		startButton.setSize(200, 50);
		startButton.setLocation(280, 550);
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

	public static void main(String[] args) {
		new GameStartFrame();
	}

}
