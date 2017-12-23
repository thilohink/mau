package mau.gui;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

import mau.Player;
import java.awt.Dimension;
import java.awt.BorderLayout;

public class DeckViewTest extends JFrame implements DrawListener {

	private static final long serialVersionUID = 1L;
	private DeckView deckView;

	public static void main(String[] args) throws Exception {
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		
		Player tester = new Player("Tester", false);
		DeckViewTest test = new DeckViewTest();
		
		test.deckView.setPlayer(tester);
		test.deckView.setListener(test);
		
		test.setVisible(true);
	}
	
	public DeckViewTest() {
		setSize(new Dimension(300, 300));
		setTitle("DeckView - Test");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		deckView = new DeckView();
		getContentPane().add(deckView, BorderLayout.CENTER);
		
	}
	
	@Override
	public void handleDraw(Player performer) {
		JOptionPane.showMessageDialog(this, performer + " zieht eine Karte.");
	}
	
}
