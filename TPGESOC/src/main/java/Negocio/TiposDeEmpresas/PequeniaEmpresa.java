package Negocio.TiposDeEmpresas;

public class PequeniaEmpresa extends TipoDeEmpresa {

    private static PequeniaEmpresa instance = null;
    String nombreTipoEmpresa = "Pequeña";

    private PequeniaEmpresa() {
    }

    public static PequeniaEmpresa GetInstance() {
        if (instance == null)
            instance = new PequeniaEmpresa();
        return instance;
    }

    public String getNombreTipoEmpresa() {
        return nombreTipoEmpresa;
    }
}
