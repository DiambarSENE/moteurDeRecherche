package examples;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
 
public class DBsource {

  private String driver;
    private String url;
    private static DBsource instance =null;
 
    public DBsource() throws IOException, ClassNotFoundException {
        driver = "com.mysql.cj.jdbc.Driver";
        url = "jdbc:mysql://127.0.0.1/moteur?useUnicode=true&characterEncoding=UTF-8";
        Class.forName(driver);
    }
 
    public static DBsource getInstance() throws IOException, ClassNotFoundException{
        if (instance==null) {
            synchronized (DBsource.class) {
                if(instance==null){
                    instance = new DBsource();  
                }
            }
        }
        return instance;
    }
    public Connection getConnection() throws SQLException{
        return DriverManager.getConnection(url, "root", null);
    }
    public void closeAll(ResultSet rs, Statement ps,Connection conn) throws SQLException{
        closeResultSet(rs);
        closeStatement(ps);
        closeConnection(conn);
    }
    public static void closeConnection(Connection con) {
        try {
          if (con != null) {
            con.close();
          }
        }catch (SQLException ex) {
            ex.printStackTrace();
        }
      }
 
    public static void closeStatement(Statement st) {
        try {
          if (st != null) {
            st.close();
          }
        }catch (SQLException ex) {
            ex.printStackTrace();
        }
      }
 
    public static void closeResultSet(ResultSet rs) {
        try {
          if (rs != null) {
            rs.close();
          }
        }catch (SQLException ex) {
            ex.printStackTrace();
        }
      }
}

