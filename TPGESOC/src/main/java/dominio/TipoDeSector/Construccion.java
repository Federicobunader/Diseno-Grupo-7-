package dominio.TipoDeSector;

import dominio.Empresa;
import dominio.TiposDeEmpresas.*;

public class Construccion extends TipoDeSector{

    private static Construccion instance = null;
    String nombreSector = "Construccion";

    private Construccion() {
    }

    public static Construccion GetInstance() {
        if (instance == null)
            instance = new Construccion();
        return instance;
    }

    public String getNombreSector() {
        return nombreSector;
    }

    @Override
    public TipoDeEmpresa calcularTipoDeEmpresa(Empresa unaEmpresa, double topeVentasMedianaTramo2, int topePersonalMedianaTramo2, double topeVentasMedianaTramo1, int topePersonalMedianaTramo1, double topeVentasPequenia, int topePersonalPequenia) {
        return super.calcularTipoDeEmpresa(unaEmpresa, 503.88, 200, 90.31, 45, 15.23, 12);
    }
}
