package game7.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import game7.DBManager;
import game7.model.User;

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


		//DB接続＆認証処理
		try(Connection conn = DBManager.getConnection()){
			String sql="select * from users where login_id = ? ";
			try(PreparedStatement stmt =conn.prepareStatement(sql)){
				stmt.setString(1, loginId);
				try(ResultSet rs =stmt.executeQuery()){
					if(rs.next()) {
						String storedHash =rs.getString("password");//DBから取得したハッシュ値
						if( PasswordUtil.checkPassword(password, storedHash)){
							//DBから取得した情報もセット
							User user = new User();
							user.setName(rs.getString("username"));//ユーザー名
							user.setPass(null);//セキュリティ上、パスワードは保持しない

							//ログイン成功
							HttpSession session = request.getSession();
							session.setAttribute("user", user);
							response.sendRedirect("index.jsp");
						}else {
							//パスワード不一致
							response.sendRedirect("login.jsp?error=1");

						}
					}else {
						//該当ユーザーなし
						response.sendRedirect("login.jsp?error=1");
					}

				}
			}
		}catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect("login.jsp?error=1");
		}

	}
}
