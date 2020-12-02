package Negocio.Proyecto;

import Negocio.Compras.GestorDeCriterios;
import Negocio.Proyecto.*;

import java.util.ArrayList;
import java.util.List;

public class GestorDeProyectos {

    private static GestorDeProyectos instance = null;
    private List<Proyecto> proyectos = new ArrayList<>();

    public void setProyectos(List<Proyecto> proyectos) {
        this.proyectos = proyectos;
    }

    public List<Proyecto> getProyectos() {
        return proyectos;
    }

    public static GestorDeProyectos GetInstance() {
        if (instance == null)
            instance = new GestorDeProyectos();
        return instance;
    }






}
