package mau.gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import mau.Game;
import mau.Player;

import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.HashMap;
import java.util.Map;
import java.awt.Dimension;
import java.awt.Color;

public class GameView extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private CardView cardView;
	private DeckView deckView;
	private JLabel lblTurnInfo;
	private Map<Player, PlayerView> players;
	private JPanel pnlPlayers;
	private Timer timer;

	public GameView() {
		getContentPane().setBackground(new Color(50, 205, 50));
		setMinimumSize(new Dimension(600, 400));
		setSize(new Dimension(600, 400));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel pnlTurnInfo = new JPanel();
		pnlTurnInfo.setOpaque(false);
		getContentPane().add(pnlTurnInfo, BorderLayout.NORTH);
		
		lblTurnInfo = new JLabel("<turnInfo>");
		lblTurnInfo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTurnInfo.setFont(new Font("Arial", Font.PLAIN, 16));
		pnlTurnInfo.add(lblTurnInfo);
		
		JPanel pnlTable = new JPanel();
		pnlTable.setOpaque(false);
		getContentPane().add(pnlTable, BorderLayout.WEST);
		GridBagLayout gbl_pnlTable = new GridBagLayout();
		gbl_pnlTable.columnWeights = new double[]{0.0};
		gbl_pnlTable.rowWeights = new double[]{0.0, 0.0, 0.0};
		pnlTable.setLayout(gbl_pnlTable);
		
		cardView = new CardView();
		GridBagConstraints gbc_cardView = new GridBagConstraints();
		gbc_cardView.fill = GridBagConstraints.BOTH;
		gbc_cardView.anchor = GridBagConstraints.NORTHWEST;
		gbc_cardView.insets = new Insets(0, 0, 0, 5);
		gbc_cardView.gridx = 0;
		gbc_cardView.gridy = 0;
		pnlTable.add(cardView, gbc_cardView);
		
		deckView = new DeckView();
		GridBagConstraints gbc_deckView = new GridBagConstraints();
		gbc_deckView.fill = GridBagConstraints.BOTH;
		gbc_deckView.anchor = GridBagConstraints.NORTHWEST;
		gbc_deckView.insets = new Insets(0, 0, 0, 5);
		gbc_deckView.gridx = 0;
		gbc_deckView.gridy = 1;
		pnlTable.add(deckView, gbc_deckView);
		
		JPanel pnlTableFill = new JPanel();
		pnlTableFill.setOpaque(false);
		GridBagConstraints gbc_pnlTableFill = new GridBagConstraints();
		gbc_pnlTableFill.fill = GridBagConstraints.BOTH;
		gbc_pnlTableFill.weighty = 1.0;
		gbc_pnlTableFill.anchor = GridBagConstraints.NORTHWEST;
		gbc_pnlTableFill.gridx = 0;
		gbc_pnlTableFill.gridy = 2;
		pnlTable.add(pnlTableFill, gbc_pnlTableFill);
		
		pnlPlayers = new JPanel();
		pnlPlayers.setOpaque(false);
		getContentPane().add(pnlPlayers, BorderLayout.CENTER);
	}
	
	public void install(Game game) {
		players = new HashMap<>();
		pnlPlayers.setLayout(new GridLayout(game.getPlayerList().size(), 1, 0, 5));
		
		for(Player player: game.getPlayerList()) {
			PlayerView view = new PlayerView();
			view.setPlayer(player);
			if (player.isComputer() == false) {
				view.setListener(game);
			}
			players.put(player, view);
			pnlPlayers.add(view);
		}
		deckView.setListener(game);
		
		timer = new Timer(1000, e -> game.nextTurn());
		timer.setRepeats(false);
		
	}

	public void report(String message) {
		JOptionPane.showMessageDialog(this, message);
	}
	
	/**
	 * Über den Timer der GameView wird ein neuer Spielzug angefordert.
	 * Der Timer ruft dafür nicht sofort Game::nextTurn, sondern wartet
	 * sein eingestelltes "delay" (1000ms == 1s) ab. Dadurch wird das 
	 * Spielgeschehen, insbesondere Züge von Computer-Spielern nachvoll-
	 * ziehbar.
	 */
	public void requestNextTurn() {
		timer.restart();
	}
	
	public void update(Game game) {
		lblTurnInfo.setText(game.getTurnInfo());
		cardView.setCard(game.getTopCard());
		deckView.setPlayer(game.getPlayer());
		
		for(Player player: game.getPlayerList()) {
			players.get(player).repaint();
		}
	}
	
}
