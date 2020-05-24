package Negocio.Entidad.Empresa.TipoDeSector.TipoDeSector;

public class Construccion extends TipoDeSector{

    private static Construccion instance = null;
    String nombreSector = "Construccion";

    private Construccion() {
    }

    public static Construccion GetInstance() {
        if (instance == null)
            instance = new Construccion();
        return instance;
    }

    public String getNombreSector() {
        return nombreSector;
    }

    public double getTopeVentasMedianaTramo2() {
        return 503.88;
    }

    public int getTopePersonalMedianaTramo2() {
        return 200;
    }

    public double getTopeVentasMedianaTramo1() {
        return 90.31;
    }

    public int getTopePersonalMedianaTramo1() {
        return 45;
    }

    public double getTopeVentasPequenia() {
        return 15.23;
    }

    public int getTopePersonalPequenia() {
        return 12;
    }

}
