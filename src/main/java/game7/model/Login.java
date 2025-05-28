package game7.model;//ログイン情報を持つ

public class Login {
	private String loginId;
	private String pass;
	
	public Login(String loginId,String pass) {
		this.loginId=loginId;
		this.pass=pass;
	}
	public String getLoginId() {return loginId;}
	public String getPass() {return pass;}

}
