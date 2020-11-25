package Bitacora;

import java.util.Date;
import java.util.List;

public class Bitacora {

    private static Bitacora instance = null;
    private List<Operacion> operaciones;

    public static Bitacora GetInstance() {
        if (instance == null)
            instance = new Bitacora();
        return instance;
    }

    public void add(Operacion operacion){
        operaciones.add(operacion);
    }
}
