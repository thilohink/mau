package mau.card;

public class DeckTest {

	public static void main(String[] args) {
		
		Deck deck = new Deck();
		
		testDealing(deck);
		testPushing(deck);
		
	}
	
	static void testDealing(Deck deck) {
		System.out.println("DeckTest.testDealing()");
		int count = 0;
		while (deck.hasCards()) {
			count ++;
			System.out.println(count + ": gebe " + deck.deal());
		}
		System.out.println("Das deck hat jetzt " + deck.size() + " Karten.");
		System.out.println();
	}
	
	static void testPushing(Deck deck) {
		System.out.println("DeckTest.testPushing()");
		Card[] cards = new Card[] {
				new Card("Herz", "Acht"),
				new Card("Karo", "Acht"),
				new Card("Karo", "Neun"),
		};
		for (Card card: cards) {
			deck.push(card);
		}
		System.out.println("Das deck hat jetzt " + deck.size() + " Karten.");
		System.out.println();
	}
	
}
