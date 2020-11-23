package Negocio;

import BaseDeDatos.EntidadPersistente;

import javax.persistence.*;

@Entity
@Table(name="documento")
public class Documento extends EntidadPersistente {

	@Column
	private int numero;
	@Column
	private String tipo;

	public Documento(int unNumero, String unTipo) {
		numero = unNumero;
		tipo = unTipo;
	}

	public Documento() {
	}

	public void setNumero(int unNumero) {
		numero = unNumero;
	}

	public void setTipo(String unTipo) {
		tipo = unTipo;
	}

	public int getNumero() {
		return numero;
	}

	public String getTipo() {
		return tipo;
	}
}//end Documento