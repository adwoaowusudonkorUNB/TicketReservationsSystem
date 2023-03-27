package Flight;

import DbConnect.DatabaseConnection;

import java.io.IOException;
import java.sql.*;
import java.util.*;


public class DbInsert {
    //private FlightData flight;
    private static Statement statement = null;
    private static Connection connection;

    ArrayList<DbActivitySubscriber> add_flight_subscribers = new ArrayList<>();
    ArrayList<DbActivitySubscriber> add_flight_default_subscribers = new ArrayList<>();
    private void notify_add_flight_subscribers (Flight nflight)
    {
        String flightid=nflight.getFlightNo();
        int day_of_week= nflight.getDow();
        Iterator temp_iterator= add_flight_subscribers.iterator();
        while (temp_iterator.hasNext())
        {
            ((DbActivitySubscriber)temp_iterator.next()).update(flightid,day_of_week);
        }
        temp_iterator= add_flight_default_subscribers.iterator();
        while (temp_iterator.hasNext())
        {
            ((DbActivitySubscriber)temp_iterator.next()).update(flightid,day_of_week);
        }
    }

    DatabaseConnection dbCon;

    public DbInsert() {
        try {
            dbCon = DatabaseConnection.getInstance();
            connection = dbCon.getConnection();
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        add_flight_default_subscribers.add(FlightCollection.getInstance());
        add_flight_default_subscribers.add(FlightDataCollection.getInstance());
        add_flight_default_subscribers.add(SearchFlightCollection.getInstance());

    }

    public boolean AddFlight(Flight nflight) {
        try {
            String sql = " insert into flights (Flight_Number, Origin_Airport, Destination_Airport,"
                    + " Departure_Time, Arrival_Time,Duration,Day,Economy_Seats,Premium_Economy_Seats,Business_Seats,FirstClass_Seats)"
                    + " values (?, ?, ?,?,?,?, ?, ?,?,?,?)";
            PreparedStatement preparedStmt = connection.prepareStatement(sql);
            preparedStmt.setString(1, nflight.getFlightNo());
            preparedStmt.setString(2, nflight.getOriginAirport());
            preparedStmt.setString(3, nflight.getDestAirport());
            preparedStmt.setInt(4, nflight.getDepatureTime());
            preparedStmt.setInt(5, nflight.getArrivalTime());
            preparedStmt.setInt(6, nflight.getDuration());
            preparedStmt.setInt(7, nflight.getDow());
            preparedStmt.setInt(8, nflight.getNoOfSeatE());
            preparedStmt.setInt(9, nflight.getNoOfSeatPE());
            preparedStmt.setInt(10, nflight.getNoOfSeatB());
            preparedStmt.setInt(11, nflight.getNoOfSeatF());
            //preparedStmt.setBigDecimal   (10, this.price);
            boolean rowsAffected = preparedStmt.execute();
            this.notify_add_flight_subscribers (nflight);

            return rowsAffected;
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
        }
        return false;
    }

    public void subscribe_add_flight(DbActivitySubscriber subscribe_request)
    {
        if(!add_flight_subscribers.contains(subscribe_request))
        {
            add_flight_subscribers.add(subscribe_request);
        }
    }
    public void unsubscribe_add_flight(DbActivitySubscriber unsubscribe_request)
    {
        if(add_flight_subscribers.contains(unsubscribe_request))
        {
            add_flight_subscribers.remove(unsubscribe_request);
        }

    }


    public boolean RemoveFlight(String flightid, int day_of_week)
    {
        try {
            statement = connection.createStatement();
            int rowsAffected= statement.executeUpdate("delete from flights where Flight_Number='"+flightid+"' AND Day='"+day_of_week+"'");
            System.out.println(rowsAffected);
            if (rowsAffected > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

}
