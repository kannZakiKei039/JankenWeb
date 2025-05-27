package game7;

public class junkenRule implements HandSkinnable{
	
	 static String[] handStr= handStrs[HAND_JAPAN];
	
	
	static void changeCountry(int country) {
		 handStr=handStrs[country];
	}

	 public int Judge(int player1,int player2) {
		 int judge=(player1 - player2 + 3) % 3; /* 勝敗を判定 */
		 
		 if(judge==0) {
			 return 0;
			 
		 }else if(judge==1) {
			 
			 return 1;
			 
		 }else {
		return 2;
		 }
	 }
	 
	 public String showJudge(int judge,String firstPlayerName) {
		 	String[] judgeStr = {"引き分けです", "負けです", "勝ちです"};
		 	String resultMessege;
		 	if(judge ==0) {
		 		resultMessege = judgeStr[judge];
		 	}else {
		 		
			   resultMessege = firstPlayerName+"の"+judgeStr[judge];
		 	}
		 	System.out.println(resultMessege);//コンソール画面にも出す
		 	return resultMessege;

		 
	 }
	 public String getHandStr(int hand) {
		    return handStr[hand];
		}
}
