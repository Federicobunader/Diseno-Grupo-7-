package Negocio.Entidad.Empresa.TipoDeSector.TipoDeSector;

import javax.persistence.*;

@Entity
@Table(name = "agropecuaria")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipoDeSector")
public class Agropecuaria extends TipoDeSector{

    private static Agropecuaria instance = null;
    //String nombreSector = "Agropecuario";

    private Agropecuaria() {
    }

    public static Agropecuaria GetInstance() {
        if (instance == null)
            instance = new Agropecuaria();
        return instance;
    }

   /* public String getNombreSector() {
        return nombreSector;
    }*/

    public double getTopeVentasMedianaTramo2() {
        return 345.43;
    }

    public int getTopePersonalMedianaTramo2() {
        return 50;
    }

    public double getTopeVentasMedianaTramo1() {
        return 48.48;
    }

    public int getTopePersonalMedianaTramo1() {
        return 10;
    }

    public double getTopeVentasPequenia() {
        return 12.89;
    }

    public int getTopePersonalPequenia() {
        return 5;
    }

}
