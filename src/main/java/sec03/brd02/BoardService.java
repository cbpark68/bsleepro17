package sec03.brd02;

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
	
	public void addArticle(ArticleVO vo) {
		dao.insertNewArticle(vo);
	}
}
