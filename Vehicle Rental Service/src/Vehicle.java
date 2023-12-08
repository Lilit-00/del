import java.io.Serializable;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;




public abstract class Vehicle implements Serializable {
 /*   @Serial
    private static final long serialVersionUID = 8340103473456058155L;*/
    private final int vehicleId;
    private final VehicleType vehicleType;
    private static int generateRoomId = 0;
    List<Reservation> reservationList = new ArrayList<>();

    public Vehicle(VehicleType vehicleType) {
        this.vehicleId = generateRoomId++;
        this.vehicleType = vehicleType;
    }
    public void addReservation(LocalDate startTime, LocalDate endTime){
        Reservation reservation = new Reservation(startTime,endTime);
        reservationList.add(reservation);

    }


    public int getVehicleId() {
        return vehicleId;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public double getPricePerDay() {
        return vehicleType.getPricePerDay();
    }

    public boolean canUsed(LocalDate startTime, LocalDate endTime) {
        if (reservationList.isEmpty()) {
            return true;
        }

        Reservation newReservation = new Reservation(startTime, endTime);

        for (Reservation existingReservation : reservationList) {
            LocalDate existingStartDate = existingReservation.getStartDate();
            LocalDate existingEndDate = existingReservation.getEndDate();

            if (!(newReservation.getStartDate().isAfter(existingEndDate)
                    || newReservation.getEndDate().isBefore(existingStartDate))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "vehicleId=" + vehicleId +
                ", vehicleType=" + vehicleType +
                ", reservationList=" + reservationList +
                '}';
    }
}


