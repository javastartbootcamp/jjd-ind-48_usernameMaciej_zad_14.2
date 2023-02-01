package pl.javastart.task;

import java.io.*;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class ReadFromUser {
    private Queue<Vehicle> vehicles = new PriorityQueue<>();
    private Scanner scanner = new Scanner(System.in);

    void mainLoop() {
        Option option;
        do {
            System.out.println("Wybierz opcje:");
            printOptions();
            option = Option.createFromInt(scanner.nextInt());
            scanner.nextLine();
            if (option == Option.ADD) {
                vehicles.add(addNewVehicle());
            } else if (option == Option.TAKE) {
                if (vehicles.isEmpty()) {
                    try {
                        readVehiclesFromFile(vehicles, "vehicles.csv");
                    } catch (FileNotFoundException e) {
                        System.err.println("Nie udalo sie odczytac danych z pliku");
                    }
                } else {
                    takeVehicle();
                }
            } else if (option == Option.EXIT) {
                System.out.println("Koniec programu!");
            }
            if (!vehicles.isEmpty()) {
                try {
                    saveVehiclesToFile("vehicles.csv");
                } catch (IOException e) {
                    System.err.println("Nie udało się zapisać danych do pliku.");
                }
            }
        } while (option != Option.EXIT);
    }

    private Queue<Vehicle> readVehiclesFromFile(Queue<Vehicle> vehicles, String fileName) throws FileNotFoundException {
        Scanner scanner1 = new Scanner(new File(fileName));
        while (scanner1.hasNextLine()) {
            String line = scanner1.nextLine();
            String[] split = line.split(" ");
            String type = split[0];
            String brand = split[1];
            String model = split[2];
            int year = Integer.parseInt(split[3]);
            int course = Integer.parseInt(split[4]);
            String vin = split[5];
            vehicles.add(new Vehicle(type, brand, model, year, course, vin));
            System.out.println(line);
        }
        return vehicles;
    }

    private void saveVehiclesToFile(String fileName) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
        for (Vehicle vehicle : vehicles) {
            writer.write(vehicle.toString());
            writer.newLine();
        }
        writer.close();
    }

    private void takeVehicle() {
        Vehicle poll = vehicles.poll();
        System.out.println("Pojazd do przeglądu:");
        System.out.println(poll);

    }

    private Vehicle addNewVehicle() {
        System.out.println("Podaj typ pojazdu:");
        String type = scanner.nextLine();
        System.out.println("Podaj marke:");
        String brand = scanner.nextLine();
        System.out.println("Podaj model:");
        String model = scanner.nextLine();
        System.out.println("Podaj rocznik:");
        int year = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Podaj przebieg:");
        int course = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Podaj numer VIN:");
        String vinNumber = scanner.nextLine();
        return new Vehicle(type, brand, model, year, course, vinNumber);
    }

    private void printOptions() {
        Option[] values = Option.values();
        for (Option value : values) {
            System.out.println(value);
        }
    }
}
