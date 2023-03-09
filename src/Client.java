import java.awt.*;

public class Client extends Checkin{

    protected int ID;

    public Client(String FName, String SName, int RegNo, String Checkin, int RoomNo, int Numofpersons,int ID ){
        super(FName, SName, RegNo, Checkin, RoomNo, Numofpersons);
        this.ID=ID;
    }


    public String getCheckinDetails (){
        return FName + SName + RegNo + Checkin + RoomNo + Numofpersons + ID ;
    }

    @Override
    public String ShowCheckinDetails() {
        String details = "Fname is: " + FName + "\n" +
                "Second name is: " + SName + "\n" +
                "RegNo is: " + RegNo + "\n" +
                "Checkin date is: " + Checkin + "\n" +
                "Room number is: " + RoomNo + "\n" +
                "Number of persons is: " + Numofpersons + "\n" +
                "The ID is: " + Integer.toString(ID);
        System.out.println(details);
        return details;
    }

}
