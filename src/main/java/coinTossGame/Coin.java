package coinTossGame;

import java.util.Random;

public class Coin {
	private int totalTossDone;
	private int totalHeads;
	private int totalTails;
	
	public Coin() {
		totalTossDone = 0;
		totalHeads = 0;
		totalTails = 0;
	}
	
	public void tossCoin() {
		totalTossDone = 0;
		totalHeads = 0;
		totalTails = 0;
		for (int i = 0; i < 10; i++) {
			Random r = new Random();
			int num =r.nextInt(2);
			// num<0.5 = Tail
			// num>=0.5 = Head
			if (num < 1) {
				totalTails++;
			} else {
				totalHeads++;
			}
			totalTossDone++;
		}
	}

	public int getTotalTossDone() {
		return totalTossDone;
	}

	public int getTotalHeads() {
		return totalHeads;
	}

	public int getTotalTails() {
		return totalTails;
	}
}