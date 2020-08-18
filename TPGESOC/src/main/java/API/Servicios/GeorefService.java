package API.Servicios;

import API.APIGeografia.ListadoDeProvincias;
import API.APIGeografia.Pais;
import retrofit2.Call;
import retrofit2.http.GET;

import java.util.List;

public interface GeorefService {

    @GET("countries")
    Call<List<Pais>> paises();

    @GET("provincias")
    Call<ListadoDeProvincias> provincias();

}
