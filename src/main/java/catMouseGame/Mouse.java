package catMouseGame;

import java.util.Random;

public class Mouse {
	private int[][] mouseCoordinates = new int[5][5];
	
	public Mouse() {
		Random r = new Random();
		int num1 = r.nextInt(5);
		int num2 = r.nextInt(5);
		mouseCoordinates[num1][num2] = 1;
	}
	
	public int[][] hiddenMouse(){
		return mouseCoordinates;
	}
}