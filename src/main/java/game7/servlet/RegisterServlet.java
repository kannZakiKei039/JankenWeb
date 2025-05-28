package game7.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public RegisterServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
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
			response.sendRedirect("register_input_error.jsp");
			return;
		}
		
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		//パスワードをハッシュ化
		String hashedPassword = PasswordUtil.hashPassword(password);
		try (Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/junken", "postgres",
				"postgres")) {

			//重複チェック
			String checkSql = "select count(*) from users where login_id = ?";
			try (PreparedStatement checkStmt = con.prepareStatement(checkSql)) {
				checkStmt.setString(1, loginId);
				ResultSet rs = checkStmt.executeQuery();
				if (rs.next() && rs.getInt(1) > 0) {
					response.sendRedirect("register_duplicate.jsp");
					return;
				}
			}

			//登録処理
			String sql = "insert into users (username,login_id,password) values(?,?,?)";
			try (PreparedStatement stmt = con.prepareStatement(sql)) {
				stmt.setString(1, username);
				stmt.setString(2, loginId);
				stmt.setString(3, hashedPassword);
				stmt.executeUpdate();
			}
			response.sendRedirect("register_success.jsp");

		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect("register_error.jsp");
		}
	}
}
