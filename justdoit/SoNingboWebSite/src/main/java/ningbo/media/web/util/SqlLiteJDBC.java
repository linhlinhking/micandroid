package ningbo.media.web.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SqlLiteJDBC {
	
	  /**
	   * 获得数据库连接
	   * @return
	   */
	  public static Connection getConnection(){
		Connection conn = null;
		try {
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection("jdbc:sqlite:test.db");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	  }
	  
	  /**
	   * 保存用户
	   * @param username
	   * @param email
	   */
	  public void saveUser(String username, String email){
		Connection conn = getConnection();
		PreparedStatement prep = null;
	    try {
			prep = conn.prepareStatement("insert into reuser values (?, ?);");
			prep.setString(1, username);
	  	    prep.setString(2, email);
	  	    prep.execute();
	  	    conn.setAutoCommit(true);
	  	    conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	  }
	
	  public static void main(String[] args) throws Exception {
		Connection conn = getConnection();
	    Statement stat = conn.createStatement();
	    stat.executeUpdate("drop table if exists reuser;");
	    stat.executeUpdate("create table reuser (name, email);");
//	    PreparedStatement prep = conn.prepareStatement(
//	      "insert into reuser values (?, ?);");
//
//	    prep.setString(1, "111");
//	    prep.setString(2, "222@qq.com");
//	    prep.addBatch();
//
//	    conn.setAutoCommit(false);
//	    prep.executeBatch();
//	    conn.setAutoCommit(true);

	    ResultSet rs = stat.executeQuery("select * from reuser;");
	    while (rs.next()) {
	      System.out.println("name = " + rs.getString("name"));
	      System.out.println("email = " + rs.getString("email"));
	    }
	    rs.close();
	    conn.close();
	  }
	}