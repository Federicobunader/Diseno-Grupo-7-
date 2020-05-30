package Negocio.Compra;

import java.util.ArrayList;

public abstract class Criterio {
    private String nombreCriterio;
    public abstract Presupuesto elegirPresupuesto(ArrayList<Presupuesto> presupuestos);

    public String getNombreCriterio() {
        return nombreCriterio;
    }
}
