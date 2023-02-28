package projectFinish;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class item extends JLabel{

	private int x = 150;
	private int y = 150;
	private ImageIcon item;

	public item(int x,int y) {
		setInitData(x, y);
		setInitLayout();
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	private void setInitData(int x, int y) {
		this.x = x;
		this.y = y;
		item = new ImageIcon("images/stopwatch.png");
	}

	private void setInitLayout() {
		setSize(50, 50);
		setLocation(x, y);
		setIcon(item);
	}
}
