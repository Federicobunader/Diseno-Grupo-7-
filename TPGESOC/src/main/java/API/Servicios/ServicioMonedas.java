package API.Servicios;

import API.APIMoneda.Conversor;
import API.APIMoneda.ListadoDeMonedas;
import API.APIMoneda.Moneda;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.List;

public class ServicioMonedas {

    private static ServicioMonedas instancia = null;
    private static int maximaCantidadRegistrosDefault = 200;
    private Retrofit retrofit;

    private ServicioMonedas() {
        this.retrofit = new Retrofit.Builder()
                .baseUrl("https://api.mercadolibre.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static ServicioMonedas instancia(){
        if(instancia== null){
            instancia = new ServicioMonedas();
        }
        return instancia;
    }

    public List<Moneda> listadoDeMonedas() throws IOException {
        CurrenciesServices currenciesServices = this.retrofit.create(CurrenciesServices.class);
        Call<List<Moneda>> requestListadoDeMonedas = currenciesServices.monedas();
        Response<List<Moneda>> responseListadoDeMonedas = requestListadoDeMonedas.execute();
        List<Moneda> listadoMonedas = responseListadoDeMonedas.body();
        ListadoDeMonedas listado = ListadoDeMonedas.GetInstance();

        listado.setMonedas(listadoMonedas);

        return listadoMonedas;
    }

    public Conversor conversorMonedas(Moneda monedaOrigen,Moneda monedaDestino) throws IOException {
        CurrenciesServices currenciesServices = this.retrofit.create(CurrenciesServices.class);
        Call<Conversor> requestConversor = currenciesServices.conversorDeMonedas(monedaOrigen.id,monedaDestino.id);
        Response<Conversor> responseConversor = requestConversor.execute();
        Conversor conversion = responseConversor.body();

        return conversion;
    }
/*
    public List<Pais> listadoDePaises() throws IOException {
        GeorefService georefService = this.retrofit.create(GeorefService.class);
        Call<List<Pais>> requestListadoDePaises = georefService.paises();
        Response<List<Pais>> responseListadoDePaises = requestListadoDePaises.execute();
        List<Pais> listaPaises = responseListadoDePaises.body();
        ListadoDePaises listado = ListadoDePaises.GetInstance();

        listado.setPaises(listaPaises);

        return listaPaises;
    }

 */
}
