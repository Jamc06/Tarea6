package model;

import model.interfaces.Loggable;


public class DataLogger implements Loggable {
    private final String loggerId;

    public DataLogger(String loggerId) {
        this.loggerId = loggerId;
    }

    public String getLoggerId() { return loggerId; }

    @Override
    public String register() {
        return "DataLogger " + loggerId + " registered an entry.";
    }
}
