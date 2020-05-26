package Negocio;

public class Item {

	private Producto producto;
	private int cantidad;

	public Item(){

	}

	public int valorTotal(){
		return producto.precio * cantidad;
	}
}//end Item