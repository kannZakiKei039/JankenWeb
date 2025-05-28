package game7.servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import game7.model.LoginLogic;
import game7.model.User;
import game7.model.UserAccount;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//文字コード設定
		request.setCharacterEncoding("UTF-8");		

		//リクエストパラメータの取得
		String loginId = request.getParameter("login_id");
		String password = request.getParameter("password");

		//入力チェック
		if (loginId == null || password == null || loginId.isEmpty() || password.isEmpty()) {
			response.sendRedirect("login.jsp?error=1");
			return;
		}
		
		//ログインロジック呼び出し
		LoginLogic logic = new LoginLogic();
		UserAccount account = logic.login(loginId,password);
		
		if (account != null) {
			// セッション用Userモデルに変換（パスワード非保持）
			User user = new User();
			user.setName(account.getUsername());
			user.setLoginId(account.getLoginId());

			HttpSession session = request.getSession();
			session.setAttribute("user", user);

			response.sendRedirect("index.jsp");
		} else {
			response.sendRedirect("login.jsp?error=1");
		}
	}
}


		