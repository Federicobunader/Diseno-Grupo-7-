package Negocio.Compras;

import BaseDeDatos.EntidadPersistente;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="egreso")
public class Egreso  extends EntidadPersistente {

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "compra_id", referencedColumnName = "id")
    private Compra compra;

    @Column(columnDefinition = "DATE")
    private Date fechaDeOperacion;

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "ingreso_id", referencedColumnName = "id")
    private Ingreso ingresoAsociado;

    @Transient
    GestorDeEgresos gestorDeEgresos = GestorDeEgresos.GetInstance();

    public Egreso(Compra unaCompra, Date unaFechaDeOperacion) {
        this.compra = unaCompra;
        this.fechaDeOperacion = unaFechaDeOperacion;
    }

    public void asociarIngreso(Ingreso unIngreso){
        ingresoAsociado = unIngreso;
    }

    public Compra getCompra() {
        return compra;
    }

    public void agregarCompraYPresupues(){
        gestorDeEgresos.registrarCompra(compra);
    }

    public boolean estaEnElPeriodoAceptale(){
        return fechaDeOperacion.after(gestorDeEgresos.getFechaAcceptableDesde()) && fechaDeOperacion.before(gestorDeEgresos.getFechaAcceptableHasta());
    }
}
