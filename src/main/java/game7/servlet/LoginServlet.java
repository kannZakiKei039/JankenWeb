package game7.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import game7.model.LoginLogic;
import game7.model.Mutter;
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
		//ユーザーをアプリケーションスコープから取得
		ServletContext application = this.getServletContext();
		List<Mutter> mutterList = (List<Mutter>)application.getAttribute("mutterList");
		
		//取得できなかった場合はユーザーを新規作成してアプリケーションスコープに保存
		if(mutterList == null) {
			mutterList = new ArrayList<Mutter>();
			application.setAttribute("mutterList", mutterList);
		}
		//ログインしているか確認するため、セッションスコープからユーザー情報を取得
		HttpSession session = request.getSession();
		User loginUser = (User)session.getAttribute("user");
		
		if(loginUser == null ) {
			//ログインしてない場合
			//リダイレクト
			RequestDispatcher dispatcher =request.getRequestDispatcher("WEB-INF/view/login.jsp");
			dispatcher.forward(request, response);
		}else {
			//ログイン済
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/view/menu.jsp");
			dispatcher.forward(request, response);
		}
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
			user.setId(account.getId());
			
			HttpSession session = request.getSession();
			session.setAttribute("user", user);

			response.sendRedirect("LoginServlet");
		} else {
			response.sendRedirect("login.jsp?error=1");
		}
	}
}


		