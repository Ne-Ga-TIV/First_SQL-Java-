package src;
import java.sql.*;


public class DBManager{

    private final static String userName = "anva8129";
    private final static String password = "cacaite228";
    private static Connection postGresConn = null;

    
    public DBManager()
    {
       try {
         Class.forName("org.postgresql.Driver");
       }
       catch (ClassNotFoundException cnfe) {
         System.out.println("Couldn't find driver class!");
         cnfe.printStackTrace();
         System.exit(1);
       }

       try {
        postGresConn = DriverManager.getConnection("jdbc:postgresql://pgsql3.mif/studentu", userName, password);
      }
      catch (SQLException sqle) {
        System.out.println("Couldn't connect to database!");
        sqle.printStackTrace();
      }


    }

    public static void getTripsInformation(Statement stmt)
    {
        
    }

    public static void closeConnection()
    {
        try{
          postGresConn.close();
        }
        catch(SQLException sqle){
            System.out.println("Couldn`t close connect to database!");
            sqle.printStackTrace();
            System.exit(2);
        }
    } 
}