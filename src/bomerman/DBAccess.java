package bomerman;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.sqlite.SQLiteConfig;

public class DBAccess {

   /**
    * @param args
    */
   public static void main(String[] args) {

      Connection connection = null;
      PreparedStatement preparedStatement = null;
      String dbPath = "jdbc:sqlite:Midas.sqlite3";
      String sqlString = "DELETE * FROM asdf WHERE id = ?"; 
      
      try {
          Class.forName("org.sqlite.JDBC"); // charger le driver pour SQLite
        
          SQLiteConfig config = new SQLiteConfig();
          config.enforceForeignKeys(true);
          connection = DriverManager.getConnection(dbPath, config.toProperties());
        
          preparedStatement = connection.prepareStatement(sqlString, Statement.RETURN_GENERATED_KEYS);
          preparedStatement.setQueryTimeout(30);
          
          // pour un insert
          preparedStatement.setString(1, "adsf");
          preparedStatement.execute();
          ResultSet result = preparedStatement.getGeneratedKeys();
          result.next();
          int generatedPrimaryKey = result.getInt(1);
          
          // pour un update ou delete
          
          preparedStatement.setInt(1, 5);
          preparedStatement.execute();
          
          // pour un select
          ResultSet resultSet = preparedStatement.executeQuery();
          while (resultSet.next()) {
             int i = result.getInt(1);
             String s = result.getString(2);
             boolean b = result.getBoolean(3);
          }
          
          
          
      }
      catch (Exception e) {
           e.printStackTrace();
      }
      finally {
         try {
            preparedStatement.getConnection().close();
         } catch (SQLException e) {
            e.printStackTrace();
         }
      }
   }

}
