package dominio.TipoDeSector;

import dominio.Empresa;
import dominio.TiposDeEmpresas.*;

public class Comercio {

    public TipoDeEmpresa calcularTipoDeEmpresa(Empresa unaEmpresa) {

        float ventas = unaEmpresa.getPromedioDeVentasAnuales();

        if (ventas <= 29.74) {
            MicroEmpresa microEmpresa = MicroEmpresa.GetInstance();
            return microEmpresa;
        } else if (ventas <= 178.86) {
            PequeniaEmpresa pequeniaEmpresa = PequeniaEmpresa.GetInstance();
            return pequeniaEmpresa;
        } else if (ventas <= 1502.75) {
            MedianaTramo1 medianaTramo1 = MedianaTramo1.GetInstance();
            return medianaTramo1;
        } else {
            MedianaTramo2 medianaTramo2 = MedianaTramo2.GetInstance();
            return medianaTramo2;
        }
    }
}

