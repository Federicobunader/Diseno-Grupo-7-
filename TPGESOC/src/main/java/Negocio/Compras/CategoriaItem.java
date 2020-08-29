package Negocio.Compras;

import javax.persistence.*;

@Entity
@Table(name="categoriaItem")
public class CategoriaItem {
    @Id
    @GeneratedValue
    private int id;
    @Column
    private String criterioDeItem;
    @Column
    private String nombreCategoria;

    public CategoriaItem(String criterioDeItem, String nombreCategoria) {
        this.criterioDeItem = criterioDeItem;
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
