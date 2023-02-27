package projectFinish;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Marble extends JLabel{

	private int x = 150;
	private int y = 150;
	private ImageIcon marble;

	public Marble(int x,int y) {
		setInitData(x, y);
		setInitLayout();
	}

	public int getX() {
		return x;
	}

	public int setX(int x) {
		this.x = x;
		return x;
	}

	public int getY() {
		return y;
	}

	public int setY(int y) {
		this.y = y;
		return y;
	}
	
	private void setInitData(int x, int y) {
		this.x = x;
		this.y = y;
		marble = new ImageIcon("images/stopwatch.png");
	}

	private void setInitLayout() {
		setSize(50, 50);
		setLocation(x, y);
		setIcon(marble);
	}
}
