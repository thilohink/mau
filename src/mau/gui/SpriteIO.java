package mau.gui;

import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import mau.card.Card;
import mau.card.Deck;

public class SpriteIO {

	private static final URL SPRITE_SHEET_URL = SpriteIO.class.getResource("/mau/res/cards_1300x720.png");
	private static final BufferedImage SPRITE_SHEET = loadSpriteSheet();

	public static final Integer CARD_HEIGHT = 144; // pixel
	public static final Integer CARD_WIDTH = 100; // pixel
	
	public static final BufferedImage CARD_BACK = createSprite(4, 2);
	public static final BufferedImage CARD_JOKER = createSprite(4, 1);
	
	private static final Map<String, Integer> MAP_SUITS = createMapSuits();
	private static final Map<String, Integer> MAP_VALUES = createMapValues();
	
	
	private static Map<String, Integer> createMapSuits() {
		Map<String, Integer> result = new HashMap<>();
		for(int row=0; row<Deck.SUITS.length; row++) {
			result.put(Deck.SUITS[row], row);
		}
		return result;
	}
	
	private static Map<String, Integer> createMapValues() {
		Map<String, Integer> result = new HashMap<>();
		for(int col=0; col<Deck.VALUES.length; col++) {
			result.put(Deck.VALUES[col], col);
		}
		return result;
	}
	
	public static BufferedImage createSprite(Card card) {
		Integer cardRow = MAP_SUITS.get(card.getSuit());
		Integer cardCol = MAP_VALUES.get(card.getValue());
		return createSprite(cardRow, cardCol);
	}
	
	public static BufferedImage createSprite(Integer row, Integer col) {
		Integer posX = col * CARD_WIDTH;
		Integer posY = row * CARD_HEIGHT;
		
		return SPRITE_SHEET.getSubimage(posX, posY, CARD_WIDTH, CARD_HEIGHT);
	}
	
	private static BufferedImage loadSpriteSheet() {
		BufferedImage result = null;
		try {
			result = ImageIO.read(SPRITE_SHEET_URL);
		} catch (Exception ex) {
			System.err.println("SpriteIO.loadSpriteSheet( " + SPRITE_SHEET_URL + " ) FAILED: " + ex);
		}
		return result;
	}
	
}
