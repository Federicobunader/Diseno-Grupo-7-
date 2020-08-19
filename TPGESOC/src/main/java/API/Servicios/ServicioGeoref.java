package API.Servicios;

import API.APIGeografia.*;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.List;

public class ServicioGeoref {

    private static ServicioGeoref instancia = null;
    private static int maximaCantidadRegistrosDefault = 200;
    private Retrofit retrofit;

    private ServicioGeoref() {
        this.retrofit = new Retrofit.Builder()
                .baseUrl("https://api.mercadolibre.com/classified_locations/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static ServicioGeoref instancia(){
        if(instancia== null){
            instancia = new ServicioGeoref();
        }
        return instancia;
    }

    public List<Pais> listadoDePaises() throws IOException {
        GeorefService georefService = this.retrofit.create(GeorefService.class);
        Call<List<Pais>> requestListadoDePaises = georefService.paises();
        Response<List<Pais>> responseListadoDePaises = requestListadoDePaises.execute();
        List<Pais> listaPaises = responseListadoDePaises.body();
        ListadoDePaises listado = ListadoDePaises.GetInstance();

       listado.setPaises(listaPaises);

        return listaPaises;
    }

    public ListadoDeProvincias listadoDeProvinciasDelPais(Pais unPais) throws IOException {
        GeorefService georefService = this.retrofit.create(GeorefService.class);
        Call<ListadoDeProvincias> requestListadoDeProvincias = georefService.provincias(unPais.id);
        Response<ListadoDeProvincias> responseListadoDeProvincias = requestListadoDeProvincias.execute();
        ListadoDeProvincias listaDeProvincias = responseListadoDeProvincias.body();
        //ListadoDeProvincias listado = ListadoDeProvincias.GetInstance();

       //listado.setStates(listaDeProvincias);

        return listaDeProvincias;
    }

    public ListadoDeCiudades listadoDeCiudadesDeLaProvincia(Provincia unaProvincia) throws IOException {
        GeorefService georefService = this.retrofit.create(GeorefService.class);
        Call<ListadoDeCiudades> requestListadoDeCiudades = georefService.ciudades(unaProvincia.id);
        Response<ListadoDeCiudades> responseListadoDeCiudades = requestListadoDeCiudades.execute();
        ListadoDeCiudades listadoDeCiudades = responseListadoDeCiudades.body();
        return listadoDeCiudades;
    }



}
