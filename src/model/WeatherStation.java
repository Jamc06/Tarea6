package model;

import model.interfaces.Measurable;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;


  //Estación meteorológica que mide temperatura, humedad y viento.
 
public class WeatherStation extends Device implements Measurable {
    private String site;
    private final Random rng = new Random();

    public WeatherStation(String id, String name, String manufacturer, double powerConsumption, String site) {
        super(id, name, manufacturer, powerConsumption);
        this.site = site;
    }

    public String getSite() { return site; }
    public void setSite(String site) { this.site = site; }

    @Override
    public Map<String, Double> readMeasurements() {
        Map<String, Double> m = new HashMap<>();
        m.put("temperature", -5 + rng.nextDouble() * 45); // °C
        m.put("humidity", rng.nextDouble() * 100); // %
        m.put("wind_speed", rng.nextDouble() * 30); // m/s
        return m;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" [WeatherStation at %s]", site);
    }
}
