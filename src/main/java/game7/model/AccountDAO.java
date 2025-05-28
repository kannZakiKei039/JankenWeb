package game7.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

import game7.DBManager;

public class AccountDAO {
	public UserAccount findLogin(String loginId) {
		UserAccount useraccount = null;
	
	try(Connection con = DBManager.getConnection()){
		String sql="select * from users where login_id = ?  ";
		try(PreparedStatement stmt = con.prepareStatement(sql))	{
			stmt.setString(1, loginId);
			
			
		//select文を実行、結果を取得
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next()) {
				//ユーザーが存在したらデータ取得
				//そのユーザーを表すUserAccountインスタンス生成
				int id = rs.getInt("id");
				String username = rs.getString("username");
				String login_id = rs.getString("login_id");
				String password = rs.getString("password");
				LocalDateTime createdAt = rs.getTimestamp("created_at").toLocalDateTime();
		useraccount = new UserAccount(id,username,login_id,password,createdAt);
			}
		}
		}catch(SQLException e) {
			e.printStackTrace();
			
		}
			return useraccount;
	}
	}

