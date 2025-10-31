package model;


// Clase abstracta que representa un dispositivo genérico.
//Implementa Comparable para ordenar por consumo eléctrico (powerConsumption).
 
public abstract class Device implements Comparable<Device> {
    private final String id;          // id único (inmutable)
    private String name;
    private String manufacturer;
    private double powerConsumption; // en Watts

    public Device(String id, String name, String manufacturer, double powerConsumption) {
        this.id = id;
        this.name = name;
        this.manufacturer = manufacturer;
        this.powerConsumption = powerConsumption;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getManufacturer() { return manufacturer; }
    public void setManufacturer(String manufacturer) { this.manufacturer = manufacturer; }
    public double getPowerConsumption() { return powerConsumption; }
    public void setPowerConsumption(double powerConsumption) { this.powerConsumption = powerConsumption; }

    @Override
    public int compareTo(Device other) {
        return Double.compare(this.powerConsumption, other.powerConsumption);
    }

    @Override
    public String toString() {
        return String.format("%s (ID: %s) - %s - %.2f W", name, id, manufacturer, powerConsumption);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Device)) return false;
        Device d = (Device) o;
        return this.id.equals(d.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
