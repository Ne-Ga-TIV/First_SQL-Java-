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
    public static void registerForTrips(Connection con){
        try{
          con.setAutoCommit(false);
          Statement statement = con.createStatement();
          ResultSet rsTrips = statement.executeQuery("SELECT town_from, town_to," +
                                "date_start, date_end, company_name FROM trip, company" +
                                " WHERE trip.company_id =  company.company_id");
          long count = statement.executeQuery("SECETC COUNT(*) FROM trip").getLong(1);
          
          con.commit();
          if(count == 0){
              System.out.println("Sorry, there are currently no available trips");
              return;
          }

          UI.printTrips(rsTrips);

          System.out.println("Please select the trip number you want to check in for:1 - " + count);

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
        return postGresConn ;
     }

    public static void main(String[] args) {
        final String [] mainMenu = {"Register for trip", "Check out my trips",
                                      "Change my place", "Exit"};
        loadDriver();
        Connection connection = getConnection();
        
        
        
        while(true){
            
            UI.printList(mainMenu);
            System.out.println("Please select one of the options " + "[1 - " + mainMenu.length + "]");
            int select = UI.readAnswer(1, mainMenu.length);
            
            switch (select) {
                case 1:
                    registerForTrips(connection);
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
