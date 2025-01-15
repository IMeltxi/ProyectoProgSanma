package domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Compras {
    private Usuario user;
    private String partido;
    private String lado;
    private Set<String> listaAsientos;  // Cambiar a HashSet

    public Compras(Usuario user, String partido, String lado, ArrayList<String> asientos) {
        this.user = user;
        this.partido = partido;
        this.lado = lado;
        this.listaAsientos = new HashSet<>();  // Usar HashSet
    }
    
    public Usuario getUser() {
		return user;
	}

	public void setUser(Usuario user) {
		this.user = user;
	}

	public String getPartido() {
		return partido;
	}

	public void setPartido(String partido) {
		this.partido = partido;
	}

	public String getLado() {
		return lado;
	}

	public void setLado(String lado) {
		this.lado = lado;
	}

	public void setListaAsientos(Set<String> listaAsientos) {
		this.listaAsientos = listaAsientos;
	}

	public void añadirAsiento(String asiento) {
        this.listaAsientos.add(asiento);  // El HashSet evitará duplicados automáticamente
    }

    @Override
    public String toString() {
        return "Entrada: [user=" + user.getNombre() + ", partido=" + partido + ", lado=" + lado + ", asientos=" + listaAsientos + "]";
    }

    public Set<String> getListaAsientos() {
        return listaAsientos;
    }
}


