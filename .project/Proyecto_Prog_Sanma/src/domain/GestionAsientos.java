package domain;

import java.util.HashMap;
import java.util.Map;

public class GestionAsientos {
    private Map<String, Boolean> asientos;

    public GestionAsientos() {
        this.asientos = new HashMap<>();
    }

    public void inicializarAsientos(int cantidadPorBloque) {
        String[] bloques = {"NORTE", "SUR", "ESTE", "PRINCIPAL"};
        for (String bloque : bloques) {
            for (int i = 1; i <= cantidadPorBloque; i++) {
                String asientoId = bloque + "-A" + i;
                asientos.put(asientoId, false); // false = disponible
            }
        }
    }

    public boolean verificarDisponibilidad(String asiento) {
        return asientos.containsKey(asiento) && !asientos.get(asiento);
    }

    public boolean reservarAsiento(String asiento) {
        if (verificarDisponibilidad(asiento)) {
            asientos.put(asiento, true); // true es  reservado
            return true;
        }
        return false;
    }

    public void liberarAsiento(String asiento) {
        if (asientos.containsKey(asiento)) {
            asientos.put(asiento, false); // false es que esta disponible
        }
    }
}
