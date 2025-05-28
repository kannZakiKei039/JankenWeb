package game7.model;

import java.io.Serializable;

public class Mutter implements Serializable {
	private String userName;//ユーザー名
	
	public Mutter() {}

	public Mutter(String userName) {
		super();
		this.userName = userName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	};
	
	
}
