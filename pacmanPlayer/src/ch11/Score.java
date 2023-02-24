package ch11;

public class Score {
	private static int score = 0;
	
	
	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public Score() {
		
	}
	
	public int scoreUp() {
		this.score += 50;
		return score;
	}
}
