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
        
        System.out.printf("NR        %20s %20s %20s %20s %20s\n", "FROR", "TO", "DATE START", "DATE END", "COMPANY");
        try{
            if(trip == null){
                System.out.println("Is Nuuulll");
                return;
            }
            while(trip.next()){
                System.out.printf("%-10d %20s %20s %20s %20s %20s\n", trip.getInt(6), trip.getString(1), trip.getString(2), trip.getDate(3).toString(),
                                    trip.getDate(4).toString(), trip.getString(5));
            }
        }catch(SQLException slqe){}
        

    }

    public static boolean sqlValid(String in){
        return in.matches("[A-Za-z]+");
    }
    
    public static void printPassengerTrip(){

    }
    public static String readStr(){
        
        Scanner s = new Scanner(System.in);
        String inputStr = null;
        
        do{
            inputStr = s.nextLine();
            if(!sqlValid(inputStr)){
                System.out.println("Please enter only letters");
                inputStr = null;
            }
        }while(inputStr == null);
        return inputStr;

    }
    public static void printList(String navList[]){
        
        for (int i = 1; i <= navList.length; i++) {
            System.out.println("[" + i + "] " + navList[i - 1]);
        }
    }
    public static void printTrips(){

    }

    
}
