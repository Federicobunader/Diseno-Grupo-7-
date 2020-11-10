package Negocio.Compras;

import BaseDeDatos.EntidadPersistente;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="categoriaItem")
public class CategoriaItem extends EntidadPersistente {

    @Column
    private String criterioDeItem;
    @Column
    private String nombreCategoria;

    public CategoriaItem(String criterioDeItem, String nombreCategoria) {
        this.criterioDeItem = criterioDeItem;
        this.nombreCategoria = nombreCategoria;
    }

    public CategoriaItem() {
    }

    public void setNombreCategoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }

    public String getCriterioDeItem() {
        return criterioDeItem;
    }

    public void setCriterioDeItem(String criterioDeItem) {
        this.criterioDeItem = criterioDeItem;
    }

    public String getNombreCategoria() {
        return nombreCategoria;
    }
}
