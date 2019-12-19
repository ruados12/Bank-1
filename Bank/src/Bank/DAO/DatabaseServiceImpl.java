package Bank.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Bank.Member;

public class DatabaseServiceImpl implements DatabaseService{
	// Driver Class
	final static String DRIVER = "oracle.jdbc.driver.OracleDriver";
	final static String URL = "jdbc:oracle:thin:@192.168.100.10:1521:xe";
	
	// SQL String
	final String INSERTSQL = "insert into member(id,pw,name,gender,age,hobby)"
			+ " values(?,?,?,?,?,?)";
	final String LOGINSQL = "select count(id) from member where id=? AND pw=?";
	
	private Connection dbConn;
	
	static {
		try {
			Class.forName(DRIVER);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public boolean Open() {
		try {
			String id = "kgitbank";
			String pw = "itbank";
			dbConn = DriverManager.getConnection(URL, id, pw);
			System.out.println("오라클 연결 성공");
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	
}
