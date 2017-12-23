package mau;

import mau.card.Card;
import mau.card.Deck;

public class PlayerTest {

	public static void main(String[] args) {
		
		Deck deck = new Deck();
		Player tester = new Player("Tester");
		
		testAddCards(tester, deck);
		testRemoveCards(tester);
		testHasWon(tester);
	}
	
	static void list(Player player) {
		System.out.println(player);
		System.out.println("Karten:");
		if (player.getCards().isEmpty()) {
			System.out.println(" - KEINE");
		} else {
			for (Card card: player.getCards()) {
				System.out.println(" - " + card);
			}
		}
	}
	
	static void testAddCards(Player player, Deck deck) {
		System.out.println("PlayerTest.testAddCards()");
		player.getCards().addAll(deck.deal(5));
		player.getCards().add(deck.deal());
		player.getCards().add(deck.deal());
		list(player);
		System.out.println();
	}
	
	static void testRemoveCards(Player player) {
		System.out.println("PlayerTest.testRemoveCards()");
		player.getCards().remove(0);
		player.getCards().remove(0);
		list(player);
		System.out.println();
	}
	
	static void testHasWon(Player player) {
		System.out.println("PlayerTest.testHasWon()");
		while (player.hasWon() == false) {
			System.out.println(player + " spielt " + player.getCards().remove(0));
		}
		System.out.println(player + " hat gewonnen!");
		System.out.println();
	}
	

	
}
