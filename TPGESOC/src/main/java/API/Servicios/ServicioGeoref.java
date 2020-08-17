package API.Servicios;

import API.APIGeografia.ListadoDePaises;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;

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

    public ListadoDePaises listadoDePaises() throws IOException {
        GeorefService georefService = this.retrofit.create(GeorefService.class);
        Call<ListadoDePaises> requestListadoDePaises = georefService.paises();
        Response<ListadoDePaises> responseListadoDePaises = requestListadoDePaises.execute();
        ListadoDePaises paises = responseListadoDePaises.body();
        return paises;
    }
/*
    public ListadoDeMunicipios listadoDeMunicipiosDeProvincia(Provincia provincia) throws IOException {
        GeorefService georefService = this.retrofit.create(GeorefService.class);
        Call<ListadoDeMunicipios> requestListadoDeMunicipios = georefService.municipios(provincia.id, "id, nombre", maximaCantidadRegistrosDefault);
        Response<ListadoDeMunicipios> responseListadoDeMunicipios = requestListadoDeMunicipios.execute();
        ListadoDeMunicipios listadoDeMunicipios = responseListadoDeMunicipios.body();
        return listadoDeMunicipios;
    }

 */
}
