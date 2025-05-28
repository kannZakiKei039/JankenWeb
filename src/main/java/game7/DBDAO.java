package game7;//対戦履歴をDBに保存

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import jakarta.servlet.ServletException;

public class DBDAO {
	public void insertMatch(int playerHand,int computerHand) throws ServletException {
		
		String sql = "insert into match_history (play_date,play_hand,computer_hand) values(?,?,?)";

		try (
				Connection con = DBManager.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)){

			ps.setTimestamp(1, Timestamp.valueOf(LocalDateTime.now()));
			ps.setInt(2,playerHand);
			ps.setInt(3,computerHand);

			ps.executeUpdate();
			System.out.println("対戦履歴をDBに保存しました");

		}catch (SQLException  e) {
			e.printStackTrace();
			throw new ServletException("DB登録中にエラー発生",e);

		}
	}


}
