package domain;

public class Partido {
	private String nombrePart;
	private String fecha;
	private String hora;
	private Integer jornada;
	
	
	public Partido(String nombrePart, String fecha, String hora, Integer jornada) {
		super();
		this.nombrePart = nombrePart;
		this.fecha = fecha;
		this.hora = hora;
		this.jornada = jornada;
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



	@Override
	public String toString() {
		return "Partido [nombrePart=" + nombrePart + ", fecha=" + fecha + ", hora=" + hora + ", competicion="
			 + ", descripcion=" + "]";
	}


	public Integer getJornada() {
		return jornada;
	}


	public void setJornada(Integer jornada) {
		this.jornada = jornada;
	}



	
	
}