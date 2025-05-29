package game7.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

import game7.DBManager;
import game7.model.UserAccount;

public class UserDAO {

	//【ログイン処理】
	public UserAccount findLogin(String loginId) {
		UserAccount useraccount = null;
	
	try(Connection con = DBManager.getConnection()){
		//ユーザー情報を取得する
	
		String sql="select * from users where login_id = ?  ";
		try(PreparedStatement stmt = con.prepareStatement(sql))	{
			//SQLの？に
			stmt.setString(1, loginId);
			
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next()) {
				//ユーザーが存在したらデータ取得
				int id = rs.getInt("id");
				String username = rs.getString("username");
				String login_id = rs.getString("login_id");
				String password = rs.getString("password");
				LocalDateTime createdAt = rs.getTimestamp("created_at").toLocalDateTime();
		
				//取得した値でUserAccountインスタンス生成
				useraccount = new UserAccount(id,username,login_id,password,createdAt);
			}
		 }
		}catch(SQLException e) {
			e.printStackTrace();
			
		}
			//結果を返す
			return useraccount;
	}
	
	//ログインIDの重複チェック
	public boolean checkUser(String loginId) throws Exception {
        String checkSql = "SELECT COUNT(*) FROM users WHERE login_id = ?";

        try (Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/junken", "postgres", "postgres");
        	 PreparedStatement checkStmt = con.prepareStatement(checkSql)) {
        		 //？にloginIdをセット
        		 checkStmt.setString(1, loginId);
        		 //件数取得
                ResultSet rs = checkStmt.executeQuery();
               
                if (rs.next()) {
                	//1件以上あればtrue(重複あり）
                    return rs.getInt(1)>0; 
                }
            }catch(SQLException e) {
            	e.printStackTrace();
            }
            //重複してなければfalse
            return false;
        }
        
        	//登録処理
            public boolean insertUser(String username,String loginId,String hashedPassword) {
            	String sql = "INSERT INTO users (username, login_id, password) VALUES (?, ?, ?)";
            try (Connection con = DBManager.getConnection();
            	 PreparedStatement stmt = con.prepareStatement(sql)){
            	
                stmt.setString(1, username);
                stmt.setString(2, loginId);
                stmt.setString(3, hashedPassword);
                
                //1件以上登録されたらture
                int result = stmt.executeUpdate();
                return result >0;
          
            	}catch(SQLException e) {
            		e.printStackTrace();
            	}
            //登録失敗したらfalse
            	return false;
            }
	}

