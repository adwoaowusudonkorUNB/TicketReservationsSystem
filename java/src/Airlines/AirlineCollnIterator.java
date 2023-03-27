package Airlines;

import Flight.FlightData;

import java.util.*;

public class AirlineCollnIterator implements AirlineIterator{
    private int position;
    private List<Airline> Airline_storage = new ArrayList<>();

    //private Iterator Airline_storage ;
    public AirlineCollnIterator(List<Airline> sentAirline){
        //this.position=0;
        this.Airline_storage = sentAirline;
    }
    @Override
    public boolean hasNext() {
        //System.out.println(Airline_storage.get(position));
        if(position >= Airline_storage.size()|| Airline_storage.get(position)==null){
            return false;
        }else{
            return true;
        }
    }
// it seems like with java's iterator we do not need AirlineCollnIterator
    //yeah
    @Override
    public Airline next() {
        if (!hasNext()) {
            throw new IndexOutOfBoundsException("No more elements");
        }
        //Map.Entry<String, Integer> entry = iterator.next();
        Airline A= Airline_storage.get(position);
        position++;
        return A;
    }
//    public void displayValue() {
//        Airline_storage.forEach((key, value) -> {
//            //coll = coll +"\n"+ key + ": " + value + " ";
//            System.out.println(key + ": " + value + " ");
//        });
//    }
}
