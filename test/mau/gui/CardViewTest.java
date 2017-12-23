package mau.gui;

import javax.swing.JFrame;
import javax.swing.UIManager;

import mau.card.Deck;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.FlowLayout;

public class CardViewTest extends JFrame {

	private static final long serialVersionUID = 1L;
	
	public static void main(String[] args) throws Exception {
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		
		new CardViewTest().setVisible(true);
	}

	private CardView cardView;
	
	public CardViewTest() {
		setSize(new Dimension(300, 300));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("CardView - Test");
		
		cardView = new CardView();
		getContentPane().add(cardView, BorderLayout.CENTER);
		
		JPanel pnlButton = new JPanel();
		FlowLayout flowLayout = (FlowLayout) pnlButton.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		getContentPane().add(pnlButton, BorderLayout.SOUTH);
		
		JButton btnDeal = new JButton("Deal!");
		btnDeal.addActionListener(e -> deal());
		pnlButton.add(btnDeal);
	}

	public void deal() {
		Deck deck = new Deck();
		cardView.setCard(deck.deal());
	}
	
}
