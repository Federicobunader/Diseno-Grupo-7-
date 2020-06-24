package Negocio.Compras;

public class Ingreso {

    public String descripcion;
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
