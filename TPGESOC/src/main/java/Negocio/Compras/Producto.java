package Negocio.Compras;

import Negocio.Proveedor;

import javax.persistence.*;

@Entity
@Table(name="producto")
public class Producto {

	@Id
	@GeneratedValue
	private int id;
	@Column
	private int precio;
	private Proveedor proveedor;
	@Column
	private String descripcion;

	public int getPrecio() {
		return precio;
	}

	public Proveedor getProveedor() {
		return proveedor;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public Producto(int precio, Proveedor proveedor, String descripcion) {
		this.precio = precio;
		this.proveedor = proveedor;
		this.descripcion = descripcion;
	}

	public Producto(){

	}

	public void finalize() throws Throwable {

	}
}//end Producto