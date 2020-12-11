package Negocio;

import BaseDeDatos.EntidadPersistente;
import Negocio.Compras.Producto;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="proveedor")
public class Proveedor extends EntidadPersistente {

	@Column
	private String nombre;
	@Column
	private int numeroID;

	@OneToMany(mappedBy = "proveedor",cascade = {CascadeType.ALL})
	private List<Producto> productos;

	public Proveedor(String nombre, int numeroID) {
		this.nombre = nombre;
		this.numeroID = numeroID;
	}

	public Proveedor() {
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setNumeroID(int numeroID) {
		this.numeroID = numeroID;
	}

	public String getNombre() {
		return nombre;
	}

	public int getNumeroID() {
		return numeroID;
	}

	public List<Producto> getProductos() {
		return productos;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}
}//end Proveedor