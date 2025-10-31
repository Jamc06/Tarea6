package model.interfaces;


// Contrato para dispositivos que pueden ejecutar acciones (abrir, cerrar, capturar).
 
public interface Actionable {
    /**
     * Ejecuta una acción soportada por el dispositivo.
     * @param action nombre de la acción a ejecutar
     * @return  resultado descriptivo de la ejecución
     */
    String performAction(String action);
}
