package Negocio.Entidad.Empresa.TipoDeSector.TipoDeSector;

import javax.persistence.*;

@Entity
@Table(name = "servicios")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipoDeSector")
public class Servicios extends TipoDeSector{

    //String nombreSector = "Servicios";
    private static Servicios instance = null;

    private Servicios() {
    }

    public static Servicios GetInstance() {
        if (instance == null)
            instance = new Servicios();
        return instance;
    }
/*
    public String getNombreSector() {
        return nombreSector;
    }
*/
    public double getTopeVentasMedianaTramo2() {
        return 425.17;
    }

    public int getTopePersonalMedianaTramo2() {
        return 165;
    }

    public double getTopeVentasMedianaTramo1() {
        return 50.95;
    }

    public int getTopePersonalMedianaTramo1() {
        return 30;
    }

    public double getTopeVentasPequenia() {
        return 8.5;
    }

    public int getTopePersonalPequenia() {
        return 7;
    }

}
