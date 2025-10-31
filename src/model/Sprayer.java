package model;

import model.interfaces.Actionable;


public class Sprayer extends Device implements Actionable {
    private double tankVolume; // litros

    public Sprayer(String id, String name, String manufacturer, double powerConsumption, double tankVolume) {
        super(id, name, manufacturer, powerConsumption);
        this.tankVolume = tankVolume;
    }

    public double getTankVolume() { return tankVolume; }
    public void setTankVolume(double tankVolume) { this.tankVolume = tankVolume; }

    @Override
    public String performAction(String action) {
        
        String lower = action.toLowerCase().trim();
        if (lower.startsWith("spray")) {
            return "Sprayer executed spraying action. (" + action + ")";
        }
        return "Action not supported by Sprayer.";
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" [Sprayer - tank %.2f L]", tankVolume);
    }
}
