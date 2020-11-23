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
    private List<Egreso> egresos = new ArrayList<>();

    @OneToMany
    @JoinColumn(name="categoria_id", referencedColumnName = "id")
    private List<Presupuesto> presupuestos = new ArrayList<>();

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

    public List<Egreso> getEgresos() {
        return egresos;
    }

    public List<Presupuesto> getPresupuestos() {
        return presupuestos;
    }
}
