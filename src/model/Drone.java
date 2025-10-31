package model;

import model.interfaces.Actionable;
import model.interfaces.Loggable;
import model.interfaces.Measurable;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;


 //Medir su batería (Measurable)
 
public class Drone extends Device implements Measurable, Actionable, Loggable {
    private double batteryLevel; // % (0-100)
    private String model;
    private final Random rng = new Random();

    public Drone(String id, String name, String manufacturer, double powerConsumption, double batteryLevel, String model) {
        super(id, name, manufacturer, powerConsumption);
        this.batteryLevel = batteryLevel;
        this.model = model;
    }

    public double getBatteryLevel() { return batteryLevel; }
    public void setBatteryLevel(double batteryLevel) { this.batteryLevel = batteryLevel; }
    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }

    @Override
    public Map<String, Double> readMeasurements() {
        Map<String, Double> m = new HashMap<>();
        // batería y altitud simnulada
        m.put("battery", batteryLevel);
        m.put("altitude", 5 + rng.nextDouble() * 95); // metros
        return m;
    }

    @Override
public String performAction(String action) {
    String lower = action.toLowerCase().trim();

    switch (lower) {
        case "open":
            return "Drone camera opened.";
        case "close":
            return "Drone camera closed.";
        case "capture":
            batteryLevel = Math.max(0, batteryLevel - 2.5);
            return "Drone captured multispectral images.";
        case "spray":
            batteryLevel = Math.max(0, batteryLevel - 5.0);
            return "Drone performed spraying action.";
        case "return":
            batteryLevel = Math.max(0, batteryLevel - 1.0);
            return "Drone returning to base.";
        default:
            return "Action not supported by Drone.";
    }
}

    @Override
    public String register() {
        return String.format("Drone %s logged at battery %.2f%%.", getId(), batteryLevel);
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" [Drone model %s - battery %.2f%%]", model, batteryLevel);
    }
}
