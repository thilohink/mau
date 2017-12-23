package mau.gui;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import mau.Player;

import java.awt.Cursor;
import java.awt.Dimension;

public class DeckView extends JButton {

	private static final long serialVersionUID = 1L;

	private Player player;
	private DrawListener listener;
	
	public DeckView() {
		super(new ImageIcon(SpriteIO.CARD_BACK));
		setSize(new Dimension(120, 160));
		setPreferredSize(new Dimension(120, 160));
		
		// Button-Hintergrund soll NICHT gemalt werden
		setContentAreaFilled(false);
		
		// Keine Tastatur-Focus-Markierung (z.B. gestrichelter Rand)
		setFocusPainted(false);

		// Button-Actions werden auf den DrawListener "umgeleitet"
		addActionListener(e -> listener.handleDraw(player));
	}
	
	/**
	 * Liest das Modell der DeckView
	 * @return
	 */
	public Player getPlayer() {
		return player;
	}
	
	/**
	 * Liest den DrawListener
	 * @return
	 */
	public DrawListener getListener() {
		return listener;
	}
	
	/**
	 * Setzt das Modell der DeckView
	 * @param newPlayer
	 */
	public void setPlayer(Player newPlayer) {
		player = newPlayer;
		// Falls der Player ein computer ist (oder null), wird
		// - ein Standard-Mauszeiger gesetzt
		// - der Button deaktiviert
		if (player == null || player.isComputer()) {
			setCursor(Cursor.getDefaultCursor());
			setEnabled(false);
		} else {
			setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			setEnabled(true);
		}
	}
	
	/**
	 * Setzt den DrawListener
	 * @param newListener
	 */
	public void setListener(DrawListener newListener) {
		listener = newListener;
	}
	
}
