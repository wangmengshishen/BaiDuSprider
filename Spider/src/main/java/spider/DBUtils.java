package spider;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBUtils {
		// 数据库连接
		private static final String URL = "jdbc:mysql://127.0.0.1:3306/spiderfiledb?useUnicode=true&characterEncoding=utf8&allowMultiQueries=true";
		private static final String DRIVER = "com.mysql.jdbc.Driver";
		private static final String userName="root";
		private static final String  password="root";
		public static Connection getConnection(){
			// 创建连接
			Connection con;
				try {
					Class.forName(DRIVER);
					con = DriverManager.getConnection(URL, userName, password);
					return con;
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					System.out.println("无法加载驱动......");
					e1.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					System.out.println("url或用户名密码错误！");
					e.printStackTrace();
				}
				return null;
		}
		public static void closeConnection(Connection con){
			if(con!=null){
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		public static void save(String sql,String title,String url,String time) throws SQLException{
			Connection conn=getConnection();
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1, title);
			ps.setString(2, url);
			ps.setString(3, time);
			ps.execute();
			closeConnection(conn);
		}
public static void main(String[] args) {
}
}
