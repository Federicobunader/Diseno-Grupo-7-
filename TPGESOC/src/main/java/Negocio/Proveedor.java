package Negocio;

import javax.persistence.*;

@Entity
@Table(name="proveedor")
public class Proveedor {

	@Id
	@GeneratedValue
	private int id;
	@Column
	private String nombre;
	@Column
	private int numeroID;

	public Proveedor(String nombre, int numeroID) {
		this.nombre = nombre;
		this.numeroID = numeroID;
	}

	public void finalize() throws Throwable {

	}
}//end Proveedor