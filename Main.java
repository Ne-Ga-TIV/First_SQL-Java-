import java.sql.*;
import java.util.Scanner;
import src.UI;




public class Main {
  public static final String [] mainMenu = {"Register for trip", "Check out my trips",
                                "Change my place", "Exit"};
  
  
  
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
          Statement statement = con.createStatement();

          ResultSet rsTrips = statement.executeQuery("SELECT town_from, town_to," +
                                "date_start, date_end, company_name, trip_id FROM trip, company" +
                                " WHERE trip.company_id =  company.company_id");
          
          ResultSet rsCount = statement.executeQuery("SELECT COUNT(*) FROM trip");
          rsCount.next();
          
          int count = rsCount.getInt(1);
          if(count == 0){
            System.out.println("Sorry, there are currently no available trips");
            return;
          }

          UI.printTrips(rsTrips);
          System.out.println("Please select the trip number you want to check(for exit enter 0)" + "");

          int select = UI.readAnswer(0, Integer.MAX_VALUE);


          if(select == 0) { return; }
          
          System.out.println("Are you a new passenger?[y/n]");
          
          Scanner s = new Scanner(System.in);
          String an;
          
          do{
            an = s.nextLine();
            if(an != "y" && an != "n"){
              System.out.println("Please enter y/n" + an);
              
            }
          }while(an != "y" && an != "n");   
          if(an == "y")
              newPassenger(select, con);
          else{
            System.out.println("Please enter your id");
            s.nextLine();

          }
        }catch(SQLException sqle){
          System.out.println("Couldn't create statment");
          sqle.printStackTrace();
          return;
        }                  
    }

    public static boolean checkPlace(int trip, int place, Connection con){
      try{
        PreparedStatement statement = con.prepareStatement("SELECT COUNT(*) FROM passenger_in_trip WHERE trip_id = ? AND place = ?");
        statement.setInt(1, trip);
        statement.setString(2, Integer.valueOf(place).toString());
        ResultSet rs = statement.executeQuery();
        rs.next();
        if(rs.getInt(0) != 0){
          return false;
        }
        return true;
      }catch(SQLException sqle){}
      return false;


    }
    public static void newPassenger(int trip, Connection con){
      String name = null, surname = null;
      
      System.out.println("Please enter your name");
      name =  UI.readStr();
      System.out.println("Please enter your surname");
      surname =  UI.readStr();
      int place = 0;
      do{
        System.out.println("please enter the desired place");
        place = UI.readAnswer(1, 100);
        if(!checkPlace(trip, place, con)){
          place = 0;
          System.out.println("Sorry this place is already taken");
        }
      }while(place == 0);
      
     
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
