package mau.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
	
	public static boolean DEBUG = false;
	
	public static final String[] SUITS = new String[] {
			"Kreuz",
			"Karo",
			"Herz",
			"Pik",
	};
	
	public static final String[] VALUES = new String[] {
			"Ass",
			"Zwei",
			"Drei",
			"Vier",
			"Fünf",
			"Sechs",
			"Sieben",
			"Acht",
			"Neun",
			"Zehn",
			"Bube",
			"Dame",
			"König",
	};
	
	private List<Card> cards;
	
	public Deck() {
		cards = new ArrayList<>();
		for(String suit: SUITS) {
			for(String value: VALUES) {
				cards.add(new Card(suit, value));
			}
		}
		if (!DEBUG) {
			Collections.shuffle(cards);
		}
	}
	
	public Card deal() {
		return cards.remove(0);
	}
	
	public List<Card> deal(int amount) {
		List<Card> result = new ArrayList<>();
		while (result.size() < amount) {
			result.add(deal());
		}
		return result;
	}
	
	public boolean hasCards() {
		return cards.isEmpty() == false;
	}

	public void push(Card card) {
		cards.add(card);
		if (!DEBUG) {
			Collections.shuffle(cards);
		}
	}
	
	public int size() {
		return cards.size();
	}
	
}
