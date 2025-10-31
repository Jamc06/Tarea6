package model;

import model.interfaces.Actionable;

// se puede abrir o cerrar 
public class IrrigationValve extends Device implements Actionable {
    private boolean open;

    public IrrigationValve(String id, String name, String manufacturer, double powerConsumption, boolean open) {
        super(id, name, manufacturer, powerConsumption);
        this.open = open;
    }

    public boolean isOpen() { return open; }
    public void setOpen(boolean open) { this.open = open; }

    @Override
    public String performAction(String action) {
        // Se soportan "open" y "close" .
        switch (action.toLowerCase()) {
            case "open":
            case "abrir":
                if (open) return "Valve already open.";
                open = true;
                return "Valve opened.";
            case "close":
            case "cerrar":
                if (!open) return "Valve already closed.";
                open = false;
                return "Valve closed.";
            default:
                return "Action not supported by IrrigationValve.";
        }
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" [IrrigationValve - %s]", open ? "OPEN" : "CLOSED");
    }
}
