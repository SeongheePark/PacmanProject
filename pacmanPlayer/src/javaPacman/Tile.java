package javaPacman;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.JFrame;

public class Tile extends Rectangle {

	public Tile(int x, int y) {
		setBounds(x, y, 50, 50);
	}

	public void render(Graphics g) {
		g.setColor(new Color(255, 0, 0));
		g.fillRect(x, y, width, height);
	}
}
