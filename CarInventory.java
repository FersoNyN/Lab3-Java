import java.io.*;
import java.util.*;

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

    // Method to list cars with sorting based on argument
    public void listCars(String sortBy) {
        if (inventory.isEmpty()) {
            System.out.println("No cars in the inventory.");
        } else {
            if (sortBy.equalsIgnoreCase("plate")) {
                Collections.sort(inventory, new PlateComparator());
            } else if (sortBy.equalsIgnoreCase("brand")) {
                Collections.sort(inventory, new BrandComparator());
            } else if (sortBy.equalsIgnoreCase("year")) {
                Collections.sort(inventory, new YearComparator());
            }

            for (Car car : inventory) {
                System.out.println(car);
            }
        }
    }

    // Method to delete a car by license plate
    public void deleteCar(String licensePlate) {
        // Sort the inventory by plate for binary search
        Collections.sort(inventory, new PlateComparator());
        
        // Create a temporary car object with the target license plate for comparison
        Car tempCar = new Car(licensePlate, "", 0);
        
        // Perform binary search
        int index = Collections.binarySearch(inventory, tempCar, new PlateComparator());
        
        if (index >= 0) {
            inventory.remove(index);
            System.out.println("Car with license plate " + licensePlate + " has been deleted.");
        } else {
            System.out.println("Car with license plate " + licensePlate + " not found.");
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
                // Command: list [optional: plate | brand | year]
                if (parts.length == 1) {
                    carInventory.listCars("");  // Default order (no sorting)
                } else {
                    String sortBy = parts[1];
                    carInventory.listCars(sortBy);
                }
            } else if (parts[0].equalsIgnoreCase("delete")) {
                // Command: delete <licensePlate>
                String licensePlate = parts[1];
                carInventory.deleteCar(licensePlate);
            } else if (parts[0].equalsIgnoreCase("save")) {
                // Command: save <fileName>
                String fileName = parts[1];
                carInventory.saveInventory(fileName);
            } else if (parts[0].equalsIgnoreCase("load")) {
                // Command: load <fileName>
                String fileName = parts[1];
                carInventory.loadInventory(fileName);
            } else {
                System.out.println("Unknown command.");
            }
        }
    }

    // Save method as before
    public void saveInventory(String fileName) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName))) {
            out.writeObject(inventory);
            System.out.println("Inventory saved to " + fileName);
        } catch (IOException e) {
            System.out.println("Error saving inventory: " + e.getMessage());
        }
    }

    // Load method as before
    public void loadInventory(String fileName) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName))) {
            inventory = (List<Car>) in.readObject();
            System.out.println("Inventory loaded from " + fileName);
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading inventory: " + e.getMessage());
        }
    }
}
