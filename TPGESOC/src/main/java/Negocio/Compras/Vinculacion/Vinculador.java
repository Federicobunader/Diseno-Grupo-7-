package Negocio.Compras.Vinculacion;

import Negocio.Compras.Ingreso;

import java.util.ArrayList;

public class Vinculador{

    private static Vinculador instance = null;

    public static Vinculador GetInstance() {
        if (instance == null)
            instance = new Vinculador();
        return instance;
    }

}
