package dominio.TipoDeSector;

import dominio.Empresa;
import dominio.TiposDeEmpresas.*;

public class IndustriaYMinera {

    public TipoDeEmpresa calcularTipoDeEmpresa(Empresa unaEmpresa) {

        float ventas = unaEmpresa.getPromedioDeVentasAnuales();

        if (ventas <= 26.54) {
            MicroEmpresa microEmpresa = MicroEmpresa.GetInstance();
            return microEmpresa;
        } else if (ventas <= 190.41) {
            PequeniaEmpresa pequeniaEmpresa = PequeniaEmpresa.GetInstance();
            return pequeniaEmpresa;
        } else if (ventas <= 1190.33) {
            MedianaTramo1 medianaTramo1 = MedianaTramo1.GetInstance();
            return medianaTramo1;
        } else {
            MedianaTramo2 medianaTramo2 = MedianaTramo2.GetInstance();
            return medianaTramo2;
        }
    }
}
