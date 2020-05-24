package Negocio.TiposDeEmpresas;

public class MedianaTramo1 extends TipoDeEmpresa {

    private static MedianaTramo1 instance = null;
    String nombreTipoEmpresa = "Mediana Tramo 1";

    private MedianaTramo1() {
    }

    public static MedianaTramo1 GetInstance() {
        if (instance == null)
            instance = new MedianaTramo1();
        return instance;
    }

    public String getNombreTipoEmpresa() {
        return nombreTipoEmpresa;
    }
}
