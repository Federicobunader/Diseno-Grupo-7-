package Negocio.Compras.Scheduler;

import Negocio.Compras.Compra;
import Negocio.Compras.GestorDeEgresos;
import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.ArrayList;

public class ImplementarScheduler implements Job{

    GestorDeEgresos gestorDeEgresos = GestorDeEgresos.GetInstance();

    public void execute(JobExecutionContext jExeCtx) throws JobExecutionException {

        System.out.println("FUNCIONA EL SCHEDULER");

        int tamanio = gestorDeEgresos.getComprasNoValidadas().size();

        for(int i=0; i<tamanio; i++) {
            Compra compraAValidar = gestorDeEgresos.getComprasNoValidadas().get(i);
            compraAValidar.validar();
            gestorDeEgresos.agregarCompraValidada(compraAValidar);
        }

        gestorDeEgresos.vaciarListaDeNoValidadas();
    }
}
