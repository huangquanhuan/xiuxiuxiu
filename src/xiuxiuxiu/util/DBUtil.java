package xiuxiuxiu.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

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

	static {
//	    //SSH通道
//	    go();
	    //加载驱动
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	//http://www.cnblogs.com/I-will-be-different/p/3925351.html?utm_source=tuicool&utm_medium=referral
	public static void go() {
        String sshUser = "root";//SSH连接用户名
        String sshPassword = "Xiuxiuxiu.";//SSH连接密码
        String sshHost = "120.78.213.208";//SSH服务器
        int transmitPort = 55245;//本地转发端口 随意取
        int sshPort = 22;//SSH访问端口
        try {
            JSch jsch = new JSch();
            Session session = jsch.getSession(sshUser, sshHost, sshPort);
            session.setPassword(sshPassword);
            session.setConfig("StrictHostKeyChecking", "no");
            session.connect();
            System.out.println(session.getServerVersion());//这里打印SSH服务器版本信息
            int assignedPort = session.setPortForwardingL(transmitPort, ip, port);
            System.out.println("localhost:" + assignedPort + " -> " + ip + ":" + port);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
	public static Connection getConnection() throws SQLException {
		String url = String.format("jdbc:mysql://%s:%d/%s?characterEncoding=%s", ip, port, database, encoding);
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