import java.sql.SQLException;
import java.util.ArrayList;

import src.DBManager;
import src.Trip;

public class Main {

    public static void main(String[] args) {
        new DBManager();
        
        ArrayList <Trip> trips = DBManager.getTrips();
    }
}
