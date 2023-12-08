
public enum VehicleType{

    CAR("Car", 50),
    BIKE("Bike", 10),
    VAN("Van", 80);

    private final String typeName;
    private final int pricePerDay;

    VehicleType(String typeName,int pricePerDay) {
        this.typeName = typeName;
        this.pricePerDay = pricePerDay;
    }

    public String getTypeName() {
        return typeName;
    }

    public double getPricePerDay() {
        return pricePerDay;
    }

}