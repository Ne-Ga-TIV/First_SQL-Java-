package src;
import java.sql.Time;
import java.sql.Date;


public class Trip {
    private int tripId,companyId;
    private String airport, townFrom, townTo, planeModel;
    private Time timeStart, timeEnd;
    private Date dateStart, dateEnd;

    public Trip(int tripId, int companyId, String airport, String planeModel,
                String townFrom, String townTo, Time timeStart, 
                Time timeEnd, Date dateStart, Date dateEnd)
    {
        this.tripId = tripId;
        this.companyId = companyId;
        this.airport = airport;
        this.planeModel = planeModel;
        this.townFrom = townFrom;
        this.townTo = townTo;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
    }

    @Override
    public String toString(){
        return super.toString();
    }

    public String getAirport() { return this.airport; }
    public String getTownTo() {return this.townTo; }
    public String getTownFrom() { return this.townFrom; }
    public int getTripId() { return this.tripId; }
    public int getCompanyId() { return this.companyId; }
    public Date getDateStart() { return this.dateStart; }
    public Date getDateEnd() { return this.dateEnd; }
    public Time getTimeStart() { return this.timeStart; }
    public Time getTimeEnd() {return this.timeEnd; }


    
}
