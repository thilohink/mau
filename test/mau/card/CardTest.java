package mau.card;

public class CardTest {

	public static void main(String[] args) {
		
		Card herzAcht = new Card("Herz", "Acht");
		Card karoAcht = new Card("Karo", "Acht");
		Card karoNeun = new Card("Karo", "Neun");
		
		testMatch(herzAcht, karoAcht); // erwartet true
		testMatch(karoAcht, karoNeun); // erwartet true
		testMatch(herzAcht, karoNeun); // erwartet false
		
	}
	
	static void testMatch(Card card1, Card card2) {
		boolean match = card1.matches(card2);
		
		System.out.println("CardTest.testMatch( " + card1 + ", " + card2 + " ) = " + match);
	}
	
}
