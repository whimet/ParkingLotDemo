package demo;

import org.junit.Assert;
import org.junit.Test;

import static junit.framework.Assert.assertNull;
import static junit.framework.Assert.assertSame;
import static org.junit.Assert.assertEquals;

public class SmartParkingBoyTest {

    @Test
    public void should_park_into_parking_lots_which_has_maximum_available_size() {
        ParkingLot lot1 = new ParkingLot(1);
        ParkingLot lot2 = new ParkingLot(2);
        SmartParkingBoy parkingBoy = new SmartParkingBoy(lot1, lot2);

        Car expectedCar = new Car();
        Ticket ticket = parkingBoy.park(expectedCar);
        Assert.assertSame(expectedCar, lot2.retrieve(ticket));
    }

    @Test
    public void should_reduce_available_spaces_after_parked_car() {
        SmartParkingBoy parkingBoy = new SmartParkingBoy(new ParkingLot(1), new ParkingLot(2));
        parkingBoy.park(new Car());
        assertEquals(2, parkingBoy.available());
    }

    @Test
    public void should_fail_if_all_parking_lots_are_full(){
        SmartParkingBoy parkingBoy = new SmartParkingBoy(a_full_parking_lot(1), a_full_parking_lot(2));
        Ticket ticket = parkingBoy.park(new Car());
        assertNull(ticket);
    }

    @Test
    public void should_retrieve_parked_car() {
        SmartParkingBoy parkingBoy = new SmartParkingBoy(new ParkingLot(1));
        Car car = new Car();
        Ticket ticket = parkingBoy.park(car);

        Car retrieved = parkingBoy.retrieve(ticket);
        assertSame(car, retrieved);
    }

    @Test
    public void should_return_null_when_retrieve_car_with_invalid_ticket() {
        SmartParkingBoy parkingBoy = new SmartParkingBoy(new ParkingLot(1));
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
