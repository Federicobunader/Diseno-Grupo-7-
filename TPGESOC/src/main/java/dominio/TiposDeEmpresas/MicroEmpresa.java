package dominio.TiposDeEmpresas;

public class MicroEmpresa extends TipoDeEmpresa {

    private static MicroEmpresa instance = null;

    private MicroEmpresa() {
    }

    public static MicroEmpresa GetInstance() {
        if (instance == null)
            instance = new MicroEmpresa();
        return instance;
    }

}
