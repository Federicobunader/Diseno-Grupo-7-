package API.Servicios;

import API.APIGeografia.ListadoDeCiudades;
import API.APIGeografia.ListadoDeProvincias;
import API.APIGeografia.Pais;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

import java.util.List;

public interface GeorefService {

    @GET("countries")
    Call<List<Pais>> paises();

    @GET("countries/{name}")
    Call<ListadoDeProvincias> provincias(@Path("name") String name);

    @GET("states/{nameCity}")
    Call<ListadoDeCiudades> ciudades(@Path("nameCity") String nameCity);

}
