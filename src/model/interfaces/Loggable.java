package model.interfaces;


//Contrato para dispositivos que pueden registrar / loggear algún evento o estado.
 
public interface Loggable {
    /**
     * Registra  una entrada de log del dispositivo.
     * @return texto con el detalle registrado
     */
    String register();
}
