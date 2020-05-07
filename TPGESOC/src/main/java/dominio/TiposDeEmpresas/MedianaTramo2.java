package dominio.TiposDeEmpresas;

public class MedianaTramo2 extends TipoDeEmpresa {

    private static MedianaTramo2 instance = null;

    private MedianaTramo2() {
    }

    public static MedianaTramo2 GetInstance() {
        if (instance == null)
            instance = new MedianaTramo2();
        return instance;
    }
}
