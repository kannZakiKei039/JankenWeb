package game7;

public class ComputerStrategySerial implements Strategy {
	int hand;

	@Override
	public int nexthand(int hand) {
		hand=(hand+1)%3;
		return hand;
	}

}
