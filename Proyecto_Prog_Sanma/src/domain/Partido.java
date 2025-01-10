package domain;

public class Partido {
	private String nombrePart;
	private String fecha;
	private String hora;
	private String competicion;
	private String descripcion;
	private Integer Jornada;
	
	// Constructor completo para inicializar todos los atributos del partido
	public Partido(String nombrePart, String fecha, String hora, String competicion, String descripcion) {
		super();
		this.nombrePart = nombrePart;
		this.fecha = fecha;
		this.hora = hora;
		this.competicion = competicion;
		this.descripcion = descripcion;
	}

	 // Métodos getter y setter para acceder y modificar los atributos
	public String getNombrePart() {
		return nombrePart;
	}


	public void setNombrePart(String nombrePart) {
		this.nombrePart = nombrePart;
	}


	public String getFecha() {
		return fecha;
	}


	public void setFecha(String fecha) {
		this.fecha = fecha;
	}


	public String getHora() {
		return hora;
	}


	public void setHora(String hora) {
		this.hora = hora;
	}


	public String getCompeticion() {
		return competicion;
	}


	public void setCompeticion(String competicion) {
		this.competicion = competicion;
	}


	public String getDescripcion() {
		return descripcion;
	}


	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	@Override
	public String toString() {
		return "Partido [nombrePart=" + nombrePart + ", fecha=" + fecha + ", hora=" + hora + ", competicion="
				+ competicion + ", descripcion=" + descripcion + "]";
	}


	public Integer getJornada() {
		return Jornada;
	}


	public void setJornada(Integer jornada) {
		Jornada = jornada;
	}
	
	
}
