package demo;

public class ParkingBoy {
    private ParkingLot[] parkingLots;

    public ParkingBoy(ParkingLot... parkingLots) {
        this.parkingLots = parkingLots;
    }

    public int available() {
        int result = 0;
        for (ParkingLot lot : parkingLots) {
            result += lot.available();
        }
        return result;
    }

    public Ticket park(Car car) {
        for (ParkingLot lot : parkingLots) {
            Ticket ticket = lot.park(car);
            if (ticket != null) {
                return ticket;
            }
        }
        return null;
    }

    public Car retrieve(Ticket ticket) {
        for (ParkingLot lot : parkingLots) {
            Car car = lot.retrieve(ticket);
            if (car != null) {
                return car;
            }
        }
        return null;
    }
}
