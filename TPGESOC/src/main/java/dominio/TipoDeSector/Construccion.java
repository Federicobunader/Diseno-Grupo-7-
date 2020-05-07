package dominio.TipoDeSector;

import dominio.Empresa;
import dominio.TiposDeEmpresas.*;

public class Construccion {

    public TipoDeEmpresa calcularTipoDeEmpresa(Empresa unaEmpresa) {

        float ventas = unaEmpresa.getPromedioDeVentasAnuales();

        if (ventas <= 15.23) {
            MicroEmpresa microEmpresa = MicroEmpresa.GetInstance();
            return microEmpresa;
        } else if (ventas <= 90.31) {
            PequeniaEmpresa pequeniaEmpresa = PequeniaEmpresa.GetInstance();
            return pequeniaEmpresa;
        } else if (ventas <= 503.88) {
            MedianaTramo1 medianaTramo1 = MedianaTramo1.GetInstance();
            return medianaTramo1;
        } else {
            MedianaTramo2 medianaTramo2 = MedianaTramo2.GetInstance();
            return medianaTramo2;
        }
    }
}
