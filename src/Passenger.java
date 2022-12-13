package src;

public class Passenger {
    private int passengerId;
    private String name, surname, fullName;


    public Passenger(int passengerId, String name, String surname, String fullName){
        this.passengerId = passengerId;
        this.name = name;
        this.surname = surname;
        this.fullName = fullName;
    }

    public String getName() { return name; }
    public int getPassengerId() { return passengerId; }
    public String getSurname() { return surname; }
    public String getFullName() { return fullName; }
}
