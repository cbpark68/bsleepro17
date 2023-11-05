package sec02.ex02;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class MemberDAO {
	private DataSource dataFactory;
	private Connection conn;
	private PreparedStatement pstmt;

	public MemberDAO() {
		try {
			Context ctx = new InitialContext();
			Context envContext = (Context)ctx.lookup("java:/comp/env");
			dataFactory = (DataSource)envContext.lookup("jdbc/oracle");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<MemberVO> listMembers(){
		List<MemberVO> list = new ArrayList<MemberVO>();
		try {
			conn = dataFactory.getConnection();
			String sql = "select * from t_member order by joinDate desc";
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				String id = rs.getString("id");
				String pwd = rs.getString("pwd");
				String name = rs.getString("name");
				String email = rs.getString("email");
				Date joinDate = rs.getDate("joinDate");
				MemberVO vo2 = new MemberVO(id,pwd,name,email,joinDate);
				System.out.println(vo2);
				list.add(vo2);
			}
			rs.close();
			pstmt.close();
			conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public void addMember(MemberVO vo) {
		try {
			conn = dataFactory.getConnection();
			String id = vo.getId();
			String pwd = vo.getPwd();
			String name = vo.getName();
			String email = vo.getEmail();
			String sql = "insert into t_member (id,pwd,name,email) values (?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pwd);
			pstmt.setString(3, name);
			pstmt.setString(4, email);
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public MemberVO findMember(String _id) {
		MemberVO vo = null;
		try {
			conn = dataFactory.getConnection();
			String sql = "select * from t_member where id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, _id);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			String id = rs.getString("id");
			String pwd = rs.getString("pwd");
			String name = rs.getString("name");
			String email = rs.getString("email");
			Date joinDate = rs.getDate("joinDate");
			vo = new MemberVO(id,pwd,name,email);
			rs.close();
			pstmt.close();
			conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return vo;
	}
	
	public void modMember(MemberVO vo) {
		String id = vo.getId();
		String pwd = vo.getPwd();
		String name = vo.getName();
		String email = vo.getEmail();
		try {
			conn = dataFactory.getConnection();
			String sql = "update t_member set pwd=?, name=?, email=? where id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, pwd);
			pstmt.setString(2, name);
			pstmt.setString(3, email);
			pstmt.setString(4, id);
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void delMember(String _id) {
		try {
			conn = dataFactory.getConnection();
			String sql = "delete from t_member where id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, _id);
			pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
