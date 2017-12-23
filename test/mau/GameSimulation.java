package mau;

public class GameSimulation extends Game {
	
	public static void main(String[] args) {
		
		Game game = new GameSimulation();
		
		game.addComputer("Anton");
		game.addComputer("Berta");
		game.addComputer("Chris");
		
		game.prepare();
		
		System.out.println(game.getTurnInfo());
		System.out.println("Es liegt " + game.getTopCard());
		System.out.println();
		
		game.start();
		
		System.out.println();
		System.out.println("Spielende! " + game.getPlayer() + " hat gewonnen!");
		
	}

	@Override
	public void start() {
		// In der Simulation laufen die Runden in einer Schleife,
		// was in der GUI-Version so nicht funktioniert ...
		// super.start();
		
		do {
			
			nextTurn();
			
			System.out.println(getTurnInfo());
			
		} while (getPlayer().hasWon() == false);
		
	}
	
	@Override
	public void update() {
		// Muss für die Simulation deaktiviert (= leer überschrieben) werden,
		// da hier auf die (in der Simulation) nicht vorhandene View zugegriffen
		// wird.
		// super.update();
	}
	
	@Override
	public void updateAndProceed() {
		// Muss für die Simulation deaktiviert (= leer überschrieben) werden,
		// da hier auf die (in der Simulation) nicht vorhandene View zugegriffen
		// wird.
		// super.updateAndProceed();
	}
	
}

