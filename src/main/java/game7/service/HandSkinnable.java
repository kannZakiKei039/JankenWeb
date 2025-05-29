package game7.service;

public interface HandSkinnable {
	//じゃんけんの手文字列配列（国別）
	String[][] handStrs = {
			{"グー","チョキ","パー"},
			{"石","はさみ","布"},
			{"rock","scissors","paper"}
	};
	//国を表す整数
	int HAND_JAPAN=0;
	int HAND_CHINA=1;
	int HAND_AMERICA=2;

}
