package Negocio.Compras;

import BaseDeDatos.EntidadPersistente;
import InterfazDeUsuario.InterfazUsuarios;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name="item")
public class Item  extends EntidadPersistente {

	@OneToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name = "producto_id", referencedColumnName = "id")
	private Producto producto;

	@Column
	private int cantidad;

	@ManyToMany
	@JoinTable(name = "item_x_categoriaItem")
	private List<CategoriaItem> categorias = new ArrayList<CategoriaItem>();

	@Transient
	private GestorDeCriterios gestorDeCriterios = GestorDeCriterios.GetInstance();

	public Item(Producto producto, int cantidad, ArrayList<CategoriaItem> categorias) {
		this.producto = producto;
		this.cantidad = cantidad;
		this.categorias = categorias;
	}

	public Item() {
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public void setCategorias(List<CategoriaItem> categorias) {
		this.categorias = categorias;
	}

	public void setGestorDeCriterios(GestorDeCriterios gestorDeCriterios) {
		this.gestorDeCriterios = gestorDeCriterios;
	}

	public void setInterfaz(InterfazUsuarios interfaz) {
		this.interfaz = interfaz;
	}

	@Transient
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