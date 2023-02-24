package ch10;

public class Life {
	private int life = 3;
	
	
	public int getLife() {
		return life;
	}

	public void setLife(int life) {
		this.life = life;
	}

	public Life() {
		
	}
	
	public void minusLife() {
		life--;
	}
}
