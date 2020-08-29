package Negocio;

import javax.persistence.*;

@Entity
@Table(name="documento")
public class Documento {
	@Id
	@GeneratedValue
	private int id;
	@Column
	private int Numero;
	@Column
	private char Tipo;

	public Documento(int numero, char tipo) {
		Numero = numero;
		Tipo = tipo;
	}

}//end Documento