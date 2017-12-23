package mau.gui;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import mau.card.Card;

import java.awt.Dimension;

public class CardView extends JLabel {

	private static final long serialVersionUID = 1L;
	
	private Card card;

	public CardView() {
		super("");
		setIcon(new ImageIcon(SpriteIO.CARD_JOKER));
		setPreferredSize(new Dimension(120, 160));
		setSize(new Dimension(120, 160));
		setHorizontalAlignment(SwingConstants.CENTER);
	}
	
	/**
	 * Liest das Modell der CardView
	 * @return
	 */
	public Card getCard() {
		return card;
	}
	
	/**
	 * Setzt das Modell der CardView
	 * @param newCard
	 */
	public void setCard(Card newCard) {
		card = newCard;
		setIcon(new ImageIcon(SpriteIO.createSprite(newCard)));
	}

}
