package Negocio.Usuario;

import BaseDeDatos.EntidadPersistente;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="mensaje")
public class Mensaje extends EntidadPersistente{
    @Column
    String contenido;

    public Mensaje() {
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }
}
