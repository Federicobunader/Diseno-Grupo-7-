package dominio.TipoDeSector;

import dominio.Empresa;
import dominio.TiposDeEmpresas.*;

public class IndustriaYMinera extends TipoDeSector{

    String nombreSector = "Industria y Minera";
    private static IndustriaYMinera instance = null;

    private IndustriaYMinera() {
    }

    public static IndustriaYMinera GetInstance() {
        if (instance == null)
            instance = new IndustriaYMinera();
        return instance;
    }

    public String getNombreSector() {
        return nombreSector;
    }

    @Override
    public TipoDeEmpresa calcularTipoDeEmpresa(Empresa unaEmpresa, double topeVentasMedianaTramo2, int topePersonalMedianaTramo2, double topeVentasMedianaTramo1, int topePersonalMedianaTramo1, double topeVentasPequenia, int topePersonalPequenia) {
        return super.calcularTipoDeEmpresa(unaEmpresa, 1190.33, 235, 190.41, 60, 26.54, 15);
    }
}
