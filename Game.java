package blackjack__game;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game {

	List<Card> deck = new ArrayList<Card>();
	String[] cardNumber = {"Ace","2","3","4","5","6","7","8","9","10","Jack", "Queen","King"};
	String[] cardSuit = {"Hearts","Clubs","Diamonds","Spades"};
	
	List<Card> p1 = new ArrayList<Card>();
	List<Card> dealer = new ArrayList<Card>();
	
	Scanner scanner = new Scanner(System.in);
		
	public void makeDeck() { // return deck
		deck.clear();
		
		for (String suit : cardSuit) {
			for (String number: cardNumber){
				Card c = new Card(suit, number);
				deck.add(c);
			}
		}
	}
	
	public Card getCard() { // Gets cards for player (also used in dealCards func)
		Card card; // Make sure to return a card
		int randIndex = (int)(Math.random()*deck.size()); // Use rnd num to select a card in the deck
		
		if (!deck.isEmpty()) {
			card = deck.get(randIndex);
			deck.remove(card);
		} else {
			System.out.println("No more cards left. Making new deck...");
			makeDeck();
			card = getCard();
		}
		return card;
	}
	
	public void dealCards() { // this function will get the starting hand.
		List<Card> p1Hand = new ArrayList<Card>();
		List<Card> dHand = new ArrayList<Card>();
		
		
		for (int i = 0;i<2;i++) { // Starting hand is 2
			p1Hand.add(getCard());
		}
		p1 = p1Hand;
		
		for (int i = 0;i<2;i++) { // Starting hand is 2
			dHand.add(getCard());
		}
		dealer = dHand;
	}
	
	public void checkWinner() {
		int p1Score = 0;
		int dScore = 0;
		
		p1Score = sumHand(p1);
		dScore = sumHand(dealer);
		
		// Check if either player is bust
		if (checkBust(p1))
			System.out.println("Dealer wins!");
		else if (checkBust(dealer))
			System.out.println("Player 1 wins!");
		else
			// If neither is bust - check if player has won
			System.out.println(p1Score > dScore ? "Player 1 wins!" : "Dealer wins!");
		
		newGame();
	}
	
	public boolean checkBust(List<Card> hand) {
		if (sumHand(hand) > 21) {
			return true;
		}
		return false;
	}
	
	public int sumHand(List<Card> hand) {
		int score = 0;
		
		for (int i=0;i<hand.size();i++) {
			score += hand.get(i).getValue(); // get player 1 score
		}
		
		return score;
	}
	
	public void newGame() {
		
		System.out.println("Would you like to play a new game? (y/n)");
		if (scanner.next().equals("y")) {
			makeDeck();
			startGame();
		}
		scanner.close();
		System.exit(0);
	}
	
	public void printHand(List<Card> hand) {
		for (Card card : hand) {
			System.out.print("| " + card.getRank() + " of " + card.getSuit() + " |");
		}
		System.out.println();
	}
	
	public void startGame() {
		dealCards(); // Deals both players hands
		
		
		System.out.println();
		System.out.println("Starting a new game...");
		System.out.println();
		
		playHand(p1);
		playHand(dealer);
		
		checkWinner(); // Print who wins
	}
	
	public void playHand(List<Card> hand) {
		boolean stick = false;
		
		while (!stick) {
			printHand(hand);
			if (checkBust(hand))
				checkWinner();
			System.out.println("Player one: Hit or stick? (h/s)");
			if (scanner.next().equals("h")) {
				hand.add(getCard());
			} else {
				stick = true;
			}
		}
	}
	
}
