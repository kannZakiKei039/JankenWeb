package accountTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import game7.model.AccountDAO;
import game7.model.UserAccount;

class AccountDAOTest {
	
	 @Test
	    public void testFindLogin() {
	        // 実際に存在する login_id を指定すること！
	        String testLoginId = "1"; // DBにあるログインID

	        AccountDAO dao = new AccountDAO();
	        UserAccount user = dao.findLogin(testLoginId);

	        assertNotNull(user, "ユーザーが見つかるべき");
	        System.out.println("取得結果:");
	        System.out.println("ユーザー名: " + user.getUsername());
	        System.out.println("ログインID: " + user.getLoginId());
	        System.out.println("作成日時: " + user.getCreatedAt());
	    }
	
}

	
	 