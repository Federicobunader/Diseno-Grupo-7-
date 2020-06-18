package Negocio.Compras;

import InterfazDeUsuario.InterfazUsuarios;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class Item {

	private Producto producto;
	private int cantidad;
	private ArrayList<CategoriaItem> categorias = new ArrayList<CategoriaItem>();
	private ArrayList<CriterioDeItem> criterios = new ArrayList<CriterioDeItem>();
	private GestorDeCriterios gestorDeCriterios = GestorDeCriterios.GetInstance();

	public Item(Producto producto, int cantidad, ArrayList<CategoriaItem> categorias) {
		this.producto = producto;
		this.cantidad = cantidad;
		this.categorias = categorias;
		categorias.stream().forEach(categoriaItem -> criterios.add(gestorDeCriterios.devolverCriterioParaEsaCategoria(categoriaItem)));
	}

	InterfazUsuarios interfaz = InterfazUsuarios.GetInstance();

	public int valorTotal(){
		return producto.precio * cantidad;
	}

	private void asociarCategoria(CategoriaItem unaCategoria){
		if(!(this.estaAsociado(unaCategoria))) {
			categorias.add(unaCategoria);
			agregarCriterio(gestorDeCriterios.devolverCriterioParaEsaCategoria(unaCategoria));
			interfaz.mostrarInformacion("Se agregó correctamente.");
		}
		else{
			interfaz.mostrarInformacion("Ya está asociado a ese criterio. Solo puede asociarse a una categoría por criterio.");
		}
	}

	private boolean estaAsociado(CategoriaItem unaCategoria){
		return criterios.contains(unaCategoria.getCriterioDeItem());
	}

	private void agregarCriterio(CriterioDeItem unCriterio){
		criterios.add(unCriterio);
	}

}//end Item