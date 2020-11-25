package Bitacora;

import java.util.Date;
import java.text.SimpleDateFormat;

public class GuardadorDeLog {

    private static GuardadorDeLog instance = null;
    Bitacora bitacora = Bitacora.GetInstance();


    public static GuardadorDeLog GetInstance() {
        if (instance == null)
            instance = new GuardadorDeLog();
        return instance;
    }

    public void GuardarEnBitacora(Object unObjeto, String tipoDeOperacion){
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        date.getTime();

       Operacion operacion = new Operacion(tipoDeOperacion,unObjeto.getClass().getSimpleName(),formatter.format(date));
       bitacora.add(operacion);

    }
}
