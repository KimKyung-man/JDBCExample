package kr.pe.gracefulife.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import kr.pe.gracefulife.jdbc.dto.User;
import kr.pe.gracefulife.jdbc.utils.DatabaseManager;

/**
 * Dao 클래스. User 관련 데이터베이스 처리를 행해주는 클래스.
 *
 * @author Gracefulife
 * @since 2015.10.12
 */
public class UserDao {
	
	/**
	 * 유저의 아이디를 받아, 정보를 돌려주는 함수
	 * 
	 * @param userId
	 * @return User 객체
	 */
	public static User findUser(String userId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			// PreparedStatement 형태의 쿼리를 준비함.
			// 쿼리 중 실제 값이 들어갈 부분에 ? 처리.
			String query = "select * from TEST_USER where ID=?";

			User user = new User();

			// DatabaseManager에게 Connection을 요청함
			conn = DatabaseManager.getConnection();
			// DatabaseManager에게 statement를 채워줄 것을 요청함.
			// getStatement connection, query, 이후로 파라메터를 줄줄히 입력하면된다.
			pstmt = DatabaseManager.getStatement(conn, query, userId);
//			pstmt = DatabaseManager.getStatement(conn, query, userId, pw, email); 등등
			// 쿼리 실행 후 결과를 받는다.
			rs = pstmt.executeQuery();

			if (rs.next()) {
				user.setId(rs.getString("ID"));
				user.setPw(rs.getString("PW"));
				return user;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// DatabaseManager에게 close해야 할 객체를 전부 넘겨준다.
			DatabaseManager.closeStreams(rs, pstmt, conn);
		}
		return null;
	}
}
