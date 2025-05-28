package game7.model;//DBのusersテーブルのレコード

import java.time.LocalDateTime;

public class UserAccount {
	private int id;
	private String username;
	private String loginId;
	private String password;
	private LocalDateTime createdAt;
	
	public UserAccount() {};

	public UserAccount(int id, String username, String loginId, String password, LocalDateTime createdAt) {
		this.id = id;
		this.username = username;
		this.loginId = loginId;
		this.password = password;
		this.createdAt = createdAt;
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

}
	
	
	