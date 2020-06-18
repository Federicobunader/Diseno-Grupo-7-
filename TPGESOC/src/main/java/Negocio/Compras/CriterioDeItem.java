package Negocio.Compras;

import java.util.ArrayList;

public class CriterioDeItem {

    private String nombreCriterio;
    private ArrayList<CategoriaItem> categorias = new ArrayList<CategoriaItem>();

    public CriterioDeItem(String nombreCriterio) {
        this.nombreCriterio = nombreCriterio;
       // categorias.stream().forEach(categoriaItem -> categoriaItem.setCriterioDeItem(this));
    }

    public String getNombreCriterio() {
        return nombreCriterio;
    }

    public ArrayList<CategoriaItem> getCategorias() {
        return categorias;
    }

    public void agregarCategoriaAlCriterio(CategoriaItem unaCategoria){
        categorias.add(unaCategoria);
    }
}
