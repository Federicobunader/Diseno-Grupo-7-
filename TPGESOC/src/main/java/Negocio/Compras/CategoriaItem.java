package Negocio.Compras;

public class CategoriaItem {

    private String criterioDeItem;
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
