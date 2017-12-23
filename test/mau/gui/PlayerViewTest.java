package mau.gui;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import mau.Player;
import mau.card.Card;
import mau.card.Deck;
import javax.swing.JPanel;
import javax.swing.UIManager;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

public class PlayerViewTest extends JFrame implements PlayListener {

	private static final long serialVersionUID = 1L;
	private PlayerView playerView;
	private PlayerView computerView;

	public static void main(String[] args) throws Exception {
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		
		Deck deck = new Deck();
		Player player = new Player("Tester", false);
		player.getCards().addAll(deck.deal(5));
		
		Player computer = new Player("Computer");
		computer.getCards().addAll(deck.deal(4));
	
		PlayerViewTest test = new PlayerViewTest();
		test.playerView.setPlayer(player);
		test.playerView.setListener(test);
		
		test.computerView.setPlayer(computer);
		
		test.setVisible(true);
	}

	public PlayerViewTest() {
		setSize(new Dimension(600, 400));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("PlayerView - Test");
		
		JPanel pnlViews = new JPanel();
		getContentPane().add(pnlViews, BorderLayout.CENTER);
		pnlViews.setLayout(new GridLayout(2, 1, 0, 5));
		
		playerView = new PlayerView();
		pnlViews.add(playerView);
		
		computerView = new PlayerView();
		pnlViews.add(computerView);
	}
	
	@Override
	public void handlePlay(Player performer, Card card) {
		JOptionPane.showMessageDialog(this, performer + " spielt " + card);
	}
	
}
