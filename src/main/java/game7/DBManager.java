package game7;//DBと接続

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBManager {
	private static final String URL ="jdbc:postgresql://localhost:5432/junken";
	private static final String USER="postgres";
	private static final String PASSWORD="postgres";
	
	public static Connection getConnection() throws SQLException{
		try {//これを明示的に表示しないと動かなかった…。
	        Class.forName("org.postgresql.Driver");
	    } catch (ClassNotFoundException e) {
	        e.printStackTrace();
	        throw new SQLException("PostgreSQLドライバが見つかりません");
	    }
		return DriverManager.getConnection(URL,USER,PASSWORD);
	}

}
