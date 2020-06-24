package Negocio.Compras;

import java.util.Date;

public class Egreso {

    private Compra compra;
    private Date fechaDeOperacion;
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
}
