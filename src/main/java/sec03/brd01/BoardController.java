package sec03.brd01;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class BoardController
 */
//@WebServlet("/board/*")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BoardService service;
	private ArticleVO vo;
	

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		service = new BoardService();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request,response);
	}

	protected void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String nextPage = "";
		String action = request.getPathInfo();
		System.out.println("action="+action);
		try {
			List<ArticleVO> list = new ArrayList<ArticleVO>();
			if(action == null || action.equals("/")) {
				list = service.listArticles();
				System.out.println("list="+list.toString());
				request.setAttribute("articlesList", list);
				nextPage = "/board01/listArticles.jsp";
			}else if(action.equals("/listArticles.do")) {
				list = service.listArticles();
				System.out.println("list="+list.toString());
				request.setAttribute("articlesList", list);
				nextPage = "/board01/listArticles.jsp";
			}else {
				nextPage = "/board01/listArticles.jsp";
			}
			RequestDispatcher disp = request.getRequestDispatcher(nextPage);
			disp.forward(request, response);
		}catch(Exception e) {
			e.printStackTrace();
		}
				
	}
}
