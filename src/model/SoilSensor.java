package model;

import model.interfaces.Measurable;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;


//Sensor de suelo que mide humedad y temperatura.
//Implementa Measurable.

public class SoilSensor extends Device implements Measurable {
    private String location; // parcela, lote, etc.
    private final Random rng = new Random();

    public SoilSensor(String id, String name, String manufacturer, double powerConsumption, String location) {
        super(id, name, manufacturer, powerConsumption);
        this.location = location;
    }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    @Override
    public Map<String, Double> readMeasurements() {
        Map<String, Double> m = new HashMap<>();
        // valores simulados
        m.put("soil_moisture", 10 + rng.nextDouble() * 60); // %
        m.put("soil_temp", 10 + rng.nextDouble() * 20); // °C
        return m;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" [SoilSensor at %s]", location);
    }
}
