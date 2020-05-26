package Negocio;

public class MenorValor extends Criterio {

    private static MenorValor instance = null;
    String nombreStrategy = "Menor valor";

    public static MenorValor GetInstance() {
        if (instance == null)
            instance = new MenorValor();
        return instance;
    }
}
