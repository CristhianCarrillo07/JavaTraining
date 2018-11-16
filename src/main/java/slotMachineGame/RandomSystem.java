package slotMachineGame;

import java.util.*;

public class RandomSystem {
	private List<Character> symbols = new ArrayList<>();
	private List<Character> randomList = new ArrayList<>();
	private Random random = new Random();
	private boolean win;
	private int reward;
	private char symbolObtained;
	
	{
		symbols.add('*'); //x2
		symbols.add('#'); //x3
		//symbols.add('$'); //x4
		//symbols.add('%'); //x5
	}
	
	public List<Character> getRandomCombination(){
		randomList.clear();
		for(int i= 0; i<3; i++ ) {
			int randomOption = random.nextInt(symbols.size());
			randomList.add(symbols.get(randomOption));
		}
		return new ArrayList<>(randomList);
	}
	
	public boolean checkEquality() {
		win = false;
		if (randomList.get(0).equals(randomList.get(1))) {
			if (randomList.get(1).equals(randomList.get(2))) {
				win = true;
				symbolObtained = randomList.get(0);
			} 
		} 
		return win;
	}
	
	public int getReward(int betAmount) {
		reward = betAmount*(symbols.indexOf(symbolObtained)+2);
		return reward;
	}
}