package demo;

public class SmartParkingBoy {
    private ParkingLot[] parkingLots;

    public SmartParkingBoy(ParkingLot... parkingLots) {
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
        int index = 0;
        for (int i = 0; i < parkingLots.length -1; i++) {
            if(parkingLots[i].available() < parkingLots[i+1].available()){
                index = i+1;
            }
        }
        return parkingLots[index].park(car);
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
