package slotMachineGame;

public class CreditSystem {
	
	private int creditAmount;
	
	public CreditSystem() {
		this.creditAmount = 0;
	}
	
	public void addCredits(int moreCredits) {
		creditAmount += moreCredits;
	}
	
	public void withdrawCredits(int lessCredits) {
		creditAmount -= lessCredits;
	}
	
	public int getCredits() {
		return creditAmount; 
	}
}