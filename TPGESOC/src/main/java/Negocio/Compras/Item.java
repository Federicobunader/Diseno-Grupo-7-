package Negocio.Compras;

import InterfazDeUsuario.InterfazUsuarios;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.stream.Collectors;

@Entity
@Table(name="item")
public class Item {

	@Id
	@GeneratedValue
	private int id;
	private Producto producto;
	@Column
	private int cantidad;
	private ArrayList<CategoriaItem> categorias = new ArrayList<CategoriaItem>();
	private GestorDeCriterios gestorDeCriterios = GestorDeCriterios.GetInstance();

	public Item(Producto producto, int cantidad, ArrayList<CategoriaItem> categorias) {
		this.producto = producto;
		this.cantidad = cantidad;
		this.categorias = categorias;
	}

	InterfazUsuarios interfaz = InterfazUsuarios.GetInstance();

	public int valorTotal(){
		return producto.getPrecio() * cantidad;
	}

	private void asociarCategoria(CategoriaItem unaCategoria){
		if(!(this.estaAsociado(unaCategoria))) {
			categorias.add(unaCategoria);
			interfaz.mostrarInformacion("Se agregó correctamente.");
		}
		else{
			interfaz.mostrarInformacion("Ya está asociado a ese criterio. Solo puede asociarse a una categoría por criterio.");
		}
	}

	private boolean estaAsociado(CategoriaItem unaCategoria){
		CriterioDeItem criterioDeItem = gestorDeCriterios.devolverCriterioParaEsaCategoria(unaCategoria);
		if(criterioDeItem != null){
			return true;
		}else{
			return false;
		}
	}

}//end Item