package xiuxiuxiu.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 
 * @author Administrator
 * @
 */
public class DBUtil {

	static String ip = "127.0.0.1";
	static int port = 3306;
	static String database = "xiuxiuxiu";
	static String encoding = "UTF-8";
	static String loginName = "root";
	static String password = "123456";
	//static String password = "Liu_Zhongyu";

	static {
//	    //SSH通道
//	    go();
	    //加载驱动
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() throws SQLException {
		String url = String.format("jdbc:mysql://%s:%d/%s?characterEncoding=%s&serverTimezone=%s&useSSL=%b", ip, port, database, encoding, "GMT%2B8", false);
		return DriverManager.getConnection(url, loginName, password);
	}

	/**
	 * 
	 * @param rs 
	 * @param stmt
	 */
	/* 关闭连接的方法 */
	public static void close(ResultSet rs, Statement stmt, Connection conn) {
		try {
			if (rs != null) {
				rs.close();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		try {
			if (stmt != null) {
				stmt.close();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		try {
			if (conn != null) {
				conn.close();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static void main(String[] args) throws SQLException {
		System.out.println(getConnection());
	}
}