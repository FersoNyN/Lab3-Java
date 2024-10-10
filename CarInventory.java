import java.io.*;
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

    // Method to save the inventory to a file
    public void saveInventory(String fileName) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName))) {
            out.writeObject(inventory);
            System.out.println("Inventory saved to " + fileName);
        } catch (IOException e) {
            System.out.println("Error saving inventory: " + e.getMessage());
        }
    }

    // Method to load the inventory from a file
    public void loadInventory(String fileName) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName))) {
            inventory = (List<Car>) in.readObject();
            System.out.println("Inventory loaded from " + fileName);
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading inventory: " + e.getMessage());
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
}
