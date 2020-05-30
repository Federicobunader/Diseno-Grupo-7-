package Negocio.Compra.Criterios;

import Negocio.Compra.Criterio;
import Negocio.Compra.Presupuesto;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class MenorValor extends Criterio {

    private static MenorValor instance = null;
    String nombreCriterio= "Menor valor";

    public static MenorValor GetInstance() {
        if (instance == null)
            instance = new MenorValor();
        return instance;
    }

    public String getNombreCriterio() {
        return nombreCriterio;
    }

    public Presupuesto elegirPresupuesto(ArrayList<Presupuesto> presupuestos) {
        double presupuestoMasBarato = this.menorValor(presupuestos);

        for (int i = 0; i < presupuestos.size(); i++) {
            if (presupuestos.get(i).valorTotal() == presupuestoMasBarato) {
                return presupuestos.get(i);
            }
        }
        return null;
    }

    private double menorValor(ArrayList<Presupuesto> presupuestos) {
        return this.valoresTotales(presupuestos).stream().min(Comparator.naturalOrder()).get();
    }

    private List<Double> valoresTotales(ArrayList<Presupuesto> presupuestos){
        return presupuestos.stream().map(presupuesto -> presupuesto.valorTotal()).collect(Collectors.toList());
    }
}
