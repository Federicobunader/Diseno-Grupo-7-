package dominio.TipoDeSector;

import dominio.Empresa;
import dominio.TiposDeEmpresas.*;

public class Comercio extends TipoDeSector{

    private static Comercio instance = null;
    String nombreSector = "Comercio";

    private Comercio() {
    }

    public static Comercio GetInstance() {
        if (instance == null)
            instance = new Comercio();
        return instance;
    }

    public String getNombreSector() {
        return nombreSector;
    }

    @Override
    public TipoDeEmpresa calcularTipoDeEmpresa(Empresa unaEmpresa, double topeVentasMedianaTramo2, int topePersonalMedianaTramo2, double topeVentasMedianaTramo1, int topePersonalMedianaTramo1, double topeVentasPequenia, int topePersonalPequenia) {
        return super.calcularTipoDeEmpresa(unaEmpresa, 1502.75, 125, 178.86, 35, 29.74, 7);
    }
}

