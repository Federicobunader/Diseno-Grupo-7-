package Negocio;

import Negocio.Compras.Ingreso;
import Negocio.Compras.Presupuesto;
import Negocio.Usuario.Usuario;

import java.util.List;

public class Proyecto {
    private double monto;
    private Usuario directorResponsable;
    private List<Ingreso> ingresos;
    private Presupuesto presupuesto;

    public Proyecto(double monto, Usuario directorResponsable, List<Ingreso> ingresos, Presupuesto presupuesto) {
        this.monto = monto;
        this.directorResponsable = directorResponsable;
        this.ingresos = ingresos;
        this.presupuesto = presupuesto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public void setDirectorResponsable(Usuario directorResponsable) {
        this.directorResponsable = directorResponsable;
    }

    public void setIngresos(List<Ingreso> ingresos) {
        this.ingresos = ingresos;
    }

    public void setPresupuesto(Presupuesto presupuesto) {
        this.presupuesto = presupuesto;
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

    public Presupuesto getPresupuesto() {
        return presupuesto;
    }


}
