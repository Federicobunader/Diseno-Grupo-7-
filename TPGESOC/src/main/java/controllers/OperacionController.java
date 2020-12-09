package controllers;

import Bitacora.Bitacora;
import Bitacora.Operacion;
import repositories.Repositorio;
import repositories.factories.FactoryRepositorio;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OperacionController {

    private Repositorio<Operacion> repo;

    public Repositorio<Operacion> getRepo() {
        return repo;
    }

    public OperacionController() {
        this.repo = FactoryRepositorio.get(Operacion.class);
    }

    public ModelAndView bitacora(Request request, Response response){

        Map<String, Object> parametros = new HashMap<>();
        List<Operacion> operaciones = this.repo.buscarTodos();
        parametros.put("operaciones", operaciones);

        return new ModelAndView(parametros,"GESOC_Bitacora.hbs");
    }

    public void GuardarEnBitacora(Object unObjeto, String tipoDeOperacion){

        Bitacora bitacora = Bitacora.GetInstance();

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        date.getTime();

        Operacion operacion = new Operacion();
        operacion.setTipoDeOperacion(tipoDeOperacion);
        operacion.setEntidad(unObjeto.getClass().getSimpleName());
        operacion.setFechaDeOperacion(formatter.format(date).toString());

        bitacora.agregarOperacion(operacion);

        Repositorio<Bitacora> repoBitacora = FactoryRepositorio.get(Bitacora.class);
        repoBitacora.modificar(bitacora);

    }

}
