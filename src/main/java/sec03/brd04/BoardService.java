package sec03.brd04;

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
	
	public int addArticle(ArticleVO vo) {
		return dao.insertNewArticle(vo);
	}
	
	public ArticleVO viewArticle(int articleNO) {
		ArticleVO vo = null;
		vo = dao.selectArticle(articleNO);
		return vo;
	}
}
