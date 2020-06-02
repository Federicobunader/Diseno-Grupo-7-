package Negocio.Compras;

public class Item {

	private Producto producto;
	private int cantidad;

	public Item(Producto producto, int cantidad) {
		this.producto = producto;
		this.cantidad = cantidad;
	}

	public int valorTotal(){
		return producto.precio * cantidad;
	}
}//end Item