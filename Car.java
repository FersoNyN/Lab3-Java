import java.io.Serializable;

public class Car implements Serializable {
    private static final long serialVersionUID = 1L;  // Recommended for Serializable classes

    private String licensePlate;
    private String brand;
    private int yearOfProduction;

    // Constructor
    public Car(String licensePlate, String brand, int yearOfProduction) {
        this.licensePlate = licensePlate;
        this.brand = brand;
        this.yearOfProduction = yearOfProduction;
    }

    // Getters
    public String getLicensePlate() {
        return licensePlate;
    }

    public String getBrand() {
        return brand;
    }

    public int getYearOfProduction() {
        return yearOfProduction;
    }

    // toString method to display car details
    @Override
    public String toString() {
        return licensePlate + " " + brand + " " + yearOfProduction;
    }
}
