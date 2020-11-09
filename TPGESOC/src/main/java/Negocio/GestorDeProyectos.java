package Negocio;

import Negocio.Compras.GestorDeCriterios;

public class GestorDeProyectos {

    private static GestorDeProyectos instance = null;

    public static GestorDeProyectos GetInstance() {
        if (instance == null)
            instance = new GestorDeProyectos();
        return instance;
    }


}
