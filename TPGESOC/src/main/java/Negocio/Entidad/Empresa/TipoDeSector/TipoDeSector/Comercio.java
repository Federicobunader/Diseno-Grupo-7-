package Negocio.Entidad.Empresa.TipoDeSector.TipoDeSector;

import javax.persistence.*;

@Entity
@Table(name = "comercio")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipoDeSector")
public class Comercio extends TipoDeSector{

    private static Comercio instance = null;
    //String nombreSector = "Comercio";

    private Comercio() {
    }

    public static Comercio GetInstance() {
        if (instance == null)
            instance = new Comercio();
        return instance;
    }
/*
    public String getNombreSector() {
        return nombreSector;
    }
*/
    public double getTopeVentasMedianaTramo2() {
        return 1502.75;
    }

    public int getTopePersonalMedianaTramo2() {
        return 125;
    }

    public double getTopeVentasMedianaTramo1() {
        return 178.86;
    }

    public int getTopePersonalMedianaTramo1() {
        return 35;
    }

    public double getTopeVentasPequenia() {
        return 29.74;
    }

    public int getTopePersonalPequenia() {
        return 7;
    }

}

