package Negocio;

import Negocio.TipoDeSector.TipoDeSector;
import Negocio.TiposDeEmpresas.*;

public class Categorizador {

    private static Categorizador instance = null;

    private Categorizador() {
    }

    public static Categorizador GetInstance() {
        if (instance == null)
            instance = new Categorizador();
        return instance;
    }

    public void actualizarTipoDeEmpresa(TipoDeSector sector,double ventas, int cantidadDePersonal){

        TipoDeEmpresa tipoDeEmpresa = this.calcularTipoDeEmpresa(sector,ventas,cantidadDePersonal);
        System.out.println(sector.getNombreSector());
        System.out.println(tipoDeEmpresa.getNombreTipoEmpresa());
    }

    public TipoDeEmpresa calcularTipoDeEmpresa(TipoDeSector sector,double ventas,int cantidadDePersonal) {

        if(ventas > sector.getTopeVentasMedianaTramo2() || cantidadDePersonal > sector.getTopePersonalMedianaTramo2()){
            MedianaTramo2 medianaTramo2 = MedianaTramo2.GetInstance();
            return medianaTramo2;
        }else if (ventas > sector.getTopeVentasMedianaTramo1() || cantidadDePersonal > sector.getTopePersonalMedianaTramo1()) {
            MedianaTramo1 medianaTramo1 = MedianaTramo1.GetInstance();
            return medianaTramo1;
        } else if (ventas > sector.getTopeVentasPequenia()|| cantidadDePersonal > sector.getTopePersonalPequenia()) {
            PequeniaEmpresa pequeniaEmpresa = PequeniaEmpresa.GetInstance();
            return pequeniaEmpresa;
        } else{
            MicroEmpresa microEmpresa = MicroEmpresa.GetInstance();
            return microEmpresa;
        }
    }
}
