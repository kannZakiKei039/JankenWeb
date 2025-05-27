package game7.servlet;

import java.security.MessageDigest;

public class PasswordUtil {

    // パスワードを SHA-256 でハッシュ化する
    public static String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = md.digest(password.getBytes());

            StringBuilder sb = new StringBuilder();
            for (byte b : hashBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();

        } catch (Exception e) {
            throw new RuntimeException("パスワードのハッシュ化に失敗しました", e);
        }
    }
    //パスワード照合
	public static boolean checkPassword(String plainPassword,String hashedPassword) {
		String hashOfInput =hashPassword(plainPassword);
		return hashOfInput.equals(hashedPassword);
	}
}

