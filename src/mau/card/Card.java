package mau.card;

public class Card {

	private String suit;
	private String value;
	
	public Card(String newSuit, String newValue) {
		this.suit = newSuit;
		this.value = newValue;
	}
	
	public String getSuit() {
		return suit;
	}
	
	public String getValue() {
		return value;
	}

	public boolean matches(Card anotherCard) {
		return matchesSuit(anotherCard) || matchesValue(anotherCard);
	}
	
	public boolean matchesSuit(Card anotherCard) {
		return suit.equals(anotherCard.suit);
	}
	
	public boolean matchesValue(Card anotherCard) {
		return value.equals(anotherCard.value);
	}
	
	@Override
	public String toString() {
		return suit + " " + value;
	}
	
}
