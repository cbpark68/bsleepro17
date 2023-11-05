package sec02.ex02;

import java.io.IOException;
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
 * Servlet implementation class MemberServlet
 */
@WebServlet(name = "MemberController3", urlPatterns = { "/member/*" })
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	MemberDAO dao;

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		dao = new MemberDAO();
	}

	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doHandle(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doHandle(request, response);
	}

	protected void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String nextPage = null;
		String action = request.getPathInfo();
		if(action == null || action.equals("/listMembers.do")){
			List<MemberVO> list = dao.listMembers();
			request.setAttribute("membersList", list);
			nextPage="/test03/listMembers.jsp";
		}else if(action.equals("/addMember.do")) {
			String id = request.getParameter("id");
			String pwd = request.getParameter("pwd");
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			MemberVO vo2 = new MemberVO(id,pwd,name,email);
			dao.addMember(vo2);
			request.setAttribute("msg", "addMember");
			nextPage="/member/listMembers.do";
		}else if(action.equals("/memberForm.do")) {
			nextPage="/test03/memberForm.jsp";
		}else if(action.equals("/modMemberForm.do")) {
			String id = request.getParameter("id");
			MemberVO vo = dao.findMember(id);
			request.setAttribute("memInfo", vo);
			nextPage = "/test03/modMemberForm.jsp";
		}else if(action.equals("/modMember.do")) {
			String id = request.getParameter("id");
			String pwd = request.getParameter("pwd");
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			MemberVO vo2 = new MemberVO(id,pwd,name,email);
			dao.modMember(vo2);
			request.setAttribute("msg", "modified");
			nextPage="/member/listMembers.do";
		}else if(action.equals("/delMember.do")) {
			String id = request.getParameter("id");
			dao.delMember(id);
			request.setAttribute("msg", "deleted");
			nextPage="/member/listMembers.do";
		}else {
			List<MemberVO> list = dao.listMembers();
			request.setAttribute("membersList", list);
			nextPage="/test03/listMembers.jsp";
		}
		RequestDispatcher disp = request.getRequestDispatcher(nextPage);
		disp.forward(request,response);
	}
}
