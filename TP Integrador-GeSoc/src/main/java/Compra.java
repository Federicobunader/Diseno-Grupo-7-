
public class Compra {

	//private Date FechaDeOperacion;
	private MedioDePago MedioDePago;
	private Documento DocumentoComercial;
	private Entidad Entidad;
	private Item Items;
	private Proveedor Proveedor;
	public MedioDePago m_MedioDePago;
	public Item m_Item;
	public Proveedor m_Proveedor;
	public Documento m_Documento;
	public Entidad m_Entidad;

	public Compra(){

	}

	public void finalize() throws Throwable {

	}
	public int valorTotal(){
		return 0;
	}
}//end Compra