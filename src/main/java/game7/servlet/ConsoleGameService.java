package game7.servlet;

import game7.Player;
import game7.Register;
import game7.junkenRule;

public class ConsoleGameService {
	private junkenRule rule= new junkenRule();
	private Register register = new Register();
	
	public int playRound(Player player1,Player player2) {
		player1.nextHand();
		player2.nextHand();
		return rule.Judge(player1.getHand(), player2.getHand());
	}

	public String getJudgeMessege(int judge,String playerName) {
		return rule.showJudge(judge, playerName);
	}
}
