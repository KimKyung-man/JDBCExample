package kr.pe.gracefulife.jdbc.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Database 관리 클래스. Connection 및 Statement 를 생성 및 자원 해제를 해주는 것을 처리해주는 유틸 클래스이다.
 *
 * @author Gracefulife
 * @since 2015.10.12
 */
public class DatabaseManager {

	/* Constants */
	// DRIVER_PACKAGE = JDBC로 연결할 데이터베이스의 드라이버 패키지명
	// DB_URL = JDBC로 연결할 데이터베이스의 URL
	// DB_USER_ID = 계정 아이디
	// DB_USER_PW = 계정 비밀번호
	private static final String DRIVER_PACKAGE = "oracle.jdbc.driver.OracleDriver";
	private static final String DB_URL = "jdbc:oracle:thin:@192.168.0.10:1521:TEST";
	private static final String DB_USER_ID = "gracefulife";
	private static final String DB_USER_PW = "test";

	/**
	 * Connection 을 생성해 반환해주는 함수
	 * 
	 * @return database connection
	 */
	public static Connection getConnection() throws Exception {
		Connection conn = null;
		Class.forName(DRIVER_PACKAGE);
		conn = DriverManager.getConnection(DB_URL, DB_USER_ID, DB_USER_PW);
		return conn;
	}

	/**
	 * query를 생성해서 돌려주는 함수
	 * 
	 * @param conn
	 *            getConnection을 이용하여 만든 connection 객체
	 * @param query
	 *            DB에 요청할 쿼리
	 * @param params
	 *            각 ? 에 들어가야 할 값. 순차적으로 보내야 함
	 * @return 최종 sql
	 */
	public static PreparedStatement getStatement(Connection conn, String query,
			Object... params) throws SQLException {
		PreparedStatement pstmt = conn.prepareStatement(query);
		for (int i = 0; i < params.length; i++) {
			pstmt.setObject(i + 1, params[i]);
		}
		return pstmt;
	}

	/**
	 * 각 자원들을 일괄 해제해주는 함수
	 * 
	 * @param closables
	 *            Resultset, PreparedStatement, Connection 순서로 보내야 함
	 */
	public static void closeStreams(AutoCloseable... closables) {
		try {
			for (AutoCloseable closable : closables) {
				if (closable != null)
					closable.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
