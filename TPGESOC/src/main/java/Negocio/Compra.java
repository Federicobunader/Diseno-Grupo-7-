package Negocio;

import Negocio.Entidad.Entidad;

import java.util.ArrayList;
import java.util.Calendar;

public class Compra {

	private ArrayList<Item> items = new ArrayList<Item>();

	private Calendar fechaDeOperacion;
	private MedioDePago medioDePago;
	private Documento documentoComercial;
	private Entidad entidad;
	private Proveedor proveedor;

	public Compra(){

	}

	public void finalize() throws Throwable {

	}
	public int valorTotal(){
		return 0;
	}
}//end Compra