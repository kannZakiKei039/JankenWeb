package game7.service;

import java.util.Random;

public class ComputerStrategyRandom implements Strategy {

	@Override
	public int nexthand(int hand) {
		Random rnd= new Random();
		hand=rnd.nextInt(3);
		return hand;
	}

}
