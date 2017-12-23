package mau;

import java.util.ArrayList;
import java.util.List;

import mau.card.Card;

public class Player {
	
	private List<Card> cards;
	private boolean computer;
	private boolean enabled;
	private String name;
	
	/**
	 * Default-Player ist ein computer
	 * @param name
	 */
	public Player(String name) {
		this(name, true);
	}

	public Player(String newName, boolean newComputer) {
		this.name = newName;
		this.computer = newComputer;
		this.cards = new ArrayList<>();
		this.enabled = false;
	}
	
	public List<Card> getCards() {
		return cards;
	}

	public boolean hasWon() {
		return cards.isEmpty();
	}
	
	public boolean isComputer() {
		return computer;
	}
	
	public boolean isEnabled() {
		return enabled;
	}
	
	public void setEnabled(boolean newEnabled) {
		enabled = newEnabled;
	}
	
	@Override
	public String toString() {
		return "Spieler \"" + name + "\"";
	}
	
}