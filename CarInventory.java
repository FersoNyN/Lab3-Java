import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CarInventory {
    private List<Car> inventory;

    // Constructor initializing the inventory list
    public CarInventory() {
        inventory = new ArrayList<>();
    }

    // Method to add a car to the inventory
    public void addCar(String licensePlate, String brand, int yearOfProduction) {
        Car car = new Car(licensePlate, brand, yearOfProduction);
        inventory.add(car);
    }

    // Method to list all cars in the inventory
    public void listCars() {
        if (inventory.isEmpty()) {
            System.out.println("No cars in the inventory.");
        } else {
            for (Car car : inventory) {
                System.out.println(car);
            }
        }
    }

    public static void main(String[] args) {
        CarInventory carInventory = new CarInventory();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String command = scanner.nextLine();
            String[] parts = command.split(" ");
            
            if (parts[0].equalsIgnoreCase("add")) {
                // Command: add <licensePlate> <brand> <yearOfProduction>
                String licensePlate = parts[1];
                String brand = parts[2];
                int yearOfProduction = Integer.parseInt(parts[3]);
                carInventory.addCar(licensePlate, brand, yearOfProduction);
            } else if (parts[0].equalsIgnoreCase("list")) {
                // Command: list
                carInventory.listCars();
            } else {
                System.out.println("Unknown command.");
            }
        }
    }
}
