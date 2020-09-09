package Negocio;

import BaseDeDatos.EntidadPersistente;

import javax.persistence.*;

enum tipoDePago {
	creditCard,
	debitCard,
	ticket,
	atm,
	accountMoney
}

@Entity
@Table(name="medioDePago")
public class MedioDePago  extends EntidadPersistente {

	//@OneToOne(cascade = {CascadeType.ALL})
	//@JoinColumn(name = "producto_id", referencedColumnName = "id")
	@Transient
	private tipoDePago tipoDePago;

	@Column
	private int Identificador;

	public MedioDePago(tipoDePago tipoDePago, int identificador) {
		this.tipoDePago = tipoDePago;
		Identificador = identificador;
	}

	public void finalize() throws Throwable {

	}
}//end MedioDePago

