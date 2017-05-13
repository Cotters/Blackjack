package blackjack__game;


public class Card {
	private String suit;
	private String rank;
	
	Card(String suit, String value) {
		this.suit = suit;
		this.rank = value;
	}
	
	public String getSuit() {
		return this.suit;
	}
	
	public String getRank() {
		return this.rank;
	}
	
	public int getValue() {
		if (rank.equals("Jack") || rank.equals("Queen") || rank.equals("King")) {
			return 10;
		} else if (rank.equals("Ace")) {
//			System.out.println("Aces high or low?"); // need to check if player wants high or low ace.
			return 1;
		} else {
			return Integer.parseInt(this.rank);
		}
	}
	
	
}
