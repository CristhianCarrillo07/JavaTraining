package coinTossGame;

import java.util.InputMismatchException;
import java.util.Scanner;

public class TossCoinGame {
	private Coin coinClass = new Coin();
	private int option;
	private Scanner sc;

	public TossCoinGame() {
	}

	public void start() {
		showInitMessage();
		enterOption();
	}

	private void showInitMessage() {
		System.out.println("Welcome to Toss Coin Game!, which consists in toss a coin 10 times and "
				+ "look how many times each side of the coin (head/tail) is obtained");
		System.out.println("Do you want to play? Yes(1) | No(2)");
	}

	private void enterOption() {
		try {
			System.out.println("Input: ");
			sc = new Scanner(System.in);
			option = sc.nextInt();
			selectOption();
		} catch (InputMismatchException ex) {
			System.out.println("Invalid option. Choose a valid option");
			enterOption();
		} 
	}

	private void selectOption() {
		switch (option) {
		case 1:
			runGame();
			System.out.println("Do you want to play again? Yes(1) | No(2)");
			enterOption();
			break;
		case 2:
			System.out.println("Bye bye!");
			System.exit(0);
			break;
		default:
			System.out.println("Invalid option. Choose a valid option");
			enterOption();
		}
	}
	
	private void runGame(){
		coinClass.tossCoin();
		System.out.println("# toss: " + coinClass.getTotalTossDone());
		System.out.println("# heads: " + coinClass.getTotalHeads());
		System.out.println("# tails: " + coinClass.getTotalTails());
	}

	public static void main(String[] args) {
		TossCoinGame main = new TossCoinGame();
		main.start();
	}
}
