package Negocio;

public class MedioDePago {

	enum tipoDePago {
		creditCard,
		debitCard,
		ticket,
		atm,
		accountMoney
	}

	private tipoDePago tipoDePago;
	private int Identificador;

	public MedioDePago(MedioDePago.tipoDePago tipoDePago, int identificador) {
		this.tipoDePago = tipoDePago;
		Identificador = identificador;
	}

	public void finalize() throws Throwable {

	}
}//end MedioDePago

