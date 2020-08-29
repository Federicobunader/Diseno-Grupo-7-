package Negocio;//package Negocio;


import API.APIGeografia.*;
import API.APIMoneda.Conversor;
import API.APIMoneda.ListadoDeMonedas;
import API.APIMoneda.Moneda;
import API.Servicios.CurrenciesServices;
import API.Servicios.ServicioGeoref;
import API.Servicios.ServicioMonedas;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Optional;

public class Main {
    public static void main(String[] args) throws IOException {

        ServicioGeoref servicioGeoref = ServicioGeoref.instancia();
        ServicioMonedas servicioMonedas = ServicioMonedas.instancia();

        //System.out.println("Seleccione una de las siguientes provincias, ingresando su id:");

        List<Pais> listadoPaises = servicioGeoref.listadoDePaises();

        List <Moneda> listadoMoneda = servicioMonedas.listadoDeMonedas();


        for(int i = 0 ; i < listadoMoneda.size(); i++){
            System.out.println(listadoMoneda.get(i).id + ") " + listadoMoneda.get(i).description);
        }

        System.out.println("Ingrese la ID de una Moneda");
        String idMonedaOrigen = pedirPorPantallaString();
        System.out.println("Ingrese la ID de la Moneda Destino");
        String idMonedaDestino = pedirPorPantallaString();

        ListadoDeMonedas monedas = ListadoDeMonedas.GetInstance();
        Optional<Moneda> posibleMonedaOrigen  = monedas.monedasPorId(idMonedaOrigen);

        Optional<Moneda> posibleMonedaDestino  = monedas.monedasPorId(idMonedaDestino);

        if(posibleMonedaOrigen.isPresent() && posibleMonedaDestino.isPresent()){
            Moneda monedaOrigen = posibleMonedaOrigen.get();
            Moneda monedaDestino = posibleMonedaDestino.get();

            Conversor conversion = servicioMonedas.conversorMonedas(monedaOrigen,monedaDestino);

            System.out.println(("El ratio de la conversion vale : "+ conversion.ratio + " Y el valor de Mercado Pago :"+ conversion.inv_rate));
        }




        for(int i = 0 ; i < listadoPaises.size(); i++){
            System.out.println(listadoPaises.get(i).id + ") " + listadoPaises.get(i).name);
        }



        System.out.println("Ingrese la ID de un Pais");
        String idPais = pedirPorPantallaString();

        ListadoDePaises paises = ListadoDePaises.GetInstance();
        Optional<Pais> posiblePais = paises.paisesDeId(idPais);

        if(posiblePais.isPresent()){
            Pais paisSeleccionado = posiblePais.get();
            ListadoDeProvincias listadoDeProvincias = servicioGeoref.listadoDeProvinciasDelPais(paisSeleccionado);
            System.out.println("Las Provincias del Pais "+ paisSeleccionado.name + " Con la ID : "+ paisSeleccionado.id + " son:");

            for(int i = 0 ; i < listadoDeProvincias.states.size(); i++){
                System.out.println(listadoDeProvincias.states.get(i).id + ") " + listadoDeProvincias.states.get(i).name);
            }

            System.out.println("Ingrese la ID de una Provincia");
            String idProvincia = pedirPorPantallaString();

            Optional<Provincia> posibleProvincia = listadoDeProvincias.provinciaDeId(idProvincia);

            if(posibleProvincia.isPresent()){
                Provincia provinciaSeleccionada = posibleProvincia.get();
                ListadoDeCiudades listadoDeCiudades = servicioGeoref.listadoDeCiudadesDeLaProvincia(provinciaSeleccionada);
                System.out.println("Las Ciudades de la provincia "+ provinciaSeleccionada.name + " Con la ID " + provinciaSeleccionada.id +" son:");
               // for(Ciudad unaCiudad: listadoDeCiudades.ciudades){
                  //  System.out.println(unaCiudad.id + ") " + unaCiudad.name);
               // }
                System.out.println("Tamaño Lista :" + listadoDeCiudades.cities.size());
                for(int i = 0 ; i < listadoDeCiudades.cities.size(); i++){
                    System.out.println(listadoDeCiudades.cities.get(i).id + ") " + listadoDeCiudades.cities.get(i).name);
                }
           }

            else{
                System.out.println("No existe la Provincia seleccionado");
            }
        }
        else{
            System.out.println("No existe el Pais seleccionado");
        }




        //Sistema sistema = Sistema.GetInstance();
        //sistema.arrancar();
    }

    public static String pedirPorPantallaString() {
        String res = "";
        try {
            BufferedReader entrada =
                    new BufferedReader(new InputStreamReader(System.in));
            res = entrada.readLine();
        }
        catch (IOException e) {}

        return res;
    }

    public static int pedirPorPantallaInt() {
        String dato = pedirPorPantallaString();

        int res = Integer.parseInt(dato);
        return res;
    }

    public static double pedirPorPantallaDouble() {
        String dato = pedirPorPantallaString();

        double res = Double.parseDouble(dato);
        return res;
    }
}

