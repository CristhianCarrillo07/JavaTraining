package catMouseGame;

public class Cat {
	private Mouse mouse = new Mouse();

	public boolean catchMouse(int x, int y) {
		int[][] hiddenMouse = mouse.hiddenMouse();
		if (hiddenMouse[x][y] == 1) {
			return true;
		} else {
			return false;
		}
	}

	public String getCoordinates() {
		int[][] hiddenMouse = mouse.hiddenMouse();
		int i = 0;
		int j = 0;
		OUTER:
		for (i = 0; i < 5; i++) {
			for (j = 0; j < 5; j++) {
				if (hiddenMouse[i][j] == 1) {
					break OUTER;
				}
			}
		}
		return i + "," + j;
	}

}