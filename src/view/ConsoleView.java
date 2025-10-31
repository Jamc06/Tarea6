package view;

import model.Device;

import java.util.List;
import java.util.Map;
import java.util.Scanner;


public class ConsoleView {
    private final Scanner scanner;

    public ConsoleView() {
        scanner = new Scanner(System.in);
    }

    public int showMainMenuAndGetChoice() {
        System.out.println("\nCatálogo de Equipos - Menú ");
        System.out.println("1) Listar todos los equipos");
        System.out.println("2) Buscar equipo por ID");
        System.out.println("3) Buscar equipo por nombre");
        System.out.println("4) Ordenar catálogo por consumo eléctrico (asc)");
        System.out.println("5) Operar / mostrar contratos de un equipo (medir/accionar/log)");
        System.out.println("0) Salir");
        System.out.print("Seleccione una opción: ");
        String line = scanner.nextLine();
        try {
            return Integer.parseInt(line.trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public String promptForString(String prompt) {
        System.out.print(prompt + " ");
        return scanner.nextLine().trim();
    }

    public void displayDevices(List<Device> list) {
        if (list == null || list.isEmpty()) {
            System.out.println("No hay dispositivos para mostrar.");
        } else {
            System.out.println("\n Lista de Dispositivos (" + list.size() + ") ");
            for (Device d : list) {
                System.out.println(d.toString());
            }
        }
    }

    public void displayDeviceDetail(Device d) {
        if (d == null) {
            System.out.println("Dispositivo no encontrado.");
            return;
        }
        System.out.println("\n Detalle del Dispositivo ");
        System.out.println("ID: " + d.getId());
        System.out.println("Nombre: " + d.getName());
        System.out.println("Fabricante: " + d.getManufacturer());
        System.out.printf("Consumo: %.2f W\n", d.getPowerConsumption());
        System.out.println("Tipo: " + d.getClass().getSimpleName());
        System.out.println("Info adicional: " + d.toString());
    }

    public void displayMeasurements(Map<String, Double> measurements) {
        if (measurements == null || measurements.isEmpty()) {
            System.out.println("No hay mediciones disponibles.");
            return;
        }
        System.out.println("\n Mediciones ");
        for (Map.Entry<String, Double> e : measurements.entrySet()) {
            System.out.printf("%s : %.2f\n", e.getKey(), e.getValue());
        }
    }

    public void displayMessage(String msg) {
        System.out.println(msg);
    }
}
