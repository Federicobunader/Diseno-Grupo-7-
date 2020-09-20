package Negocio;

import BaseDeDatos.EntidadPersistente;

import javax.persistence.*;

@Entity
@Table(name="proveedor")
public class Proveedor extends EntidadPersistente {

	@Column
	private String nombre;
	@Column
	private int numeroID;

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

	public void finalize() throws Throwable {

	}
}//end Proveedor