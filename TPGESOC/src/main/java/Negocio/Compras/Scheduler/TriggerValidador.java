package Negocio.Compras.Scheduler;
import Negocio.Compras.Validador;
import org.quartz.*;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;

public class TriggerValidador {

    public void ejecutarScheduler() {

        try {
            SchedulerFactory schFactory = new StdSchedulerFactory();
            Scheduler sch = schFactory.getScheduler();

            JobDetail job = JobBuilder.newJob(ImplementarScheduler.class).withIdentity("validador").build();

            // specify the running period of the job
            Trigger trigger = TriggerBuilder.newTrigger().withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInHours(24).repeatForever()).build();

            sch.start();
            sch.scheduleJob(job, trigger);

        } catch (SchedulerException e) {

            e.printStackTrace();
        }
    }
}

