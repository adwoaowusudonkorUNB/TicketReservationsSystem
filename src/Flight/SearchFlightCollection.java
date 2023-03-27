package Flight;
import java.sql.*;
import java.util.*;

public class SearchFlightCollection implements DbActivitySubscriber
{
    private static SearchFlightCollection singleton_searchflightcollection=null;
    private Map<String, ArrayList<String>> searchflight_storage = new HashMap<>();
    //key is (String)origin+destination+day_of_week
    private static Statement statement=null;
    private static Connection connection;

    private SearchFlightCollection(){}
    public static SearchFlightCollection getInstance()
    {
        if(singleton_searchflightcollection==null)
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
            singleton_searchflightcollection = new SearchFlightCollection();
            return singleton_searchflightcollection;
        }
        else
        {
            return singleton_searchflightcollection;
        }
    }
    public ArrayList Searchflight(String origin, String destination,int day_of_week)
    {
        if (!searchflight_storage.containsKey(origin+destination+day_of_week))
        {
            try
            {
                ArrayList<String> flightlist=new ArrayList<String>();
                ResultSet r1= statement.executeQuery("select Flight_Number from flights where Origin_Airport='"+origin+"' AND Destination_Airport='"+destination+"' AND Day='"+day_of_week+"'");
                while(r1.next())
                {
                    flightlist.add(r1.getString("Flight_Number"));
                }
                searchflight_storage.put(origin+destination+day_of_week,flightlist);

                r1.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        return searchflight_storage.get(origin+destination+day_of_week);
    }
    public void Verify_Origin_Destination_flights(String origin, String destination)
    {

    }
    @Override
    public void update(Object flightid, Object day_of_week)
    {
        try
        {
            ResultSet r1= statement.executeQuery("select * from flights where Flight_Number='"+flightid+"' AND Day='"+day_of_week+"'");
            if(r1.next())
            {
                String key=r1.getString("Origin_Airport")+r1.getString("Destination_Airport")+day_of_week;
                if(searchflight_storage.containsKey(key))
                {
                   searchflight_storage.get(key).add((String) flightid);
                }
        }
        } catch (Exception e) {
            System.out.println(e);
        }
   }
}