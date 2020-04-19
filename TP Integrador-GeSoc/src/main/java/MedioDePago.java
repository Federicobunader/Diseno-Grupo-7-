
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

	public void finalize() throws Throwable {

	}
}//end MedioDePago

