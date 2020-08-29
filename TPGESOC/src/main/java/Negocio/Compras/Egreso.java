package Negocio.Compras;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="egreso")
public class Egreso {

    @Id
    @GeneratedValue
    private int id;
    @Column
    private Compra compra;
    @Column(columnDefinition = "DATE")
    private Date fechaDeOperacion;
    @Column
    private Ingreso ingresoAsociado;

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

    GestorDeEgresos gestorDeEgresos = GestorDeEgresos.GetInstance();

    public void agregarCompraYPresupues(){
        gestorDeEgresos.registrarCompra(compra);
    }
}
