package dominio.TipoDeSector;
import dominio.Empresa;
import dominio.TiposDeEmpresas.*;

public class Agropecuaria extends TipoDeSector {

    public TipoDeEmpresa calcularTipoDeEmpresa(Empresa unaEmpresa) {

        float ventas = unaEmpresa.getPromedioDeVentasAnuales();

        if (ventas <= 12.89) {
            MicroEmpresa microEmpresa = MicroEmpresa.GetInstance();
            return microEmpresa;
        } else if (ventas <= 48.48 && ventas > 12.89) {
            PequeniaEmpresa pequeniaEmpresa = PequeniaEmpresa.GetInstance();
            return pequeniaEmpresa;
        } else if (ventas <= 345.43 && ventas > 48.48) {
            MedianaTramo1 medianaTramo1 = MedianaTramo1.GetInstance();
            return medianaTramo1;
        } else {
            MedianaTramo2 medianaTramo2 = MedianaTramo2.GetInstance();
            return medianaTramo2;
        }
    }
}
