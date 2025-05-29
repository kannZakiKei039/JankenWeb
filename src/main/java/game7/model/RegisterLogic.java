package game7.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import game7.servlet.PasswordUtil;

public class RegisterLogic {
	public boolean registerUser(String username, String loginId, String password) throws Exception {
        String hashedPassword = PasswordUtil.hashPassword(password);

        try (Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/junken", "postgres", "postgres")) {

            // 重複チェック
            String checkSql = "SELECT COUNT(*) FROM users WHERE login_id = ?";
            try (PreparedStatement checkStmt = con.prepareStatement(checkSql)) {
                checkStmt.setString(1, loginId);
                ResultSet rs = checkStmt.executeQuery();
                if (rs.next() && rs.getInt(1) > 0) {
                    return false; // 重複あり
                }
            }

            // 登録処理
            String sql = "INSERT INTO users (username, login_id, password) VALUES (?, ?, ?)";
            try (PreparedStatement stmt = con.prepareStatement(sql)) {
                stmt.setString(1, username);
                stmt.setString(2, loginId);
                stmt.setString(3, hashedPassword);
                stmt.executeUpdate();
            }

            return true; // 登録成功
        }
    }
}
