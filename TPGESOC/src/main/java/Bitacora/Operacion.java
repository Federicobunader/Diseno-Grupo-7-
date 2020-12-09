package Bitacora;

import BaseDeDatos.EntidadPersistente;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="operacion")
public class Operacion extends EntidadPersistente {
    @Column
    private String tipoDeOperacion;
    @Column
    private String entidad;
    @Column
    private String fechaDeOperacion;


    public Operacion(String tipoDeOperacion, String entidad, String fechaDeOperacion) {
        this.tipoDeOperacion = tipoDeOperacion;
        this.entidad = entidad;
        this.fechaDeOperacion = fechaDeOperacion;

    }

    public Operacion() {
    }

    public String getTipoDeOperacion() {
        return tipoDeOperacion;
    }

    public void setTipoDeOperacion(String tipoDeOperacion) {
        this.tipoDeOperacion = tipoDeOperacion;
    }

    public String getEntidad() {
        return entidad;
    }

    public void setEntidad(String entidad) {
        this.entidad = entidad;
    }

    public String getFechaDeOperacion() {
        return fechaDeOperacion;
    }

    public void setFechaDeOperacion(String fechaDeOperacion) {
        this.fechaDeOperacion = fechaDeOperacion;
    }
}
