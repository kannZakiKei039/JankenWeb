package game7.service;

public class WebGameService {
	public static final String[] handNames= {"グー","チョキ","パー"};

	private junkenRule rule = new junkenRule();
	private Strategy computerStrategy = new ComputerStrategyRandom();
	private int lastComputerHand;
	private int lastJudge;

	public void playGame(int playerHand,String playerName) {
		lastComputerHand = computerStrategy.nexthand(-1);
		lastJudge = rule.Judge(playerHand, lastComputerHand);


	}
	public int getJudge() {
		return lastJudge;
	}
	public int getComputerHand() {
		return lastComputerHand;
	}
	public String getResultMessage(int playerHand,String playerName) {
		String resultMessage = rule.showJudge(lastJudge,playerName);
		return String.format("あなたの手：%s / コンピューターの手： %s<br>%s,",
				rule.getHandStr(playerHand),
				rule.getHandStr(lastComputerHand),
				resultMessage
				);
	}

}
