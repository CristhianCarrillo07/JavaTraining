package catMouseGame;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.*;

public class Main {

	private int totalWin;
	private int totalLose;
	private int totalPlays;
	private int tries;
	private Scanner sc;
	private String line;
	private int option;
	private boolean flag = false;
	private Cat cat;
	private Pattern pattern = Pattern.compile("[0-4]{1}[\\,]{1}[0-4]{1}");
	private List<String> coordinates = new ArrayList<>();
	private boolean existsCoordinate = false;

	public void start() {
		showInitMessage();
		enterOption();
	}

	private void showInitMessage() {
		System.out.println("Welcome to Cat Mouse Game!, which consists of a cat "
				+ "trying to catch a mouse. Instructions: "
				+ "\n1. You need to write coordinates in format x,x where x is an int, as 2,2."
				+ "\n2. The range of the coordinates are from [0][0] to [4][4]" + "\n3. You can't repeat coordinates."
				+ "\n4. You have 5 tries to catch the mouse." + "\nGood look!");
		System.out.println("Do you want to play? Yes(1) | No(2)");
	}

	private void enterOption() {
		try {
			System.out.println("Input: ");
			sc = new Scanner(System.in);
			option = sc.nextInt();
			selectOption();
		} catch (InputMismatchException ex) {
			System.out.println("Invalid option. Choose a valid option.");
			enterOption();
		} 
	}

	private void selectOption() {
		switch (option) {
		case 1:
			System.out.println("Enter the coordinates where you think the mouse is.");
			runGame();
			System.out.println("Do you want to play again? Yes(1) | No(2)");
			enterOption();
			break;
		case 2:
			System.out.println("Bye bye!");
			System.exit(0);
			break;
		default:
			System.out.println("Invalid option. Choose a valid option.");
			enterOption();
		}

	}

	private void runGame() {
		cat = new Cat();
		tries = 5;
		while (tries > 0) {
			System.out.println("You have " + tries + " attempt(s) left");
			System.out.println("Input: ");
			// Read coordinates
			sc = new Scanner(System.in);
			line = sc.nextLine();
			// Check if the coordinates format is correct
			if (pattern.matcher(line).matches()) {
				existsCoordinate = false;

				// Check if the coordinates are repeated
				if (!coordinates.isEmpty()) {
					for (String s : coordinates) {
						if (line.equals(s)) {
							existsCoordinate = true;
							break;
						}
					}
				}

				if (existsCoordinate != true) {
					int d1 = Character.getNumericValue(line.charAt(0));
					int d2 = Character.getNumericValue(line.charAt(2));
					// Check is the mouse is in that position
					if (cat.catchMouse(d1, d2) == true) {
						flag = true;
						break;
					} else {
						System.out.println("The mouse is not here, try again!");
					}
					// If the coordinates format is ok, they aren't repeated, and the cat didn't
					// find the mouse, subtract an attempt
					tries--;
				} else {
					System.out.println("You have already used these coordinates. Enter new ones.");
					continue;
				}

			} else {
				System.out.println("Invalid coordinates. Enter valid coordinates.");
				continue;
			}
			// Add the coordinates to the ArrayList
			coordinates.add(line);
		}

		totalPlays++;
		if (flag == true) {
			System.out.println("You find the mouse!");
			totalWin++;
			// Reset flag
			flag = false;

		} else {
			System.out.println("You could not find the mouse. The mouse was at " + cat.getCoordinates()
					+ ". Luck for the next time!");
			totalLose++;
		}

		System.out.println("Games played: " + totalPlays);
		System.out.println("Games won: " + totalWin);
		System.out.println("Games lost: " + totalLose);
		// Clear ArrayList
		coordinates.clear();
	}

	public static void main(String[] args) {
		Main main = new Main();
		main.start();
	}
}