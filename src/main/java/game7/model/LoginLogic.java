package game7.model;

import game7.servlet.PasswordUtil;

public class LoginLogic {
	public UserAccount login(String loginId,String password) {
		AccountDAO dao = new AccountDAO();
		UserAccount useraccount = dao.findLogin(loginId);
		
		if(useraccount != null && PasswordUtil.checkPassword(password, useraccount.getPassword())) {
		return useraccount;
	}
	return null;

}
}