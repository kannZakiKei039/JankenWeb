package game7.servlet;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import game7.Register;
import game7.dao.DBDAO;
import game7.service.WebGameService;


@WebServlet("/GameServlet")
public class GameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Register register = new Register();//履歴管理用


	public GameServlet() {
		super();

	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//フォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("game.jsp");
		dispatcher.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String handStr = request.getParameter("hand");
		int playerHand = Integer.parseInt(handStr);

		String playerName = "あなた";

		WebGameService service = new WebGameService();

		service.playGame(playerHand, playerName);//これで判定実行
		int judge = service.getJudge();//勝敗取り出す
		int computerHand = service.getComputerHand();

		register.recordJudge(judge);//ここで履歴登録
		DBDAO dao = new DBDAO();//DB接続呼び出し
		dao.insertMatch(playerHand, computerHand);

		String resultMessage = service.getResultMessage(playerHand,playerName);

		request.setAttribute("resultMessage", resultMessage);
		request.setAttribute("resultArray", register.getResult());//履歴表示用
		RequestDispatcher dispatcher = request.getRequestDispatcher("result.jsp");
		dispatcher.forward(request, response);
	}
}
