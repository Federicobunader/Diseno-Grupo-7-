package Negocio.Compras;

import BaseDeDatos.EntidadPersistente;
import Negocio.Proveedor;

import javax.persistence.*;

@Entity
@Table(name="producto")
public class Producto  extends EntidadPersistente {

	@Column
	private int precio;
	@OneToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name = "proveedor_id", referencedColumnName = "id")
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