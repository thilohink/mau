package mau.gui;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.util.List;

import javax.swing.JComponent;

import mau.Player;
import mau.card.Card;

public class PlayerView extends JComponent implements MouseListener, MouseMotionListener {

	private static final long serialVersionUID = 1L;

	private Card hover;
	private PlayListener listener;
	private Player player;
	
	public PlayerView() {
		super();
		setSize(300, 150);
		setPreferredSize(new Dimension(300, 150));
	}
	
	/**
	 * Berechnet ein Rechteck für die gegebene card, das angibt,
	 * an welcher Position und in welcher Größe das Bild einer Karte
	 * in der Komponente gezeichnet wird.
	 * 
	 * @see paintComponent, mouseMoved
	 * 
	 * @param card
	 * @return
	 */
	private Rectangle createBounds(Card card) {
		List<Card> cards = player.getCards();
		int marginLeft = 10; // 10 pixel Abstand vom linken Rand
		int marginTop  = 60; // 60 pixel Abstand vom oberen Rand
		int displayWidth = getWidth() - marginLeft;
		
		int cardOffset = displayWidth / cards.size();
		int cardIndex  = cards.indexOf(card);
		int cardPosX   = cardIndex * cardOffset; 
		
		return new Rectangle(cardPosX, marginTop, SpriteIO.CARD_WIDTH, SpriteIO.CARD_HEIGHT);
	}
	
	/**
	 * Erzeugt ein Bild (mittels SpriteIO) zu gegebener Karte.
	 * Falls das Modell (player) der Komponente ein Computer ist,
	 * wird nur eine Kartenrückseite zurück gegeben.
	 * @param card
	 * @return
	 */
	private BufferedImage createSprite(Card card) {
		BufferedImage result = SpriteIO.CARD_BACK;
		if (player.isComputer() == false) {
			result = SpriteIO.createSprite(card);
		}
		return result;
	}
	
	/**
	 * Liest den PlayListener
	 * @return
	 */
	public PlayListener getListener() {
		return listener;
	}
	
	/**
	 * Liest das Modell der PlayerView
	 * @return
	 */
	public Player getPlayer() {
		return player;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// Falls
		// - die linke Maustaste gedrückt UND
		// - eine hover-Karte existiert UND
		// - ein playListener existiert
		// => Nachricht an den playListener
		if (e.getButton() == MouseEvent.BUTTON1
		 && hover != null
		 && listener != null) {
			listener.handlePlay(player, hover);
		}
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// Verlässt die Maus die Komponente, wird auch das hover zurück gesetzt
		setHover(null);
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		Card newHover = null;
		// Es wird die Karte "unter" dem Mauszeiger ermittelt
		for(Card card: player.getCards()) {
			Rectangle rect = createBounds(card);
			if (rect.contains(e.getPoint())) {
				newHover = card;
			}
		}
		setHover(newHover);
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		// Extra-Wurst für den WindowBuilder
		if (player == null) {
			g.drawString("<playerView>", 10, getHeight()/2);
			return;
		}
		
		// Graphics2D-API für kantengeglättete Schrift
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);
		
		// Beschriftung des Spielers
		if (player.isEnabled()) {
			g2d.setFont(new Font("Arial", Font.BOLD, 16));
			g2d.drawString(player + " ist am Zug", 10, 30);
		} else {
			g2d.setFont(new Font("Arial", Font.PLAIN, 16));
			g2d.drawString(player.toString(), 10, 30);
		}
		
		// Darstellung der Karten
		for(Card card: player.getCards()) {
			Rectangle rect = createBounds(card);
			if (card == hover) {
				// Die hover-Karte erscheint 20 pixel nach "oben" versetzt
				g2d.drawImage(createSprite(card), rect.x, rect.y - 20, null);
			} else {
				g2d.drawImage(createSprite(card), rect.x, rect.y, null);
			}
		}
		
		// Fusszeile
		g2d.drawLine(0, getHeight()-1, getWidth()-1, getHeight()-1);
	}
	
	private void setHover(Card newHover) {
		Card oldHover = hover;
		hover = newHover;
		if (hover == null) {
			setCursor(Cursor.getDefaultCursor());
		} else {
			setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		}
		// malt die Komponente neu, falls sich hover ändert
		if (oldHover != newHover) {
			repaint();
		}
	}

	/**
	 * Setzt den PlayListener
	 * @param newListener
	 */
	public void setListener(PlayListener newListener) {
		listener = newListener;
	}

	/**
	 * Setzt das Modell der PlayerView
	 * @param newPlayer
	 */
	public void setPlayer(Player newPlayer) {
		player = newPlayer;
		if (player.isComputer() == false) {
			addMouseListener(this);
			addMouseMotionListener(this);
		}
		repaint(); // malt die Komponente neu
	}
	
}
