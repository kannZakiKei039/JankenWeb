package game7.service;

import game7.service.Player;
import game7.service.Register;
import game7.service.junkenRule;

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
