package Negocio;


import Negocio.Entidad.Empresa.TipoDeEmpresas.TiposDeEmpresas.MedianaTramo2;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Validador {

    Validador validador = Validador.GetInstance();

    int cantidadDePresupuestosRequeridos;

    private static Validador instance = null;

    public static Validador GetInstance() {
        if (instance == null)
            instance = new Validador();
        return instance;
    }

    public void validarPresupuestosTotales(Compra unaCompra){
        if(unaCompra.cantidadPresupuestos() != cantidadDePresupuestosRequeridos) {
            System.out.println("No se tiene la cantidad de presupuestos requeridos.");
        }
    }

    public void validarSiSeUtilizoPresupuesto(Compra unaCompra){
        if(unaCompra.getPresupuestos().stream().anyMatch(presupuesto -> presupuesto.valorTotal() == unaCompra.valorTotal())){
            System.out.println("No se utilizo ningún presupuesto.");
        }
    }

    public void validarEleccionCorrecta(Compra unaCompra){
        MenorValor menorValor = MenorValor.GetInstance();

        if(unaCompra.getCriterioEleccionPresupuesto() == menorValor){
            List<Double> valoresTotales = unaCompra.getPresupuestos().stream().map(presupuesto -> presupuesto.valorTotal()).collect(Collectors.toList());
            if(valoresTotales.stream().min(Comparator.naturalOrder()).get() != unaCompra.valorTotal()){
                System.out.println("No se eligió el presupuesto de menor valor.");
            }
        }
    }

    private void validarPresupuestoCorrespondeAUnaCompra(Compra unaCompra, Presupuesto unPresupuesto){
        // HACER UN GESTOR DE COMPRAS, QUE TENGA UNA LISTA DE COMPRAS ENTRE OTRAS COSAS.
    }


}
