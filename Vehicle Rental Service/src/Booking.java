import java.io.Serializable;
import java.time.LocalDate;

public class Booking implements Serializable {

    private final Customer customer;
    private final Vehicle vehicle;
    private final LocalDate startDate;
    private final LocalDate endDate;


    public Booking(Customer customer, Vehicle vehicle, LocalDate startDate, LocalDate endDate) {
        this.customer = customer;
        this.vehicle = vehicle;
        this.startDate = startDate;
        this.endDate = endDate;
        vehicle.addReservation(startDate,endDate);
    }

    public double getCost() {
       return (endDate.toEpochDay() - startDate.toEpochDay()) * vehicle.getPricePerDay();
    }

    public Customer getCustomer() {
        return customer;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }


}
