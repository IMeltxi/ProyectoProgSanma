package domain;

public class Partido {
	private String nombrePart;
	private String fecha;
	private String hora;
	private String competicion;
	private String descripcion;
	
	
	public Partido(String nombrePart, String fecha, String hora, String competicion, String descripcion) {
		super();
		this.nombrePart = nombrePart;
		this.fecha = fecha;
		this.hora = hora;
		this.competicion = competicion;
		this.descripcion = descripcion;
	}


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
	
	
}
