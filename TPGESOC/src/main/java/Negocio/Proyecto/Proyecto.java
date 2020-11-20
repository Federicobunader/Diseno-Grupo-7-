package Negocio;

import Negocio.Compras.Compra;
import Negocio.Compras.Egreso;
import Negocio.Compras.Ingreso;
import Negocio.Compras.Presupuesto;
import Negocio.Usuario.Usuario;

import java.util.List;

public class Proyecto {
    private double monto;
    private Usuario directorResponsable;
    private double montoDefinido;
    private int cantidadPresupuestosExigibles;
    private List<Ingreso> ingresos;
    private boolean fueValidado = false;

    public Proyecto(Usuario directorResponsable, double montoDefinido, int cantidadPresupuestosExigibles, List<Ingreso> ingresos) {
        this.directorResponsable = directorResponsable;
        this.montoDefinido = montoDefinido;
        this.cantidadPresupuestosExigibles = cantidadPresupuestosExigibles;
        this.ingresos = ingresos;
    }

    public void setMonto() {

        double montoTotal = 0;
        for(int i = 0; i < ingresos.size(); i++){
         montoTotal += ingresos.get(i).getMontoTotal();
        }

        monto = montoTotal;
    }

    public void setDirectorResponsable(Usuario directorResponsable) {
        this.directorResponsable = directorResponsable;
    }

    public void setIngresos(List<Ingreso> ingresos) {
        this.ingresos = ingresos;
    }

    public double getMonto() {
        return monto;
    }

    public Usuario getDirectorResponsable() {
        return directorResponsable;
    }

    public List<Ingreso> getIngresos() {
        return ingresos;
    }

    public void validar() {
        if (!fueValidado) {
            for (int i = 0; i < ingresos.size(); i++) {
                int cantEgresos = ingresos.get(i).getEgresosVinculados().size();
                for (int j = 0; j < cantEgresos; j++) {
                    Compra compra = ingresos.get(i).getEgresosVinculados().get(j).getCompra();
                    compra.validar(montoDefinido, cantidadPresupuestosExigibles);
                    compra.setPerteneceAProyecto(true);
                }
            }
            fueValidado = true;
        }
    }
}
