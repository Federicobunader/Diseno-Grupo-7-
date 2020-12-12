package Bitacora;

import com.mongodb.Mongo;

import java.util.Date;
import java.text.SimpleDateFormat;

public class GuardadorDeLog {

    private static GuardadorDeLog instance = null;
    Bitacora bitacora = Bitacora.GetInstance();
    MongoDB mongoDB = MongoDB.GetInstance();

    public static GuardadorDeLog GetInstance() {
        if (instance == null)
            instance = new GuardadorDeLog();
        return instance;
    }

    public void GuardarEnBitacora(Object unObjeto, String tipoDeOperacion){
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        date.getTime();


       Operacion operacion = new Operacion(tipoDeOperacion,unObjeto.getClass().getSimpleName(),formatter.format(date).toString());
       operacion.setTipoDeOperacion(tipoDeOperacion);
       operacion.setEntidad(unObjeto.getClass().getSimpleName());
       operacion.setFechaDeOperacion(formatter.format(date).toString());
       bitacora.add(operacion);

       mongoDB.addDocument(tipoDeOperacion,unObjeto.getClass().getSimpleName(),formatter.format(date).toString());
    }
}
