package dominio.TipoDeSector;
import dominio.Empresa;
import dominio.TiposDeEmpresas.*;

public class Agropecuaria extends TipoDeSector {

    private static Agropecuaria instance = null;
    String nombreSector = "Agropecuario";

    private Agropecuaria() {
    }

    public static Agropecuaria GetInstance() {
        if (instance == null)
            instance = new Agropecuaria();
        return instance;
    }

    public String getNombreSector() {
        return nombreSector;
    }

    @Override
    public TipoDeEmpresa calcularTipoDeEmpresa(Empresa unaEmpresa, double topeVentasMedianaTramo2, int topePersonalMedianaTramo2, double topeVentasMedianaTramo1, int topePersonalMedianaTramo1, double topeVentasPequenia, int topePersonalPequenia) {
        return super.calcularTipoDeEmpresa(unaEmpresa, 345.43, 50,
                48.48, 10, 12.89, 5);
    }
}
