package game7.model;//ユーザー情報に関する情報を持つ

import java.io.Serializable;

public class User implements Serializable {
	private String name;//ユーザー名
	private String pass;//パスワード
	private String loginId;//ユーザーID
	private int id;//一意のID
	
	public User() {}

	public User(String name, String pass,String loginId,int id) {
		super();
		this.name = name;
		this.pass = pass;
		this.loginId= loginId;
		this.id=id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name=name;

	}

	public String getPass() {
		return pass;
	}
	
	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	

}
