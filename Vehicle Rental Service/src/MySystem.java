import java.sql.SQLOutput;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;


public class MySystem {

    {
        loadData();
    }
    private final BaseSystem baseOfProgram= new BaseSystem();

    // Main method to manage the hotel room system
    public void startProgram() {
        Scanner sc = new Scanner(System.in);
        String operator;
        System.out.println("Welcome !!!");
        do {
            printMenu();
            operator = sc.next();
            switch (operator) {
                case "1" -> addVehicle(sc);

                case "2" -> addCustomer(sc);

                case "3" ->  bookRoom();

                case "4" -> {
                   // saveData();
                }
                case "5" -> {
                   // makeReport(sc);
                }
                case "6" -> {
                  //  baseOfProgram.printHotelInfo();
                }
                case "0" -> {
                    System.out.println("Exit the program, do you want to save data?  Y/N");
                    String choice = sc.next().toLowerCase();
                    if(choice.equals("y")){
                        saveData();
                    }

                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        } while (!operator.equals("0"));
    }

    // Utility method to read an integer input from the user
/*    private int readIntInput(Scanner sc, String prompt) {
        int input = 0;
        boolean validInput = false;

        while (!validInput) {
            try {
                System.out.print(prompt);
                input = sc.nextInt();
                validInput = true;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid numeric choice.");
                sc.nextLine();
            }
        }
        return input;
    }*/

    private void vehiclesOfTheMoment(){

      List<Vehicle> currentlist =  baseOfProgram.vehicles;
      currentlist.sort((el1, el2) -> (int) (el1.getPricePerDay() - el2.getPricePerDay()));

        System.out.println("ll");
      for (Vehicle el : currentlist) {
          if(el.getPricePerDay() == 10){
              System.out.println("Car - " + el.getVehicleId());
          }
          if(el.getPricePerDay() == 50){
              System.out.println("Bike - " + el.getVehicleId());

          }else {
              System.out.println("Van - " + el.getVehicleId());

          }
      }
    }

    void addVehicle(Scanner sc){
        String output = """
                1 - Car
                2 - Bike
                3 - Van
                """;
        System.out.println(output);
        boolean flag = false;
        while (!flag){
            System.out.println("Please, choose one of this:  1 , 2 , 3");
            switch (sc.next()){
                case "1" -> {
                    baseOfProgram.registerCar();
                    flag = true;
                }
                case "2" -> {
                    baseOfProgram.registerBike();
                    flag = true;
                }
                case "3" -> {
                    baseOfProgram.registerVan();
                    flag= true;
                }
            }
        }
    }



    public void addCustomer(Scanner sc) {
        String name, email;
        int numberLicense;
        System.out.println("Enter name : ");
        name = sc.next();

        System.out.println("Enter email :");
        email = sc.next();

        System.out.println("Enter number of License:");
        numberLicense = sc.nextInt();

       /* while (true) {
            System.out.print("Name: ");
            name = sc.next();
            if (validateName(name)) {
                break;
            } else {
                System.out.println("Invalid name. Please try again.");
            }
        }

        while (true) {
            System.out.print("Email: ");
            email = sc.next();
            if (validateEmail(email)) {
                break;
            } else {
                System.out.println("Invalid email. Please try again.");
            }
        }

        while (true) {
            System.out.print("Number license: ");
            numberLicense = sc.next();
            if (validateNumberLicence(numberLicense)) {
                break;
            } else {
                System.out.println("Invalid number of License . Please try again.");
            }
        }
*/
        baseOfProgram.registerCustomer(name, email,numberLicense);
    }
    void bookRoom(){
        Scanner sc = new Scanner(System.in);
        String name,email;
        int numberLicense;
        LocalDate start,end;


        System.out.println("Enter name:");
        name= sc.next();
        System.out.println("Enter email:");
        email = sc.next();
        System.out.println("Enter numberLicense");
        numberLicense = sc.nextInt();

        while (true){

                System.out.println("Start Date (yyyy-MM-dd):");
                start = LocalDate.parse(sc.next());
                System.out.println("End Date (yyyy-MM-dd):");
                end = LocalDate.parse(sc.next());

                if (validateDate(start,end)) {
                    break;
                }

        }

        /*baseOfProgram.availableRoomsForPeriod(start, end);
        int id = readIntInput(sc, "Enter room ID: ");*/


        Customer customer = new Customer(name, email,numberLicense);
        if(!baseOfProgram.contain(customer)){
            baseOfProgram.registerCustomer(name,email,numberLicense);
        }
        System.out.println("ll");

        vehiclesOfTheMoment();
        int id = sc.nextInt();
        System.out.println("ll1");

        try {
            baseOfProgram.bookVehicle(customer, id, start, end);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

    }

    // Method to generate a report
  /*  public void makeReport(){
        baseOfProgram.printRoomInfo();
        int roomId = readIntInput(sc,"Input ID");

        Vehicle vehicle = baseOfProgram.findRoomById(roomId);

        if (vehicle == null) {
            System.out.println("Room with ID " + roomId + " not found.");
        } else {
            System.out.println("Generating report for Room ID " + roomId + "...");
            baseOfProgram.roomBookingReport(roomId, "reports.txt");
            System.out.println("Report generated successfully.");
        }
    }*/

    // Method to save data to a file
    public void saveData(){
        try {
            baseOfProgram.saveAllData();
            System.out.println("All data saved successfully to file");
        } catch (Exception e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }


    public void loadData(){
        try {
            baseOfProgram.loadAllData();
            System.out.println("All data reads  successfully from file");
        } catch (Exception e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }
/*    public boolean validateEmail(String email) {
        String regex = "^[a-z0-9][a-z0-9.+-]*@[a-z][a-z.-]*\\.[a-z]*$";
        return Pattern.matches(regex, email);
    }*/

    /*public boolean validateName(String name) {
        String regex = "^[a-zA-Z\\s-]+$";
        return Pattern.matches(regex, name);
    }*/

    public boolean validateNamberLicense(int n){
        return n < 7 && n > 0;
    }
    public boolean validateDate(LocalDate first, LocalDate second) {
        if (first.isAfter(second)) {
            System.out.println("input valid dates.");
            return false;
        }

        LocalDate currentDate = LocalDate.now();

        if (first.isBefore(currentDate)) {
            System.out.println("start date must be on or after the current date.");
            return false;
        }

        return true;
    }

    // Utility method to print the main menu
    private void printMenu() {
        String message = """
                1: Add a Car
                2: Add a Customer
                3: Vehicle a  for a Customer
                4: Save System State to a File
                5: Print info
                0: Exit
                """;
        System.out.println(message);
    }
}