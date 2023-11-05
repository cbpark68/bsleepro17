package sec03.brd02;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class BoardDAO {
	private DataSource dataFactory;
	private Connection conn;
	private PreparedStatement pstmt;

	public BoardDAO() {
		try {
			Context ctx = new InitialContext();
			Context envContext = (Context)ctx.lookup("java:/comp/env");
			dataFactory = (DataSource)envContext.lookup("jdbc/oracle");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public List selectAllArticles() {
		List list = new ArrayList();
		try {
			conn = dataFactory.getConnection();
			String sql = "select level,articleno,parentno,title,content,id,writedate from t_board ";
			sql += " start with parentno = 0 connect by prior articleno = parentno order siblings by articleno desc ";
			pstmt = conn.prepareStatement(sql);
			System.out.println("sql="+sql);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				int level = rs.getInt("level");
				int articleno = rs.getInt("articleno");
				int parentno = rs.getInt("parentno");
				String title = rs.getString("title");
				String content = rs.getString("content");
				String id = rs.getString("id");
				Date writeDate = rs.getDate("writedate");
				ArticleVO vo = new ArticleVO();
				vo.setLevel(level);
				vo.setArticleNO(articleno);
				vo.setParentNO(parentno);
				vo.setTitle(title);
				vo.setContent(content);
				vo.setId(id);
				vo.setWriteDate(writeDate);
				list.add(vo);
				//System.out.println("vo="+vo.toString());
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public int getNewAtricleNO() {
		try {
			conn = dataFactory.getConnection();
			String sql = "select max(articleno) from t_board";
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next())
				return (rs.getInt(1)+1);
			rs.close();
			pstmt.close();
			conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public void insertNewArticle(ArticleVO vo) {
		try {
			conn = dataFactory.getConnection();
			int articleNO = getNewAtricleNO();
			int parentNO = vo.getParentNO();
			String title = vo.getTitle();
			String content = vo.getContent();
			String id = vo.getId();
			String imageFileName = vo.getImageFileName();
			String sql = "insert into t_board (articleno,parentno,title,content,imagefilename,id) ";
			sql += " values (?,?,?,?,?,?) ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, articleNO);
			pstmt.setInt(2, parentNO);
			pstmt.setString(3, title);
			pstmt.setString(4, content);
			pstmt.setString(5, imageFileName);
			pstmt.setString(6, id);
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
