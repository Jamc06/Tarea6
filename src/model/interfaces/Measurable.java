package model.interfaces;

import java.util.Map;


///Contrato para dispositivos que pueden medir y devolver lecturas.
  //El mapa retorna pares "nombreMedida" -> valor.
 
public interface Measurable {
    /**
     * Lee mediciones actualmente disponibles del equipo (simuladas).
     * @return mapa con nombre de medición -> valor numérico
     */
    Map<String, Double> readMeasurements();
}
