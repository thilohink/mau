package mau;

import java.util.ArrayList;
import java.util.List;

import javax.swing.UIManager;

import mau.card.Card;
import mau.card.Deck;
import mau.gui.DrawListener;
import mau.gui.GameView;
import mau.gui.PlayListener;

public class Game implements DrawListener, PlayListener {
	
	public static final int MAX_CARDS = 5; // Anzahl der Karten pro Spieler zu Beginn
	
	public static void main(String[] args) throws Exception {
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		
		Game game = new Game();
		game.addPlayer("Homer Simpson");
		game.addComputer("HAL 9000");
		game.addComputer("C64");
		
		game.prepare();
		game.start();
	}
	
	@Override
	public void handleDraw(Player performer) {
		if (performer.isEnabled()) {
			performer.setEnabled(false);
			performDraw();
		}
	}
	
	@Override
	public void handlePlay(Player performer, Card card) {
		if (performer.isEnabled()) {
			performer.setEnabled(performPlay(card));
		} else {
			view.report(performer + ", du bist doch gar nicht dran!");
		}
	}
	
	private Deck deck;
	private Player player;
	private List<Player> playerList;
	private Card topCard;
	private int turnCount;
	private String turnInfo;
	private GameView view;
	
	public Game() {
		playerList = new ArrayList<>();
		view = new GameView();
	}
	
	public void addComputer(String name) {
		playerList.add(new Player(name));
	}
	
	public void addPlayer(String name) {
		playerList.add(new Player(name, false));
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public List<Player> getPlayerList() {
		return playerList;
	}
	
	public Card getTopCard() {
		return topCard;
	}
	
	public String getTurnInfo() {
		return turnInfo;
	}
	
	public void nextPlayer() {
		int next = playerList.indexOf(player) + 1;
		if (next >= playerList.size()) {
			next = 0;
		}
		setPlayer(playerList.get(next));
	}
	
	public void nextTurn() {
		turnCount++;
		nextPlayer();
		update();
		if (player.isComputer()) {
			performComputedTurn();
		}
	}
	
	private void performComputedTurn() {
		boolean performedPlay = false;
		for(Card card: player.getCards()) {
			performedPlay = performPlay(card);
			if (performedPlay) {
				break;
			}
		}
		if(!performedPlay) {
			performDraw();
		}
	}
	
	private void performDraw() {
		Card card = deck.deal();
		player.getCards().add(card);
		// Falls ein Computer zieht, ist die Karteninfo verborgen
		if (player.isComputer()) {
			turnInfo = "Zug #" + turnCount + ": " + player + " zieht eine Karte";
		} else {
			turnInfo = "Zug #" + turnCount + ": " + player + " zieht " + card;
		}
		updateAndProceed();
	}

	private boolean performPlay(Card card) {
		boolean result = false;
		if (topCard.matches(card)) {
			setTopCard(card);
			player.getCards().remove(card);
			turnInfo = "Zug #" + turnCount + ": " + player + " spielt " + card;
			result = true;
			updateAndProceed();
		}
		return result;
	}
	
	public void prepare() {
		deck = new Deck();
		setPlayer(null);
		for(Player p: playerList) {
			p.getCards().addAll(deck.deal(MAX_CARDS));
		}
		topCard = deck.deal();
		turnCount = 0;
		turnInfo = "Ein neues Spiel wurde gestartet.";
	}
	
	private void setPlayer(Player newPlayer) {
		Player oldPlayer = player;
		player = newPlayer;
		if (oldPlayer != null) {
			oldPlayer.setEnabled(false);
		}
		if (newPlayer != null) {
			newPlayer.setEnabled(true);
		}
	}
	
	private void setTopCard(Card newTopCard) {
		Card oldTopCard = topCard;
		topCard = newTopCard;
		deck.push(oldTopCard);
	}
	
	public void start() {
		view.install(this);
		view.update(this);
		update();
		view.setVisible(true);
		
		nextTurn();
	}
	
	/**
	 * Aktualisiert die GameView.
	 * Ist deshalb in einer eigenen Methode, um sie
	 * in Ableitungen (-> GameSimulation) lahmlegen zu können.
	 */
	public void update() {
		view.update(this);
	}
	
	public void updateAndProceed() {
		view.update(this);
		if (player.hasWon()) {
			view.report("Spielende! " + player + " hat gewonnen!");
		} else {
			view.requestNextTurn();
		}
	}
	
}
