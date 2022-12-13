import java.net.ConnectException;
import java.sql.*;
import java.util.Scanner;
import src.UI;
import java.util.UUID;



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
    public static int generateUniqueId() {      
      UUID idOne = UUID.randomUUID();
      String str=""+idOne;        
      int uid=str.hashCode();
      String filterStr=""+uid;
      str=filterStr.replaceAll("-", "");
      return Integer.parseInt(str);
  }

    public static boolean checkId(Connection con, int id){
      try{
        PreparedStatement statement = con.prepareStatement("SELECT COUNT(*) FROM passenger WHERE passenger_id = ?");
        statement.setInt(1, id);

        ResultSet rs = statement.executeQuery();
        rs.next();
        if(rs.getInt(0) == 0){
          return true;
        }
        return false;
      }catch(SQLException sqle){}
      
      return false;
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
            if(an.equals("y") && an.equals("n")){
              System.out.println("Please enter y/n");
              
            }
          }while(an.equals("y") && an.equals("n"));   
          
          if(an.equals("y"))
              newPassenger(select, con);
          else{
          
            System.out.println("Please enter your id");

            int id = UI.readAnswer(1, Integer.MAX_VALUE);
            
            if(checkId(con, id)){
              System.out.println("No passenger with this ID");
               return;
            }
            int place = 0;
           
            do{
              System.out.println("Please enter the desired place");
              place = UI.readAnswer(1, 100);
              
              if(!checkPlace(select, place, con)){
                place = 0;
                System.out.println("Sorry this place is already taken");
              }
            }while(place == 0);

            PreparedStatement statement2 = con.prepareStatement("INSERT INTO passenger_in_trip VALUES(?, ?, ?)");
            statement2.setInt(1, select);
            statement2.setInt(2, id);
            statement2.setString(3, Integer.valueOf(place).toString());
            statement2.executeUpdate();
            System.out.println("You have successfully registered for the trip, your personal ID: " +  id + ", please don't forget this");
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

        System.out.println(rs.getInt(0));
        return true;
      }catch(SQLException sqle){}
      return true;


    }
    
    public static void getPassengerTrips(int id, Connection con){
      try{
        PreparedStatement statement = con.prepareStatement("SELECT town_from, town_to," +
                                                    "date_start, date_end, company_name, trip_id FROM trip, company, passenger_in_trip" +
                                                    "WHERE trip.company_id =  company.company_id AND"+  
                                                    "passenger_in_trip.trip_id = trip.trip_id AND passenger_in_trip.passenger_id = ?");
        statement.setInt(1, id);

        ResultSet rsTrips = statement.executeQuery();
        UI.printTrips(rsTrips);

      }catch(SQLException sqle){}

    }
    public static void newPassenger(int trip, Connection con){
      
      String name = null, surname = null;
      
      System.out.println("Please enter your name");
      name =  UI.readStr();
      
      System.out.println("Please enter your surname");
      surname =  UI.readStr();
      
      int place = 0;
      
      do{
        System.out.println("Please enter the desired place");
        place = UI.readAnswer(1, 100);
        
        if(!checkPlace(trip, place, con)){
          place = 0;
          System.out.println("Sorry this place is already taken");
        }
      }while(place == 0);

      try{
        con.setAutoCommit(false);
        int passId = generateUniqueId();
       
        PreparedStatement statement1 = con.prepareStatement("INSERT INTO passenger VALUES (?, ?, ?)");
        statement1.setInt(1, passId);
        statement1.setString(2, name);
        statement1.setString(3, surname);
        statement1.executeUpdate();
       
        PreparedStatement statement2 = con.prepareStatement("INSERT INTO passenger_in_trip VALUES (?, ?, ?)");
        statement2.setInt(1, trip);
        statement2.setInt(2, passId);
        statement2.setString(3, Integer.valueOf(place).toString());
        statement2.executeUpdate();
        con.commit();
        System.out.println("You have successfully registered for the trip, your personal ID: " +  passId + ", please don't forget this");
        con.setAutoCommit(true);
        return;  
      
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
