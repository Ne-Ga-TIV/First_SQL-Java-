import java.sql.*;


public class Main{

    private final static String userName = "anva8129";
    private final static String password = "cacaite228";


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

    public static Connection getConnection() 
    {
        Connection postGresConn = null;
        try {
          postGresConn = DriverManager.getConnection("jdbc:postgresql://pgsql3.mif/studentu", userName, password);
        }
        catch (SQLException sqle) {
          System.out.println("Couldn't connect to database!");
          sqle.printStackTrace();
          return null ;
        }
        return postGresConn ;
     }


    public static void closeConnection(Connection con)
    {
        try{
            con.close();
        }
        catch(SQLException sqle){
            System.out.println("Couldn`t close connect to database!");
            sqle.printStackTrace();
            System.exit(2);
        }
    } 
    public static void main(String[] args)
     {
        loadDriver();
        Connection connection  = getConnection();
        



        closeConnection(connection);
        
    }
}