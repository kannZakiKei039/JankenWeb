package game7.servlet;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import game7.dao.UserDAO;


@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public RegisterServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
	RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/register.jsp");
	dispatcher.forward(request, response);
}


	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		String username = request.getParameter("username");
		String loginId = request.getParameter("login_id");
		String password = request.getParameter("password");

		if (username == null || username.isEmpty() ||
				loginId == null || loginId.isEmpty() ||
				password == null || password.isEmpty()) {
			response.sendRedirect("WEB-INF/view/register_input_error.jsp");
			return;
		}
		UserDAO userDAO = new UserDAO();
		
		try {
		//重複チェック
		if(userDAO.checkUser(loginId)) {
			//既に重複してたら重複画面へ
			response.sendRedirect("WEB-INF/view/register_dupkicate.jsp");
			return;
		}
	}catch(Exception e) {
		e.printStackTrace();//ログに出す
		response.sendRedirect("WEB-INF/view/register_error.jsp");
		return;
	}
		
		//パスワードをハッシュ化
		String hashedPassword = PasswordUtil.hashPassword(password);

		//登録処理
		boolean inserted = userDAO.insertUser(username, loginId, hashedPassword);
		if(inserted) {
			response.sendRedirect("WEB-INF/register_success.jsp");

		}else{
			response.sendRedirect("WEB-INF/register_error.jsp");
		}
		
		}
	}

