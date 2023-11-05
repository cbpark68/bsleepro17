package sec03.brd07;

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
	
	public void modArticle(ArticleVO vo) {
		dao.updateArticle(vo);
	}
	
	public List<Integer> removeArticle(int articleNO){
		List<Integer> articleNOList = dao.selectRemoveArticles(articleNO);
		dao.deleteArticle(articleNO);
		return articleNOList;
	}
	
	public int addReply(ArticleVO vo) {
		return dao.insertNewArticle(vo);
	}
}