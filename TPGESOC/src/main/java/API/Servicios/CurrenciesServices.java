package API.Servicios;

import API.APIMoneda.Moneda;
import retrofit2.Call;
import retrofit2.http.GET;

import java.util.List;

public interface CurrenciesServices {

    @GET("currencies/")
    Call<List<Moneda>> monedas();
}
