package Negocio;

import java.util.ArrayList;

public class EntidadJuridica extends Entidad {

	private ArrayList<EntidadBase> entidadBases = new ArrayList<EntidadBase>();

	private String RazonSocial;
	private String NombreFicticio;
	private int CUIT;
	private String DireccionPostal;
	private int CodigoDeInscripcion;

	public EntidadJuridica(){

	}

	public void finalize() throws Throwable {

	}
}//end EntidadJuridica