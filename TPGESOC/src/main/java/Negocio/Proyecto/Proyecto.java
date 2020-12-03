package Negocio.Proyecto;

import BaseDeDatos.EntidadPersistente;
import Negocio.Compras.Compra;
import Negocio.Compras.Egreso;
import Negocio.Compras.Ingreso;
import Negocio.Compras.Presupuesto;
import Negocio.Usuario.Usuario;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="proyecto")
public class Proyecto extends EntidadPersistente {

    @Column
    private double monto;
    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "director_responsable_id", referencedColumnName = "id")
    private Usuario directorResponsable;
    @Column
    private double montoDefinido;
    @Column
    private int cantidadPresupuestosExigibles;
    @OneToMany
    @JoinColumn(name = "proyecto_id", referencedColumnName = "id")
    private List<Ingreso> ingresos = new ArrayList<>();
    @Column
    private boolean fueValidado = false;

    public Proyecto(Usuario directorResponsable, double montoDefinido, int cantidadPresupuestosExigibles, List<Ingreso> ingresos) {
        this.directorResponsable = directorResponsable;
        this.montoDefinido = montoDefinido;
        this.cantidadPresupuestosExigibles = cantidadPresupuestosExigibles;
        this.ingresos = ingresos;
    }

    public Proyecto() {
    }

    public void agregarIngresoAProyecto(Ingreso ingreso){
        ingresos.add(ingreso);
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

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public double getMontoDefinido() {
        return montoDefinido;
    }

    public void setMontoDefinido(double montoDefinido) {
        this.montoDefinido = montoDefinido;
    }

    public boolean isFueValidado() {
        return fueValidado;
    }

    public void setFueValidado(boolean fueValidado) {
        this.fueValidado = fueValidado;
    }

    public double getMonto() {
        return monto;
    }

    public int getCantidadPresupuestosExigibles() {
        return cantidadPresupuestosExigibles;
    }

    public void setCantidadPresupuestosExigibles(int cantidadPresupuestosExigibles) {
        this.cantidadPresupuestosExigibles = cantidadPresupuestosExigibles;
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
