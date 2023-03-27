package Flight;

import java.util.GregorianCalendar;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

public class FlightDataCollection  implements DbActivitySubscriber {
    private FlightData flightdata;
    private static FlightDataCollection singleton_flightdatacollection=null;
    private Map<String, FlightData> flightData_storage = new HashMap<>();
    private Map<String,  ArrayList<String>> flightData_storage_index_of_ORGDES_date = new HashMap<>();
    private FlightDataCollection(){    }// in singleton we restrict anyone from calling the constructor
    private static Statement statement=null;
    private static Connection connection;

    public void add(String id,FlightData c) {
        flightData_storage.put(id,c);
        //DbInsert newDb = new DbInsert();
        //newDb.Flight(c);
    }


    public FlightDataIterator createIterator() {
        return new FlightDataCollectionIterator(flightData_storage);
    }
    private void FlightDataCollection(){}
    public static FlightDataCollection getInstance()
    {
        if(singleton_flightdatacollection==null)
        {
            try
            {
                Class.forName("com.mysql.cj.jdbc.Driver");
                String url = "jdbc:mysql://localhost:3306/reservations";
                connection = DriverManager.getConnection(url, "root", "");
                statement = connection.createStatement();
            } catch (Exception e) {
                System.out.println(e);
            }
            singleton_flightdatacollection = new FlightDataCollection();
            return singleton_flightdatacollection;
        }
        else
        {
            return singleton_flightdatacollection;
        }
    }
    void populate(Flight flight, String date, int day) throws Exception
    {
            int availSeatsEconomy, availSeatsBusiness, availSeatsFirst, availSeatsPremiumEconomy;
            String flightno=flight.getFlightNo();
            ResultSet r1;
            //First Class is 1
            //Business is 2
            //Premium economy is 3
            //Economy is 4
            availSeatsFirst=flight.getNoOfSeatF();
            availSeatsBusiness=flight.getNoOfSeatB();
            availSeatsPremiumEconomy=flight.getNoOfSeatPE();
            availSeatsEconomy= flight.getNoOfSeatE();
            r1 = statement.executeQuery("select COUNT(Ticket_Number) from bookings where Flight_Number='" + flightno + "' AND Flight_Departure_Date='" + date + "' AND Status='BOOKED' AND Booking_Class=1");
            if (r1.next()) { availSeatsFirst-=r1.getInt(1); }
            r1 = statement.executeQuery("select COUNT(Ticket_Number) from bookings where Flight_Number='" + flightno + "' AND Flight_Departure_Date='" + date + "' AND Status='BOOKED' AND Booking_Class=2");
            if (r1.next()) { availSeatsBusiness-=r1.getInt(1); }
            r1 = statement.executeQuery("select COUNT(Ticket_Number) from bookings where Flight_Number='" + flightno + "' AND Flight_Departure_Date='" + date + "' AND Status='BOOKED' AND Booking_Class=3");
            if (r1.next()) { availSeatsPremiumEconomy-=r1.getInt(1); }
            r1 = statement.executeQuery("select COUNT(Ticket_Number) from bookings where Flight_Number='" + flightno + "' AND Flight_Departure_Date='" + date + "' AND Status='BOOKED' AND Booking_Class=4");
            if (r1.next()) { availSeatsEconomy-=r1.getInt(1); }
            r1.close();
            flightdata=new FlightData(flightno+"_"+day,availSeatsFirst,availSeatsBusiness,availSeatsPremiumEconomy,availSeatsEconomy);
            flightData_storage.put(flightno+"_"+date,flightdata);

    }
        public ArrayList<FlightData> results (ArrayList flights, String date, int class_of_travel, String origin, String destination)
    {
        if (!flightData_storage.containsKey(flights.get(0)+"_"+date))
        {
// calculate the count from bookings table and populate seat count variable of FlightData object
            Iterator iterate_flights_local=flights.iterator();
            int dd, mm, yy;
            dd = Integer.valueOf(date.substring(0, 2));
            mm = Integer.valueOf(date.substring(2, 4)) - 1;
            yy = Integer.valueOf(date.substring(4, 6));
            int day = new GregorianCalendar(yy + 2000, mm, dd).get(Calendar.DAY_OF_WEEK);
            String OrgDesDay=origin+destination+day;
            FlightCollection flightcollection=FlightCollection.getInstance();
            Flight flight;
            try{
                while(iterate_flights_local.hasNext())
                {
                    flight=flightcollection.getObject((String) iterate_flights_local.next(),day);
                    populate(flight,date,day);
                }
                if (!flightData_storage_index_of_ORGDES_date.containsKey(OrgDesDay+day))
                {
                    ArrayList<String> datelist=new ArrayList<String>();
                    datelist.add(date);
                    flightData_storage_index_of_ORGDES_date.put(OrgDesDay+day,datelist);
                }else
                {
                    ArrayList<String> datelist =flightData_storage_index_of_ORGDES_date.get(OrgDesDay+day);
                    datelist.add(date);
                }

           } catch (Exception e) {
               System.out.println(e);
           }
        }
        Iterator iterate_flights=flights.iterator();
        ArrayList<FlightData> results_list= new ArrayList<>();
        FlightData temp_flighdata;
        while (iterate_flights.hasNext())
        {
            temp_flighdata=flightData_storage.get(iterate_flights.next() + "_" + date);
            if (class_of_travel==1){
                if(temp_flighdata.getAvailSeatsFirst()>0){results_list.add(temp_flighdata);}
            }
            else if (class_of_travel==2){
                if(temp_flighdata.getAvailSeatsBusiness()>0){results_list.add(temp_flighdata);}
            }
            else if (class_of_travel==3){
                if(temp_flighdata.getAvailSeatsPremiumEconomyEconomy()>0){results_list.add(temp_flighdata);}
            }
            else if (class_of_travel==4){
                if(temp_flighdata.getAvailSeatsEconomy()>0){results_list.add(temp_flighdata);}
            }
        }
        return  results_list;
    }

    void refresh(String flightid)
    {

        //remove the existing instance of the flightid from flightSeatData create a new one from database and include add it in the database
    }

    @Override
    public void update(Object flightid, Object day_of_week)
    {
        try
        {
            ResultSet r1= statement.executeQuery("select * from flights where Flight_Number='"+flightid+"' AND Day='"+day_of_week+"'");
            if(r1.next())
            {
                String OrgDesDay=r1.getString("Origin_Airport")+r1.getString("Destination_Airport")+day_of_week;

                if(flightData_storage_index_of_ORGDES_date.containsKey(OrgDesDay))
                {
                    ArrayList<String> datelist =flightData_storage_index_of_ORGDES_date.get(OrgDesDay);
                    Iterator datelist_iterator=datelist.iterator();
                    String date;
                    Flight flight=new Flight(r1.getString("Flight_Number"), r1.getString("Origin_Airport"), r1.getString("Destination_Airport"), r1.getInt("Departure_Time"), r1.getInt("Arrival_Time"), r1.getInt("Duration"), r1.getInt("Day"),r1.getInt("Economy_Seats"),r1.getInt("Premium_Economy_Seats"),r1.getInt("Business_Seats"),r1.getInt("FirstClass_Seats"));

                    while(datelist_iterator.hasNext())
                    {
                        date=(String) datelist_iterator.next();
                        populate(flight,date,(int)day_of_week);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
