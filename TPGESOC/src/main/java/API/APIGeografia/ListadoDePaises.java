package API.APIGeografia;

import java.util.List;
import java.util.Optional;

public class ListadoDePaises {

    public int cantidad;
    public int total;
    public int inicio;
    public Parametro parametros;
    public List<Pais> paises;

        public Optional<Pais> paisPorId(String id){
            return this.paises.stream()
                    .filter(pais -> pais.id.equals(id))
                    .findFirst();
        }

    private class Parametro {
        public List<String> campos;
    }
}
