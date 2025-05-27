package game7;

public  class Player {
	int hand;
	String name;
	Strategy strategy=null;
	
	public Player(String name,Strategy strategy) {
		this.name=name;
		this.strategy=strategy;
			
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public  void nextHand() {
		int hand = strategy.nexthand(this.hand);//次の手を考える
		setHand(hand);
	}

	public void setHand(int hand) {
		this.hand = hand;
	}

	public int getHand() {
		return hand;
	}
	public void showHand() {
		
	}
	public String toString() {
		return " ";
	}
	
	
}
