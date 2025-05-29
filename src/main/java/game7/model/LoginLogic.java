package game7.model;

import game7.dao.UserDAO;
import game7.servlet.PasswordUtil;

public class LoginLogic {
	public UserAccount login(String loginId,String password) {
		UserDAO dao = new UserDAO();
		UserAccount useraccount = dao.findLogin(loginId);
		
		if(useraccount != null && PasswordUtil.checkPassword(password, useraccount.getPassword())) {
		return useraccount;
	}
	return null;

}
}