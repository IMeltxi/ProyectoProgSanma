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

    public Usuario(String nombre, String apellido, String tlf, String fechNacStr, 
                   String email, String contrasena, int numeroSocio) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.tlf = tlf;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        this.fechNac = LocalDate.parse(fechNacStr, formatter);

        this.email = email;
        this.contrasena = contrasena;
        this.numeroSocio = numeroSocio;
    }

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

    public int getNumeroSocio() {
        return numeroSocio;
    }

    public void setNumeroSocio(int numeroSocio) {
        this.numeroSocio = numeroSocio;
    }

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
}
