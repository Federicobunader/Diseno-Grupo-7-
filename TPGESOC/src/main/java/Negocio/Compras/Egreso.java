package Negocio.Compras;

import java.util.Date;

public class Egreso {

    private Compra compra;
    private Date fechaDeOperacion;

    public Egreso(Compra unaCompra, Date unaFechaDeOperacion) {
        this.compra = unaCompra;
        this.fechaDeOperacion = unaFechaDeOperacion;
    }

    }
