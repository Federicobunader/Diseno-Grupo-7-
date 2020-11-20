package Negocio;

import Negocio.Compras.GestorDeCriterios;
import Negocio.Proyecto.*;

import java.util.List;

public class GestorDeProyectos {

    private static GestorDeProyectos instance = null;
    private List<Negocio.Proyecto> proyectos;

    public void setProyectos(List<Negocio.Proyecto> proyectos) {
        this.proyectos = proyectos;
    }

    public List<Negocio.Proyecto> getProyectos() {
        return proyectos;
    }

    public static GestorDeProyectos GetInstance() {
        if (instance == null)
            instance = new GestorDeProyectos();
        return instance;
    }


}
