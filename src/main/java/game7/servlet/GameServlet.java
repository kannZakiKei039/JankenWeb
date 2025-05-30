package game7.servlet;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import game7.dao.HistoryDAO;
import game7.model.User;
import game7.service.Register;
import game7.service.WebGameService;
import game7.service.junkenRule;


@WebServlet("/GameServlet")
public class GameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Register register = new Register();//履歴管理用


	public GameServlet() {
		super();

	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//フォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/view/game.jsp");
		dispatcher.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
		User loginUser = (User)session.getAttribute("user");
		 if (loginUser == null) {
		        response.sendRedirect("WEB-INF/view/login.jsp");
		        return;
		    }
		
		int id = loginUser.getId();
		String handStr = request.getParameter("hand");
		int playerHand = Integer.parseInt(handStr);

		String playerName = loginUser.getName();

		//ゲーム実行
		WebGameService service = new WebGameService();
		service.playGame(playerHand, playerName);//これで判定実行
		
		int judge = service.getJudge();//勝敗取り出す
		int computerHand = service.getComputerHand();
		
		junkenRule rule = new junkenRule();
		String resultStr = rule.getJudgeResultStr(judge);

		//メモリ上の履歴に保存
		register.recordJudge(judge);//ここで履歴登録
		
		HistoryDAO dao = new HistoryDAO();//DB接続呼び出し
		dao.insertMatch(id,playerHand, computerHand,resultStr);

		String resultMessage = service.getResultMessage(playerHand,playerName);

		request.setAttribute("resultMessage", resultMessage);
		request.setAttribute("resultArray", register.getResult());//履歴表示用
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/view/result.jsp");
		dispatcher.forward(request, response);
	}
}
