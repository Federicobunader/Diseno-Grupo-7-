package API.APIMoneda;

import java.util.List;
import java.util.Optional;

public class ListadoDeMonedas {
    private static ListadoDeMonedas instancia = null;
    public List<Moneda> monedas;


    public Optional<Moneda> monedasPorId(String id){
        return this.monedas.stream()
                .filter(unaMoneda -> unaMoneda.id.equals(id))
                .findFirst();
    }

    public void setMonedas(List<Moneda> monedas) {
        this.monedas = monedas;
    }

    public static ListadoDeMonedas GetInstance(){
        if(instancia== null){
            instancia = new ListadoDeMonedas();
        }
        return instancia;
    }
}
