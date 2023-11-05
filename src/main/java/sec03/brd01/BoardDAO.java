package sec03.brd01;

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
				System.out.println("vo="+vo.toString());
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}
