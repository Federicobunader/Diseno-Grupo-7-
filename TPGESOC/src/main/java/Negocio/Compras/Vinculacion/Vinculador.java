package Negocio.Compras.Vinculacion;

import Negocio.Compras.Ingreso;

import java.util.ArrayList;

public class Vinculador{

    private CriterioDeVinculacion criterioDeVinculacion;

    private static Vinculador instance = null;

    public static Vinculador GetInstance() {
        if (instance == null)
            instance = new Vinculador();
        return instance;
    }

    public void vincularIngresoAEgreso(Ingreso unIngreso){
        criterioDeVinculacion.vincular(unIngreso);
    }
}
