import java.sql.*;
import java.util.ArrayList;
import src.UI;
import src.Trip;




public class Main {
    
  public static void loadDriver()
  {
     try {
       Class.forName("org.postgresql.Driver");
     }
     catch (ClassNotFoundException cnfe) {
       System.out.println("Couldn't find driver class!");
       cnfe.printStackTrace();
       System.exit(1);
     }
  }
    public static void RegisterForTrips(Connection con){
        try{
          Statement statement = con.createStatement();
          ResultSet rs = statement.executeQuery("SELECT town_from, town_to," +
                                "date_start, date_end, company_name FROM trip, company" +
                                " WHERE trip.company_id =  company.company_id");
          UI.printTrips(rs);

        }catch(SQLException sqle){

        }                  
    }
    public static Connection getConnection() {
        
        Connection postGresConn = null;
        
        try {
          postGresConn = DriverManager.getConnection("jdbc:postgresql://pgsql3.mif/studentu", "anva8129", "cacaite228") ;
        }
        catch (SQLException sqle) {
          System.out.println("Couldn't connect to database!");
          sqle.printStackTrace();
          return null ;
        }
        System.out.println("Successfully connected to Postgres Database");
 
        return postGresConn ;
     }

    public static void main(String[] args) {

        loadDriver();
        Connection connection = getConnection();
        
        final String [] mainMenu = {"Register for trip", "Check out my trips",
                                "Change my place", "Exit"}; 
        
        while(true){
            
            UI.printList(mainMenu);
            System.out.println("Please select one of the options " + "[1 - " + mainMenu.length + "]");
            int select = UI.readAnswer(1, mainMenu.length);
            
            switch (select) {
                case 1:
                  
                    break;
                case 2:
                    break;
                case 3:{

                }
                    break;

                case 4:
                    return;
            }
        }


    }
}
