package Negocio;

import BaseDeDatos.EntidadPersistente;

import javax.persistence.*;

@Entity
@Table(name="documento")
public class Documento extends EntidadPersistente {

	@Column
	private int Numero;
	@Column
	private char Tipo;

	public Documento(int numero, char tipo) {
		Numero = numero;
		Tipo = tipo;
	}

}//end Documento