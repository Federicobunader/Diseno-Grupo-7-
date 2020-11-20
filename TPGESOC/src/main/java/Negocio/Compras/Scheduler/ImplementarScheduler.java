package Negocio.Compras.Scheduler;

import Negocio.Compras.Compra;
import Negocio.Compras.GestorDeEgresos;
import Negocio.GestorDeProyectos;
import Negocio.Proyecto;
import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.ArrayList;

public class ImplementarScheduler implements Job{

    GestorDeEgresos gestorDeEgresos = GestorDeEgresos.GetInstance();

    GestorDeProyectos gestorDeProyectos = GestorDeProyectos.GetInstance();

    double montoDefinido;
    int cantidadPresupuestosExigibles;

    public void execute(JobExecutionContext jExeCtx) throws JobExecutionException {

        int tamanioEgresos = gestorDeEgresos.getComprasNoValidadas().size();
        int tamanioProyectos = gestorDeProyectos.getProyectos().size();

        for(int i=0; i<tamanioProyectos; i++) {
            Proyecto proyecto = gestorDeProyectos.getProyectos().get(i);
            proyecto.validar();
        }

        for(int i=0; i<tamanioEgresos; i++) {
            Compra compraAValidar = gestorDeEgresos.getComprasNoValidadas().get(i);
            if(!compraAValidar.isPerteneceAProyecto()){
                compraAValidar.validar(montoDefinido, cantidadPresupuestosExigibles);
                gestorDeEgresos.agregarCompraValidada(compraAValidar);
            }
        }

        gestorDeEgresos.vaciarListaDeNoValidadas();
    }
}
