package dominio.TipoDeSector;

import dominio.Empresa;
import dominio.TiposDeEmpresas.*;

public abstract class TipoDeSector {

    String nombreSector;
    public TipoDeEmpresa calcularTipoDeEmpresa(Empresa unaEmpresa, double topeVentasMedianaTramo2, int topePersonalMedianaTramo2,
                                               double topeVentasMedianaTramo1, int topePersonalMedianaTramo1,
                                               double topeVentasPequenia, int topePersonalPequenia) {

        double ventas = unaEmpresa.getPromedioDeVentasAnuales();
        int cantidadDePersonal = unaEmpresa.getCantidadDePersonal();

        if(ventas > topeVentasMedianaTramo2 || cantidadDePersonal > topePersonalMedianaTramo2){
            MedianaTramo2 medianaTramo2 = MedianaTramo2.GetInstance();
            return medianaTramo2;
        }else if (ventas > topeVentasMedianaTramo1 || cantidadDePersonal > topePersonalMedianaTramo1) {
            MedianaTramo1 medianaTramo1 = MedianaTramo1.GetInstance();
            return medianaTramo1;
        } else if (ventas > topeVentasPequenia|| cantidadDePersonal > topePersonalPequenia) {
            PequeniaEmpresa pequeniaEmpresa = PequeniaEmpresa.GetInstance();
            return pequeniaEmpresa;
        } else{
            MicroEmpresa microEmpresa = MicroEmpresa.GetInstance();
            return microEmpresa;
        }
    }

    public String getNombreSector() {
        return nombreSector;
    }
}