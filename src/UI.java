package src;
import java.util.Scanner;
import java.sql.*;
import java.lang.Runtime;
public class UI {
   
    private static Scanner input = new Scanner(System.in);
  

    

    public static int readAnswer(int from, int to){

        String in;
        int answer;
        do{
            in = input.nextLine();
            answer = Integer.parseInt(in) ;
            if(from > answer ||answer  > to){
                System.out.println("Please select one of the options " + "[" + from + " - " + to + "]");
            }

        }while(from > answer || answer > to);
        return answer;
    }

    public static void printTrips(ResultSet trip){
        int i = 1;
        try{
            Runtime.getRuntime().exec("clear");
        }catch(Exception e){}
        System.out.printf("NR     %20s %20s %20s %20s %20s\n", "FROR", "TO", "DATE START", "DATE END", "COMPANY");
        try{
            while(trip.next()){
                System.out.printf("%-7d %20s %20s %20s %20s %20s\n", i, trip.getString(1), trip.getString(2), trip.getDate(3).toString(),
                                    trip.getDate(4).toString(), trip.getString(5));
                i++;
            }
        }catch(SQLException slqe){}

    }
    public static void printList(String navList[]){
        
        for (int i = 1; i <= navList.length; i++) {
            System.out.println("[" + i + "] " + navList[i - 1]);
        }
    }
    public static void printTrips(){

    }

    
}
