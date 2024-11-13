package domain;

public class Entradas implements descontable, sociable {
	private String partido;
	
	public enum TipoEntrada {
		GENERAL, SOCIOS, GAZTEABONO, VIP
	}
	private enum Localidad {
		NORTE, SUR, ESTE, PRINCIPAL
	}
	private TipoEntrada tipoEntrada;
	private Localidad localidad;
	private String bloque;
	private String asiento;
	private float precio;
	
	private String nombre;
	private String apellido;
	private String email;
	
	private String contraseña;
	public Entradas() {
		
	}
	
	
	public Entradas(String partido, TipoEntrada tipoEntrada, Localidad localidad, String bloque, String asiento,
			float precio, String nombre, String apellido, String email, String contraseña) {
		super();
		this.partido = partido;
		this.tipoEntrada = tipoEntrada;
		this.localidad = localidad;
		this.bloque = bloque;
		this.asiento = asiento;
		this.precio = precio;
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.contraseña = contraseña;
	}


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
	
	@Override
	public String toString() {
		return "Entradas [tipoEntrada=" + tipoEntrada + ", localidad=" + localidad + ", bloque=" + bloque + ", asiento="
				+ asiento + ", precio=" + precio + "]";
	}

	
	@Override
	public boolean esDescontable() {
		/**
		 * Descontar el precio a las entradas si son socios del club.
		 * */
		return false;
	}


	@Override
	public boolean esSocio() {
		/**
		 * Metodo para comprobar si son socios.
		 * */
		return false;
	}
	public TipoEntrada[] getTipoEntradas() {
		return TipoEntrada.values();
	}
	
	
	
	
}
