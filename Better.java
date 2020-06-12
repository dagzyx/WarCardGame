package testgame;

public class Better {
	
	private int wins;
	private String choice;
	
	public Better() {
		this.wins = 0;
		this.choice = "none";
	}
	
	public int getWins() {
		return this.wins;
	}
	
	public void incrementWins() {
		this.wins++;
	}
	
	public void setWins(int wins) {
		this.wins = wins;
	}
	
	public String getChoice() {
		return this.choice;
	}
	
	public void setChoice(String choice) {
		this.choice = choice;
	}

}
