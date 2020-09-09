package Negocio.Compras;

import BaseDeDatos.EntidadPersistente;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="ingreso")
public class Ingreso  extends EntidadPersistente {

    @Column
    public String descripcion;
    @Column
    public double montoTotal;

    public Ingreso(String descripcion, double montoTotal) {
        this.descripcion = descripcion;
        this.montoTotal = montoTotal;
    }

    public void asociarseAEgreso(Egreso unEgreso){
        unEgreso.asociarIngreso(this);
    }

    public String getDescripcion() {
        return descripcion;
    }

    public double getMontoTotal() {
        return montoTotal;
    }
}
