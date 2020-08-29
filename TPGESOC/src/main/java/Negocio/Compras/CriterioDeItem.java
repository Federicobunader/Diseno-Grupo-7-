package Negocio.Compras;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.ArrayList;

@Entity
@Table(name="criterioDeItem")
public class CriterioDeItem {

    private ArrayList<Componente> criteriosDeMenorJerarquia = new ArrayList<Componente>();

    @Column
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
