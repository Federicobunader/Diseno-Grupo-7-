package API.Servicios;

import API.APIGeografia.ListadoDePaises;
import retrofit2.Call;
import retrofit2.http.GET;

import java.util.List;

public interface GeorefService {

    @GET("countries")
    Call<ListadoDePaises> paises();


}
