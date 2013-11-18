package demo;

import org.junit.Assert;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;
import static org.junit.Assert.assertSame;

public class ParkingLotTest {

    @Test
    public void should_show_available_lots() {
        ParkingLot lot = new ParkingLot(10);
        assertEquals(10, lot.availableLots());
    }

    @Test
    public void should_return_ticket_when_park_a_car() {
        ParkingLot lot = new ParkingLot(10);
        Ticket ticket = lot.park(new Car());
        assertNotNull(ticket);
    }

    @Test
    public void should_reduce_available_lots_after_parked_car() {
        ParkingLot lot = new ParkingLot(10);
        lot.park(new Car());
        assertEquals(9, lot.availableLots());
    }

    @Test
    public void should_return_null_when_park_car_in_full_lot() {
        ParkingLot lot = new ParkingLot(1);
        lot.park(new Car());
        Ticket ticket = lot.park(new Car());
        Assert.assertNull(ticket);
    }

    @Test
    public void should_retrieve_parked_car() {
        ParkingLot lot = new ParkingLot(10);
        Car car = new Car();
        Ticket ticket = lot.park(car);

        Car retrieved = lot.retrieve(ticket);
        assertSame(car, retrieved);
    }

    @Test
    public void should_increase_available_lots_after_retrieved_car() {
        ParkingLot lot = new ParkingLot(10);
        Car car = new Car();
        Ticket ticket = lot.park(car);

        lot.retrieve(ticket);
        assertEquals(10, lot.availableLots());
    }

    @Test
    public void should_return_null_given_invalid_ticket() {
        ParkingLot lot = new ParkingLot(10);
        Car car = new Car();
        Ticket ticket = lot.park(car);

        lot.retrieve(ticket);
        Car retrieved = lot.retrieve(ticket);
        assertNull(retrieved);
    }


}
