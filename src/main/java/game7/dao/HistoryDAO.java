package game7.dao;//対戦履歴をDBに保存

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import jakarta.servlet.ServletException;

public class HistoryDAO {
	public void insertMatch(int id,int playerHand,int computerHand,String result) throws ServletException {
		
		String sql = "insert into match_history (user_id,player_hand,cpu_hand,result,play_date) values(?,?,?,?,?)";

		try (
				Connection con = DBManager.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)){
			
			ps.setInt(1,id);
			ps.setInt(2,playerHand);
			ps.setInt(3,computerHand);
			ps.setString(4, result);
			ps.setTimestamp(5, Timestamp.valueOf(LocalDateTime.now()));

			ps.executeUpdate();
			System.out.println("対戦履歴をDBに保存しました");

		}catch (SQLException  e) {
			e.printStackTrace();
			throw new ServletException("DB登録中にエラー発生",e);

		}
	}


}
