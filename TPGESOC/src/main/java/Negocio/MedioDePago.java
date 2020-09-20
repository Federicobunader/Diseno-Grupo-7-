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

	public MedioDePago() {
	}

	public void setTipoDePago(Negocio.tipoDePago tipoDePago) {
		this.tipoDePago = tipoDePago;
	}

	public void setIdentificador(int identificador) {
		Identificador = identificador;
	}
}//end MedioDePago

