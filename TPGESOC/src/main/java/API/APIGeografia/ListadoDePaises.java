package API.APIGeografia;

import API.Servicios.ServicioGeoref;

import java.util.List;
import java.util.Optional;

public class ListadoDePaises {

    private static ListadoDePaises instancia = null;
    public List<Pais> paises;


    public Optional<Pais> paisesDeId(String id){
        return this.paises.stream()
                .filter(unPais -> unPais.id.equals(id))
                .findFirst();
    }

    public void setPaises(List<Pais> paises) {
        this.paises = paises;
    }

    public static ListadoDePaises GetInstance(){
        if(instancia== null){
            instancia = new ListadoDePaises();
        }
        return instancia;
    }
}
