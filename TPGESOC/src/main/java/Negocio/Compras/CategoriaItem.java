package Negocio.Compras;

import BaseDeDatos.EntidadPersistente;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="categoriaItem")
public class CategoriaItem extends EntidadPersistente {

    @Column
    private String criterioDeItem;
    @Column
    private String nombreCategoria;

    @OneToMany
    @JoinColumn(name="categoria_id", referencedColumnName = "id")
    private List<Presupuesto> presupuestos = new ArrayList<>();

    @ManyToMany(cascade =
            {
                    CascadeType.DETACH,
                    CascadeType.MERGE,
                    CascadeType.REFRESH,
                    CascadeType.PERSIST
            }, fetch = FetchType.LAZY)
    @JoinTable(name = "producto_x_categoria",joinColumns = @JoinColumn(name = "categoria_id"),inverseJoinColumns = @JoinColumn (name = "producto_id"))
    private List<Producto> productos = new ArrayList<Producto>();

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

    public List<Presupuesto> getPresupuestos() {
        return presupuestos;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void asociarProductoACategoria(Producto producto){
        productos.add(producto);
    }

    public void asociarPresupuestoACategoria(Presupuesto presupuesto){
        presupuestos.add(presupuesto);
    }
}
