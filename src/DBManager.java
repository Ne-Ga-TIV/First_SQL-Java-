package src;
import java.sql.*;
import java.util.ArrayList;


public class DBManager{

    private final static String userName = "anva8129";
    private final static String password = "cacaite228";
    private static Connection postGresConn = null;
    private static Statement statement = null;

    
    public DBManager()
    {
       try {
          Class.forName("org.postgresql.Driver");

        }
       catch (ClassNotFoundException cnfe) {
          System.out.println("Error: " + cnfe.getMessage());
          cnfe.printStackTrace();
          System.exit(1);
       }

       try {
          postGresConn = DriverManager.getConnection("jdbc:postgresql://pgsql3.mif/studentu", userName, password);
          statement = postGresConn.createStatement();
      }
      catch (SQLException sqle) {
         System.out.println("Error: " + sqle.getMessage());
          sqle.printStackTrace();
          System.exit(1);
      }
        
    }

    public static ArrayList<Trip> getTrips()
    {
      ArrayList<Trip> trips = new ArrayList<>();
      try{
        ResultSet rs = statement.executeQuery("SELECT * FROM Trip");
        while(rs.next()){
          trips.add(new Trip(rs.getInt(1), rs.getInt(2), 
          rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getTime(7), 
          rs.getTime(8), rs.getDate(9), rs.getDate(10)));
        
        }
    }catch(SQLException slqe){
        System.out.println("Error: " + slqe.getMessage());
        slqe.printStackTrace();
        System.exit(1);
    }

      return trips;
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