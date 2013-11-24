package demo;

import org.junit.Test;

import static junit.framework.Assert.assertNull;
import static junit.framework.Assert.assertSame;
import static org.junit.Assert.assertEquals;

public class ParkerTest {

    @Test
    public void should_manage_multiple_lots() {
        Parker parker = new Parker(new ParkingLot(1), new ParkingLot(2));
        int number = parker.available();
        assertEquals(3, number);
    }

    @Test
    public void should_reduce_available_spaces_after_parked_car() {
        Parker parker = new Parker(new ParkingLot(1), new ParkingLot(2));
        parker.park(new Car());
        assertEquals(2, parker.available());
    }

    @Test
    public void should_not_park_car_when_lots_are_full() {
        ParkingLot lot1 = a_full_parking_lot(1);
        ParkingLot lot2 = a_full_parking_lot(2);
        Parker parker = new Parker(lot1, lot2);

        Ticket ticket = parker.park(new Car());
        assertNull(ticket);
    }

    @Test
    public void should_retrieve_parked_car() {
        Parker parker = new Parker(new ParkingLot(1));
        Car car = new Car();
        Ticket ticket = parker.park(car);

        Car retrieved = parker.retrieve(ticket);
        assertSame(car, retrieved);
    }

    @Test
    public void should_return_null_when_retrieve_car_with_invalid_ticket() {
        Parker parker = new Parker(new ParkingLot(1));
        Car car = new Car();
        Ticket ticket = parker.park(car);

        parker.retrieve(ticket);
        Car retrieved = parker.retrieve(ticket);

        assertNull(retrieved);
    }

    private ParkingLot a_full_parking_lot(int capacity) {
        ParkingLot lot = new ParkingLot(capacity);
        for (int i = 0; i < capacity; i++) {
            lot.park(new Car());
        }
        return lot;
    }
}
