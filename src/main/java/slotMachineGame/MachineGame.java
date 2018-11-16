package slotMachineGame;

import java.io.IOException;
import java.util.*;

public class MachineGame {
	private Scanner sc;
	private CreditSystem creditSystem = new CreditSystem();
	private RandomSystem randomSystem = new RandomSystem();
	private int option;
	private int betCredits;
	private Properties prop = new Properties();

	{
		try {
			ClassLoader loader = Thread.currentThread().getContextClassLoader();
			prop.load(loader.getResourceAsStream("messages.properties"));
		} catch (IOException ex) {
			System.out.println("The messages properties file wasn't found");
		}
	}

	public void start() {
		showInitMessage();
		enterOption();
	}

	private void showInitMessage() {
		System.out.println(prop.getProperty("welcome"));
		System.out.println(prop.getProperty("question.options"));
		System.out.println(prop.getProperty("options"));
	}

	private void enterOption() {
		try {
			System.out.println(prop.getProperty("input"));
			sc = new Scanner(System.in);
			option = sc.nextInt();
			selectOption();
		} catch (InputMismatchException ex) {
			System.out.println(prop.getProperty("invalid.option"));
			enterOption();
		}
	}

	private void selectOption() {
		switch (option) {
		case 1:
			if (creditSystem.getCredits() == 0) {
				System.out.println("You need to add credits");
				addCredits();
				System.out.println(prop.getProperty("balance") + creditSystem.getCredits());
			} else {
				System.out.println("How many credits do you want to bet?");
				System.out.println(prop.getProperty("balance") + creditSystem.getCredits());
				betCredits();
				if (creditSystem.getCredits() < betCredits) {
					System.out
							.println("You have not enough credits, please add credits if you want to bet this amount");
					System.out.println(prop.getProperty("balance") + creditSystem.getCredits());
					System.out.println(prop.getProperty("question.options"));
					System.out.println(prop.get("options"));
					enterOption();
				} else {
					creditSystem.withdrawCredits(betCredits);
					System.out.println(prop.getProperty("balance") + creditSystem.getCredits());
					runGame();

				}
			}
			System.out.println(prop.getProperty("question.options"));
			System.out.println(prop.get("options"));
			enterOption();
			break;
		case 2:
			System.out.println("How many credits do you want to add?");
			addCredits();
			System.out.println(prop.getProperty("balance") + creditSystem.getCredits());
			System.out.println(prop.getProperty("question.options"));
			System.out.println(prop.get("options"));
			enterOption();
			break;
		case 3:
			System.out.println("Bye bye!");
			System.exit(0);
			break;
		default:
			System.out.println(prop.getProperty("invalid.option"));
			enterOption();
		}
	}

	private void betCredits() {
		try {
			System.out.println(prop.getProperty("input"));
			sc = new Scanner(System.in);
			betCredits = sc.nextInt();
			if (betCredits <= 0) {
				System.out.println(prop.getProperty("invalid.amount"));
				betCredits();
			}
		} catch (InputMismatchException ex) {
			System.out.println(prop.getProperty("invalid.amount"));
			betCredits();
		}
	}

	private void addCredits() {
		try {
			System.out.println(prop.getProperty("input"));
			sc = new Scanner(System.in);
			int betCredits = sc.nextInt();
			if (betCredits <= 0) {
				System.out.println(prop.getProperty("invalid.amount"));
				addCredits();
			} else {
				creditSystem.addCredits(betCredits);
			}
		} catch (InputMismatchException ex) {
			System.out.println(prop.getProperty("invalid.amount"));
			addCredits();
		}
	}

	private void runGame() {
		for (int i = 0; i < 10; i++) {
			System.out.print("-");
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("\n" + randomSystem.getRandomCombination());
		if (randomSystem.checkEquality()) {
			System.out.println("Congratulations!!!");
			System.out.println("You win " + randomSystem.getReward(betCredits) + " credits");
			creditSystem.addCredits(randomSystem.getReward(betCredits));
			System.out.println(prop.getProperty("balance") + creditSystem.getCredits());
		} else {
			System.out.println("You lost. Luck for the next time!");
			System.out.println(prop.getProperty("balance") + creditSystem.getCredits());
		}
	}

	public static void main(String[] args) {
		MachineGame mg = new MachineGame();
		mg.start();
	}
}