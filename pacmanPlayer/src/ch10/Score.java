package ch10;

public class Score {
	private int score = 0;
	private int total;
	
	
	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public Score() {
		
	}
	
	public void scoreUp() {
		score += 50;
		total += score;
	}
}
