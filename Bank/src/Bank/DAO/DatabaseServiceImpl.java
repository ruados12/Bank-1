package Bank.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Bank.Member;
import Bank.Service.CommonService;
import Bank.Service.CommonServiceImpl;

public class DatabaseServiceImpl implements DatabaseService{
   // Driver Class
   final static String DRIVER = "oracle.jdbc.driver.OracleDriver";
   final static String URL = "jdbc:oracle:thin:@192.168.100.10:1521:xe";

   // SQL String
   final String INSERTSQL = "insert into bankmember(id,pw,name,phone,ppnum,banknum,balance)"
         + " values(?,?,?,?,?,?,?)";
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

   @Override
   public void Insert(Member member) { 
      try {
         PreparedStatement prep = dbConn.prepareStatement(INSERTSQL);
         // 동적할당
         prep.setString(1, member.getId());
         prep.setString(2, member.getPw());
         prep.setString(3, member.getName());
         prep.setString(4, member.getPhoneNum());
         prep.setString(5, member.getIdNum());
         prep.setString(6, member.getAccNum());
         prep.setLong(7, member.getMoney()); 

         System.out.println("회원 등록 완료");
         prep.executeUpdate();
      } catch(SQLException e) {
         e.printStackTrace();
      }
  
   }

   @Override
   public boolean Select(String id, String pw) {
      boolean result = true;
      
      CommonService commonSrv = new CommonServiceImpl();

      try {
         PreparedStatement prep = dbConn.prepareStatement(LOGINSQL);
         prep.setString(1, id);
         prep.setString(2, pw);

         ResultSet rs = prep.executeQuery();
         if(rs.next()) {
            if(rs.getInt(1)==0) {
               commonSrv.InfoMsg("입력하신 정보가 틀립니다.", "ID와 패스워드를 다시 확인해주세요.");
               result = false;
            }
            rs.close();
         } 
      } catch (SQLException e) {
         result = false;
         e.printStackTrace();
      }

      return result;
   }
}