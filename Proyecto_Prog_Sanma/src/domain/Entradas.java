package domain;

public class Entradas{
	private String partido;
	
	public enum TipoEntrada {
		GENERAL, SOCIOS, GAZTEABONO, VIP
	}
	public enum Localidad {
		NORTE, SUR, ESTE, PRINCIPAL
	}
	private TipoEntrada tipoEntrada;
	private Localidad localidad;
	private String bloque;
	private String asiento;
	private float precio;
	
	public Entradas() {
		
	}
	
	// Constructor completo para inicializar todos los atributos
	public Entradas(String partido, TipoEntrada tipoEntrada, Localidad localidad, String bloque, String asiento,
			float precio, String nombre, String apellido, String email, String contraseña) {
		super(); // Llama al constructor de la clase base (en este caso, Object)
		this.partido = partido;
		this.tipoEntrada = tipoEntrada;
		this.localidad = localidad;
		this.bloque = bloque;
		this.asiento = asiento;
		this.precio = precio;
	}

	// Métodos getter y setter para acceder y modificar los atributos
	public String getPartido() {
		return partido;
	}
	public void setPartido(String partido) {
		this.partido = partido;
	}
	public TipoEntrada getTipoEntrada() {
		return tipoEntrada;
	}
	public void setTipoEntrada(TipoEntrada tipoEntrada) {
		this.tipoEntrada = tipoEntrada;
	}
	public Localidad getLocalidad() {
		return localidad;
	}
	public void setLocalidad(Localidad localidad) {
		this.localidad = localidad;
	}
	public String getBloque() {
		return bloque;
	}
	public void setBloque(String bloque) {
		this.bloque = bloque;
	}
	public String getAsiento() {
		return asiento;
	}
	public void setAsiento(String asiento) {
		this.asiento = asiento;
	}
	public float getPrecio() {
		return precio;
	}
	public void setPrecio(float precio) {
		if(tipoEntrada.equals(TipoEntrada.GENERAL)) {
			precio=60;
		}else if(tipoEntrada.equals(TipoEntrada.SOCIOS)) {
			precio=50;
		}else if(tipoEntrada.equals(TipoEntrada.GAZTEABONO)) {
			precio=30;
		}else if(tipoEntrada.equals(TipoEntrada.VIP)) {
			precio=150;
		};
	}
	

	public boolean reservarAsiento(String bloque, String asiento) {
	    if (this.bloque.equals(bloque) && this.asiento.equals(asiento)) {
	        if (this.asiento == null || this.asiento.isEmpty()) {
	            this.asiento = asiento; 
	            return true;
	        }
	    }
	    return false;
	}

	public void liberarAsiento() {
	    this.asiento = null; // Liberar asiento
	}
	

	
	@Override
	public String toString() {
		return "Entradas [tipoEntrada=" + tipoEntrada + ", localidad=" + localidad + ", bloque=" + bloque + ", asiento="
				+ asiento + ", precio=" + precio + "]";
	}

	
	



	public TipoEntrada[] getTipoEntradas() {
		return TipoEntrada.values();
	}
	
	
	
	
}
