package dominio.TiposDeEmpresas;

public class MedianaTramo2 extends TipoDeEmpresa {

    private static MedianaTramo2 instance = null;
    String nombreTipoEmpresa = "Mediana Tramo 2";

    private MedianaTramo2() {
    }

    public static MedianaTramo2 GetInstance() {
        if (instance == null)
            instance = new MedianaTramo2();
        return instance;
    }

    public String getNombreTipoEmpresa() {
        return nombreTipoEmpresa;
    }
}
