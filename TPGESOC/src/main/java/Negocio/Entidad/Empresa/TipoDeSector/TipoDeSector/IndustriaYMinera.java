package Negocio.Entidad.Empresa.TipoDeSector.TipoDeSector;

public class IndustriaYMinera extends TipoDeSector{

    String nombreSector = "Industria y Minera";
    private static IndustriaYMinera instance = null;

    private IndustriaYMinera() {
    }

    public static IndustriaYMinera GetInstance() {
        if (instance == null)
            instance = new IndustriaYMinera();
        return instance;
    }

    public String getNombreSector() {
        return nombreSector;
    }

    public double getTopeVentasMedianaTramo2() {
        return 1190.33;
    }

    public int getTopePersonalMedianaTramo2() {
        return 235;
    }

    public double getTopeVentasMedianaTramo1() {
        return 190.41;
    }

    public int getTopePersonalMedianaTramo1() {
        return 60;
    }

    public double getTopeVentasPequenia() {
        return 26.54;
    }

    public int getTopePersonalPequenia() {
        return 15;
    }

}
