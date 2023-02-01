package pl.javastart.task;

public class Vehicle implements Comparable<Vehicle> {
    private String type;
    private String brand;
    private String model;
    private int year;
    private int course;
    private String vin;

    public Vehicle(String type, String brand, String model, int year, int course, String vin) {
        this.type = type;
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.course = course;
        this.vin = vin;
    }

    @Override
    public String toString() {
        return type + " " + brand + " " + model + " " + year + " " + course + " " + vin;
    }

    @Override
    public int compareTo(Vehicle v) {
        return vin.compareTo(v.vin);
    }
}
