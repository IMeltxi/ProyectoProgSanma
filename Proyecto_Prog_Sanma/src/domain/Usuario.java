package domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Usuario implements Serializable {
    public enum tipoSocio {
        SOCIOMENSUAL, SOCIO, VIP, GAZTEABONO
    }

    private tipoSocio tiposocio;
    private String nombre;
    private String apellido;
    private String tlf;
    private LocalDate fechNac;
    private String email;
    private String contrasena;
    private int numeroSocio;
    // Constructor completo para inicializar todos los atributos del usuario
    public Usuario(tipoSocio tiposocio,String nombre, String apellido, String tlf, String fechNacStr, 
                   String email, String contrasena) {
    	this.tiposocio=tiposocio;
        this.nombre = nombre;
        this.apellido = apellido;
        this.tlf = tlf;
        this.fechNac = LocalDate.parse(fechNacStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        this.email = email;
        this.contrasena = contrasena;
    }
    // Métodos getter y setter para acceder y modificar los atributos
    public tipoSocio getTiposocio() {
        return tiposocio;
    }

    public void setTiposocio(tipoSocio tiposocio) {
        this.tiposocio = tiposocio;
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
        return fechNac.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
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

    public int getNumeroSocio() {
        return numeroSocio;
    }

    public void setNumeroSocio(int numeroSocio) {
        this.numeroSocio = numeroSocio;
    }
    // Sobrescribe el método toString para representar al usuario como texto
    @Override
    public String toString() {
        return "Usuario [nombre=" + nombre + ", apellido=" + apellido + ", tlf=" + tlf + 
                ", fechNac=" + fechNac + ", email=" + email + ", contrasena=" + contrasena + ", numeroSocio=" + numeroSocio + "]";
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
        return Objects.equals(apellido, other.apellido) && Objects.equals(email, other.email)
                && Objects.equals(tlf, other.tlf);
    }
    public boolean verificarContrasena(char[] contrasenaIngresada) {
        return new String(contrasenaIngresada).equals(this.contrasena);
    }

    
}
