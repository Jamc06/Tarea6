package controller;

import model.*;
import model.interfaces.Actionable;
import model.interfaces.Measurable;
import model.interfaces.Loggable;
import view.ConsoleView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;


public class CatalogController {
    private final List<Device> catalog; // la lista polimórfica 
    private final ConsoleView view;

    public CatalogController(ConsoleView view) {
        this.view = view;
        this.catalog = new ArrayList<>();
        initCatalog();
    }

    
    //  Carga inicial  con al menos 10 equipos diversos.
     
    private void initCatalog() {
        // Agregar variedad de dispositivos (10)
        catalog.add(new SoilSensor("SS-001", "SoilSensor-A1", "AgroSense", 2.5, "Parcel A"));
        catalog.add(new SoilSensor("SS-002", "SoilSensor-A2", "GreenTech", 2.7, "Parcel B"));
        catalog.add(new WeatherStation("WS-001", "WeatherX-1", "MeteoCorp", 5.0, "Station North"));
        catalog.add(new WeatherStation("WS-002", "WeatherX-2", "MeteoCorp", 4.5, "Station South"));
        catalog.add(new IrrigationValve("IV-001", "Valve-1", "HydroFlow", 1.2, false));
        catalog.add(new IrrigationValve("IV-002", "Valve-2", "HydroFlow", 1.0, true));
        catalog.add(new Sprayer("SP-001", "Sprayer-Alpha", "SprayInc", 6.0, 20.0));
        catalog.add(new Sprayer("SP-002", "Sprayer-Beta", "SprayInc", 5.5, 15.0));
        catalog.add(new Drone("DR-001", "Drone-X1", "SkyFarm", 50.0, 88.0, "X1"));
        catalog.add(new Drone("DR-002", "Drone-X2", "SkyFarm", 55.0, 76.0, "X2"));

        
    }

    //inicia la vista
    public void run() {
        int option;
        do {
            option = view.showMainMenuAndGetChoice();
            switch (option) {
                case 1:
                    view.displayDevices(catalog);
                    break;
                case 2:
                    String id = view.promptForString("Ingrese ID del equipo a buscar:");
                    Optional<Device> foundById = findById(id);
                    view.displayDeviceDetail(foundById.orElse(null));
                    break;
                case 3:
                    String name = view.promptForString("Ingrese nombre (o parte) del equipo:");
                    List<Device> byName = findByName(name);
                    view.displayDevices(byName);
                    break;
                case 4:
                    List<Device> sorted = sortByConsumptionAscending();
                    view.displayDevices(sorted);
                    break;
                case 5:
                    // Demostración de contrato: ejecutar acción/lectura/log cuando corresponda
                    String eid = view.promptForString("Ingrese ID del equipo para operar:");
                    Optional<Device> devopt = findById(eid);
                    if (devopt.isPresent()) {
                        Device d = devopt.get();
                        // Si es measurable  muestra lecturas
                        if (d instanceof Measurable) {
                            view.displayMeasurements(((Measurable) d).readMeasurements());
                        } else {
                            view.displayMessage("Dispositivo no es medible.");
                        }
                        // Si es actionable pedi acción
                        if (d instanceof Actionable) {
                            String action = view.promptForString("Ingrese acción a ejecutar ( open, close, capture, spray):");
                            String result = ((Actionable) d).performAction(action);
                            view.displayMessage("Resultado: " + result);
                        } else {
                            view.displayMessage("Dispositivo no es accionable.");
                        }
                        // Si es loggable  registra
                        if (d instanceof Loggable) {
                            String log = ((Loggable) d).register();
                            view.displayMessage("Log: " + log);
                        } else {
                            view.displayMessage("Dispositivo no es registrable (Loggable).");
                        }
                    } else {
                        view.displayMessage("Equipo no encontrado.");
                    }
                    break;
                case 0:
                    view.displayMessage("Saliendo... Gracias.");
                    break;
                default:
                    view.displayMessage("Opción no reconocida.");
            }
        } while (option != 0);
    }

    public Optional<Device> findById(String id) {
        return catalog.stream().filter(d -> d.getId().equalsIgnoreCase(id)).findFirst();
    }

    public List<Device> findByName(String term) {
        String t = term.toLowerCase();
        List<Device> result = new ArrayList<>();
        for (Device d : catalog) {
            if (d.getName().toLowerCase().contains(t)) result.add(d);
        }
        return result;
    }

    public List<Device> sortByConsumptionAscending() {
        List<Device> copy = new ArrayList<>(catalog);
        Collections.sort(copy); // usa Comparable
        return copy;
    }


    public Optional<Device> findById(String id, boolean ignoreCase) {
        if (ignoreCase) return findById(id);
        return catalog.stream().filter(d -> d.getId().equals(id)).findFirst();
    }
}
