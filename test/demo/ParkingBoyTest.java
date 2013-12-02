package demo;

import org.junit.Test;

import static junit.framework.Assert.assertNull;
import static junit.framework.Assert.assertSame;
import static org.junit.Assert.assertEquals;

public class ParkingBoyTest {

    @Test
    public void should_manage_multiple_lots() {
        ParkingBoy parkingBoy = new ParkingBoy(new ParkingLot(1), new ParkingLot(2));
        int number = parkingBoy.available();
        assertEquals(3, number);
    }

    @Test
    public void should_reduce_available_spaces_after_parked_car() {
        ParkingBoy parkingBoy = new ParkingBoy(new ParkingLot(1), new ParkingLot(2));
        parkingBoy.park(new Car());
        assertEquals(2, parkingBoy.available());
    }

    @Test
    public void should_not_park_car_when_lots_are_full() {
        ParkingLot lot1 = a_full_parking_lot(1);
        ParkingLot lot2 = a_full_parking_lot(2);
        ParkingBoy parkingBoy = new ParkingBoy(lot1, lot2);

        Ticket ticket = parkingBoy.park(new Car());
        assertNull(ticket);
    }

    @Test
    public void should_retrieve_parked_car() {
        ParkingBoy parkingBoy = new ParkingBoy(new ParkingLot(1));
        Car car = new Car();
        Ticket ticket = parkingBoy.park(car);

        Car retrieved = parkingBoy.retrieve(ticket);
        assertSame(car, retrieved);
    }

    @Test
    public void should_return_null_when_retrieve_car_with_invalid_ticket() {
        ParkingBoy parkingBoy = new ParkingBoy(new ParkingLot(1));
        Car car = new Car();
        Ticket ticket = parkingBoy.park(car);

        parkingBoy.retrieve(ticket);
        Car retrieved = parkingBoy.retrieve(ticket);

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
