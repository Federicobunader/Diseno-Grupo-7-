package Negocio.Compras;

import BaseDeDatos.EntidadPersistente;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="criterioDeItem")
public class CriterioDeItem extends EntidadPersistente {

    @Transient
    private ArrayList<Componente> criteriosDeMenorJerarquia = new ArrayList<Componente>();

    @Column
    private String nombreCriterio;

    @OneToMany(cascade = {CascadeType.ALL})
    @JoinColumn(name = "categoriaItem_id", referencedColumnName = "id")
    private List<CategoriaItem> categorias = new ArrayList<CategoriaItem>();

    public CriterioDeItem(String nombreCriterio) {
        this.nombreCriterio = nombreCriterio;
        // categorias.stream().forEach(categoriaItem -> categoriaItem.setCriterioDeItem(this));
    }

    public String getNombreCriterio() {
        return nombreCriterio;
    }

    public List<CategoriaItem> getCategorias() {
        return categorias;
    }

    public void agregarCategoriaAlCriterio(CategoriaItem unaCategoria){
        categorias.add(unaCategoria);
    }

}
