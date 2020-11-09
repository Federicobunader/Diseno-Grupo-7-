package Negocio.Usuario;

import BaseDeDatos.EntidadPersistente;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name="mensaje")
public class Mensaje extends EntidadPersistente{
    @Column
    String contenido;

    @Column(name = "fechaDelMensaje", columnDefinition = "DATE")
    private LocalDate fechaDelMensaje;

    public Mensaje() {
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public String getContenido() {
        return contenido;
    }

    public LocalDate getFechaDelMensaje() {
        return fechaDelMensaje;
    }

    public void setFechaDelMensaje(LocalDate fechaDelMensaje) {
        this.fechaDelMensaje = fechaDelMensaje;
    }
}
