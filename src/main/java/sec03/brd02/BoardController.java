package sec03.brd02;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * Servlet implementation class BoardController
 */
@WebServlet(name = "BoardController2")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String ARTICLE_IMAGE_REPO = "/home/cbpark68/file_repo/article_image";
	private BoardService service;
	private ArticleVO vo;

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		service = new BoardService();
		vo = new ArticleVO();
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

	protected void doHandle(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String nextPage = "";
		String action = request.getPathInfo();
		System.out.println("action=" + action);
		try {
			List<ArticleVO> list = new ArrayList<ArticleVO>();
			if (action == null || action.equals("/")) {
				list = service.listArticles();
				System.out.println("list=" + list.toString());
				request.setAttribute("articlesList", list);
				nextPage = "/board02/listArticles.jsp";
			} else if (action.equals("/listArticles.do")) {
				list = service.listArticles();
				System.out.println("list=" + list.toString());
				request.setAttribute("articlesList", list);
				nextPage = "/board02/listArticles.jsp";
			} else if (action.equals("/articleForm.do")) {
				nextPage = "/board02/articleForm.jsp";
			} else if (action.equals("/addArticle.do")) {
				Map<String, String> map = upload(request, response);
				String title = map.get("title");
				String content = map.get("content");
				String imageFileName = map.get("imageFileName");
				vo.setParentNO(0);
				vo.setId("hong");
				vo.setTitle(title);
				vo.setContent(content);
				vo.setImageFileName(imageFileName);
				service.addArticle(vo);
				nextPage = "/board/listArticles.do";
			} else {
				nextPage = "/board02/listArticles.jsp";
			}
			RequestDispatcher disp = request.getRequestDispatcher(nextPage);
			disp.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private Map<String, String> upload(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Map<String, String> map = new HashMap<String, String>();
		String encoding = "utf-8";
		File cpath = new File(ARTICLE_IMAGE_REPO);
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setRepository(cpath);
		factory.setSizeThreshold(1024 * 1024);
		ServletFileUpload upload = new ServletFileUpload(factory);
		try {
			List items = upload.parseRequest(request);
			for (int i = 0; i < items.size(); i++) {
				FileItem fileItem = (FileItem) items.get(i);
				if (fileItem.isFormField()) {
					System.out.println(fileItem.getFieldName() + "=" + fileItem.getString(encoding));
					map.put(fileItem.getFieldName(), fileItem.getString(encoding));
				} else {
					if(fileItem.getSize()>0) {
						int idx = fileItem.getName().lastIndexOf("\\");
						if(idx == -1) {
							idx = fileItem.getName().lastIndexOf("/");
						}
						String fileName = fileItem.getName().substring(idx+1);
						map.put(fileItem.getFieldName(),fileName);
						File uploadFile = new File(cpath+"/"+fileName);
						fileItem.write(uploadFile);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
}