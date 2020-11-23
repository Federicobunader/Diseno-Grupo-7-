package Negocio;

import BaseDeDatos.EntidadPersistente;

import javax.persistence.*;

@Entity
@Table(name="medioDePago")
public class MedioDePago  extends EntidadPersistente {

	//@OneToOne(cascade = {CascadeType.ALL})
	//@JoinColumn(name = "producto_id", referencedColumnName = "id")
	@Column
	private String tipoDePago;

	public MedioDePago(String tipoDePago) {
		this.tipoDePago = tipoDePago;
	}

	public MedioDePago() {
	}

	public void setTipoDePago(String tipoDePago) {
		this.tipoDePago = tipoDePago;
	}

	public String getTipoDePago() {
		return tipoDePago;
	}
}//end MedioDePago

