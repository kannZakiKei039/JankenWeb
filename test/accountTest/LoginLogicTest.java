package accountTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import game7.model.LoginLogic;
import game7.model.UserAccount;

class LoginLogicTest {

	@Test
    public void testExecute_success() {
        LoginLogic logic = new LoginLogic();

        // この login_id は DB に存在している
        String loginId = "1";
        String password ="0831";

        UserAccount result = logic.login(loginId,password);
        assertNotNull(result, "ログイン成功と判定されるべき");
    }

    @Test
    public void testExecute_failure() {
        LoginLogic logic = new LoginLogic();

        // 存在しない login_id
        String loginId = "not_exist_user";
        String password="0000";

        UserAccount result = logic.login(loginId,password);
        assertNotNull(result, "ログイン失敗と判定されるべき");
	}

}
