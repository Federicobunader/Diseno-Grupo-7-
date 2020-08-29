package API.Servicios;

import API.APIMoneda.Conversor;
import API.APIMoneda.Moneda;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

import java.util.List;

public interface CurrenciesServices {

    @GET("currencies/")
    Call<List<Moneda>> monedas();

    @GET("currency_conversions/search")
    //Call<Conversor> conversorDeMonedas(@Path("MonedaOrigen") String monedaOrigen, @Path("MonedaDestino") String monedaDestino);
    Call<Conversor> conversorDeMonedas(@Query("from") String monedaOrigen, @Query("to") String monedaDestino);
}
