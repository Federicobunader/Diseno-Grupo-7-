package Negocio.Entidad.Empresa.TipoDeEmpresas.TiposDeEmpresas;

public class MicroEmpresa extends TipoDeEmpresa {

    private static MicroEmpresa instance = null;
    String nombreTipoEmpresa = "Micro";

    private MicroEmpresa() {
    }

    public static MicroEmpresa GetInstance() {
        if (instance == null)
            instance = new MicroEmpresa();
        return instance;
    }

    public String getNombreTipoEmpresa() {
        return nombreTipoEmpresa;
    }
}
