import java.io.IOException;
import java.io.ObjectInputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.io.*;
import java.util.Map;

public class BaseSystem {

    String vehiclesFilePath = "VehicleRegister.bin";
    String costumerFilePath = "CustumerRegister.bin";
    final List<Vehicle> vehicles= new ArrayList<>();
    final List<Customer> customers= new ArrayList<>();
    final Map<Vehicle, List<Booking>> roomBookingsMap = new HashMap<>();
    final Map<Customer, List<Booking>> customerBookingsMap = new HashMap<>();


    public void registerCar(){

        System.out.println("Car added.");
        vehicles.add(new Car());
    }
    public void registerBike(){

        System.out.println("Bike added.");
        vehicles.add(new Bike());
    }
    public void registerVan(){

        System.out.println("Van added.");
        vehicles.add(new Van());
    }
    public boolean contain(Customer customer){
        for (Customer el: customers){
            if (el.equals(customer))
                return true;
        }
        return false;
    }

    public  void registerCustomer(String name, String email,int numberLicense){
        Customer customerCurrent = new Customer(name, email,numberLicense);
        boolean flag = false;

        for (Customer element : customers) {
            if (customerCurrent.equals(element)){
                flag = true;
                System.err.println("Customer is already registered.");
                break;
            }
        }

        if (flag) {
            customers.add(customerCurrent);
            System.out.println("Customer registered successfully.");
        }
    }
    public  void bookVehicle(Customer customer, int id, LocalDate start,LocalDate end) {

    }


    public void  loadAllData(){
        loadRooms();
        loadCostumer();
    }
    public void saveAllData(){
        saveCustumers();
        saveRooms();
    }
    private void saveRooms() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(vehiclesFilePath))) {
            oos.writeObject(vehicles);
            oos.flush();
        } catch (IOException e) {
            System.err.println("Error saving vehicles data: " + e.getMessage());
        }
    }
    private void saveCustumers(){
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(costumerFilePath))) {
            oos.writeObject(customers);
            oos.flush();
        } catch (IOException e) {
            System.err.println("Error saving customers data: " + e.getMessage());
        }
    }

    private void loadCostumer(){
        try (ObjectInputStream ois= new ObjectInputStream(new FileInputStream(costumerFilePath))) {
            List<Customer> currentList = (List<Customer>) ois.readObject();
            customers.addAll(currentList);
            System.out.println("Custumer loaded successfully from " + costumerFilePath);
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("No saved costumer data found.");
        }
    }
    private void loadRooms() {
        try (ObjectInputStream ois= new ObjectInputStream(new FileInputStream(vehiclesFilePath))) {
            List<Vehicle> currentList = (List<Vehicle>) ois.readObject();
            vehicles.addAll(currentList);
            System.out.println("Vehicles data loaded successfully from " + vehiclesFilePath);
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("No saved vehicles data found.");
        }
    }
}
