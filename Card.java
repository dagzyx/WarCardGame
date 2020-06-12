package testgame;

public class Card {
	
	private int num;
	private int suit;
	
	public Card(int num, int suit) {
		this.num = num;
		this.suit = suit;
	}
	
	public int getCard() {
		return this.num;
	}
	
	public void setCard(int num) {
		this.num = num;
	}
	
	@Override
	public String toString() {
		
		StringBuilder displayCard = new StringBuilder();
		
		switch(num) {
			case 11:
				displayCard.append("Jack");
				break;
			case 12:
				displayCard.append("Queen");
				break;
			case 13:
				displayCard.append("King");
				break;
			case 14:
				displayCard.append("Ace");
				break;
			default:
				displayCard.append(num);
				break;
		}
		
		displayCard.append(" of ");
		
		switch(suit) {
			case 0:
				displayCard.append("Spades");
				break;
			case 1:
				displayCard.append("Clubs");
				break;
			case 2:
				displayCard.append("Hearts");
				break;
			case 3:
				displayCard.append("Diamonds");
				break;
			default:
				break;
		}
		
		return displayCard.toString();
	}
	

}
