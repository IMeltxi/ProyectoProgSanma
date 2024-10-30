package domain;

import java.io.Serializable;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Usuario implements Serializable{
	private String nombre;
	private String apellido;
	private String tlf;
	private LocalDate fechNac;
	private String email;
	private String contrasena;
	private int numeroSocio;
	
	
	public Usuario(String nombre, String apellido, String tlf, String fechNac, 
			String email, String contrasena, int numeroSocio) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.tlf = tlf;
		this.fechNac = LocalDate.parse(fechNac,DateTimeFormatter.ofPattern("dd-MM-yyyy"));
		this.email = email;
		this.contrasena = contrasena;
		this.numeroSocio = numeroSocio;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getApellido() {
		return apellido;
	}


	public void setApellido(String apellido) {
		this.apellido = apellido;
	}


	public String getTlf() {
		return tlf;
	}


	public void setTlf(String tlf) {
		this.tlf = tlf;
	}


	public LocalDate getFechNac() {
		return fechNac;
	}
	
	public String getFechNacStr() {
		return fechNac.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
	}


	public void setFechNac(LocalDate fechNac) {
		this.fechNac = fechNac;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getContrasena() {
		return contrasena;
	}


	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}
	
	public int getNumeroUsuario() {
		return numeroSocio;
	}
	
	public void setNumeroUsuario() {
		this.numeroSocio = numeroSocio;
	}

	@Override
	public String toString() {
		return "Usuario [nombre=" + nombre + ", apellido=" + apellido + ", tlf=" + tlf + 
				", fechNac=" + fechNac+ ", email=" + email + ", contrasena=" + contrasena + ", numeroSocio="+ numeroSocio + "]";
	}


	@Override
	public int hashCode() {
		return Objects.hash(email);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		return Objects.equals(apellido, other.apellido)&& Objects.equals(email, other.email)
				&& Objects.equals(tlf, other.tlf);
	}
	

	
	
	
	
	
	
	
	

}
