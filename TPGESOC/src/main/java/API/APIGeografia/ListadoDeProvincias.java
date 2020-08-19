package API.APIGeografia;

import java.util.List;
import java.util.Optional;

public class ListadoDeProvincias {

    private static ListadoDeProvincias instancia = null;
    public String id;
    public String name;
    public Parametro parametros;
    public List<Provincia> states;

    public Optional<Provincia> provinciaDeId(String id){
        return this.states.stream()
                .filter(unaProvincia -> unaProvincia.id.equals(id))
                .findFirst();
    }

/*
    public void setStates(List<Provincia> states) {
        this.states = states;
    }
*/
    private class Parametro {
            public List<String> campos;
    }
/*
    public static ListadoDeProvincias GetInstance(){
        if(instancia== null){
            instancia = new ListadoDeProvincias();
        }
        return instancia;
    }
*/
}
