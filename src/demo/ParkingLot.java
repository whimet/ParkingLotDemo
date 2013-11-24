package demo;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    private int capacity;
    private Map<Ticket,Car> ticketCarMap;

    public ParkingLot(int capacity) {
        this.capacity = capacity;
        ticketCarMap = new HashMap<Ticket, Car>();
    }

    public int available() {
        return capacity - ticketCarMap.size();
    }

    public Ticket park(Car car) {
        if (available() == 0) {
            return null;
        }
        
        Ticket ticket = new Ticket();
        ticketCarMap.put(ticket, car);
        return ticket;
    }

    public Car retrieve(Ticket ticket) {
        return ticketCarMap.remove(ticket);
    }
}
