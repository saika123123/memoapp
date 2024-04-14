import java.sql.*;
import bean.*;


public class StudentDAO3 {
  private final String URL = "jdbc:mysql://localhost/sampledb";
  private final String USER = "root";
  private final String PASS = "tubekobe";
  private Connection con = null;

  public void connect(){
    try{
      
      con = DriverManager.getConnection(URL, USER, PASS);
    } catch(Exception e){
      e.printStackTrace();
    }
  }
  
  public StudentDTO select() {
    Statement stmt = null;
    ResultSet rs = null;
    StudentDTO sdto = new StudentDTO();
    String sql = "SELECT * FROM student2";
    try{
      connect();
      
      stmt = con.createStatement();
      
      rs = stmt.executeQuery(sql);
      
      while(rs.next()){
        StudentBean sb = new StudentBean();
        sb.setdate(rs.getString("date"));
        sb.setName(rs.getString("name"));
        sb.setcontent(rs.getString("content"));
        sdto.add(sb);
      }
    } catch(Exception e){
      e.printStackTrace();
    } finally {
      try{
        if(rs != null) rs.close();
        if(stmt != null) stmt.close();
      } catch(Exception e){
        e.printStackTrace();
      }
    }
    disconnect();
    return sdto;
  }
  
  public int insert(String date, String name, String content) {
    String sql = "INSERT INTO student2 VALUES ('"
                   + date + "', '" + name + "', '" + content + "')";
    return executeSql(sql);
  }
  
  public int update(String date, String name, String content) {
    String sql = "UPDATE student2 SET date = '" + date + "', name = '" + name
                   + "', content = '" + content + "' WHERE date = '" + date + "'";
    return executeSql(sql);
  }
  
  public int delete(String date) {
    String sql = "DELETE FROM student2 WHERE date = '" + date + "'";
    return executeSql(sql);
  }
  
  public int executeSql(String sql) {
    Statement stmt = null;
    ResultSet rs = null;
    int result = 0;
    try{
      connect();
      
      stmt = con.createStatement();
      
      result = stmt.executeUpdate(sql);
    } catch(Exception e){
      e.printStackTrace();
    } finally {
      try{
        if(rs != null) rs.close();
        if(stmt != null) stmt.close();
      } catch(Exception e){
        e.printStackTrace();
      }
    }
    disconnect();
    return result;
  }

  public void disconnect(){
    try{
      
      if(con != null) con.close();
    } catch(Exception e){
      e.printStackTrace();
    }
  }
}

