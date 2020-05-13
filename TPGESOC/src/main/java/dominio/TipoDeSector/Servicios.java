package dominio.TipoDeSector;

import dominio.Empresa;
import dominio.TiposDeEmpresas.*;

public class Servicios extends TipoDeSector{

    String nombreSector = "Servicios";
    private static Servicios instance = null;

    private Servicios() {
    }

    public static Servicios GetInstance() {
        if (instance == null)
            instance = new Servicios();
        return instance;
    }

    public String getNombreSector() {
        return nombreSector;
    }

    @Override
    public TipoDeEmpresa calcularTipoDeEmpresa(Empresa unaEmpresa, double topeVentasMedianaTramo2, int topePersonalMedianaTramo2, double topeVentasMedianaTramo1, int topePersonalMedianaTramo1, double topeVentasPequenia, int topePersonalPequenia) {
        return super.calcularTipoDeEmpresa(unaEmpresa, 425.17, 165,
                50.95, 30, 8.5, 7);
    }
}
