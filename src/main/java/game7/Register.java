package game7;

public class Register {
	int[] result= {0,0,0};

	public Register() {
		
	}
	public void recordJudge(int judeg) {
		result[judeg]++;
	}
	
	public int[] getResult() {
	    return result;
	}
	

}
