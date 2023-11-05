package sec03.brd01;

import java.util.List;

public class BoardService {
	BoardDAO dao;
	
	public BoardService() {
		dao = new BoardDAO();
	}
	
	public List<ArticleVO> listArticles(){
		List<ArticleVO> list = dao.selectAllArticles();
		return list;
	}
}
