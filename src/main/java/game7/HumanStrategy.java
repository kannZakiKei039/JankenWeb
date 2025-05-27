package game7;

import java.util.Scanner;

public class HumanStrategy implements Strategy {

	@Override
	public int nexthand(int hand) {
		Scanner scan = new Scanner(System.in);
		hand=scan.nextInt();
		return hand;
	}

}
