package mau.gui;

import mau.Player;
import mau.card.Card;

public interface PlayListener {

	public void handlePlay(Player performer, Card card);
	
}
