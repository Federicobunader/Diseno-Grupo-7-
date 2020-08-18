package API.Servicios;

import API.APIGeografia.ListadoDeProvincias;
import API.APIGeografia.Pais;
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
        List<Pais> paises = responseListadoDePaises.body();
        return paises;
    }
/*
    public ListadoDeProvincias listadoDeProvincias() throws IOException {
        GeorefService georefService = this.retrofit.create(GeorefService.class);
        Call<ListadoDeProvincias> requestProvinciasArgentinas = georefService.provincias();
        Response<ListadoDeProvincias> responseProvinciasArgentinas = requestProvinciasArgentinas.execute();
        ListadoDeProvincias provinciasArgentinas = responseProvinciasArgentinas.body();
        return provinciasArgentinas;
    }

 */
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
