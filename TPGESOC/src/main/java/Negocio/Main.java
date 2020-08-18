package Negocio;//package Negocio;


import API.APIGeografia.Pais;
import API.Servicios.ServicioGeoref;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {

        ServicioGeoref servicioGeoref = ServicioGeoref.instancia();

        //System.out.println("Seleccione una de las siguientes provincias, ingresando su id:");

        List<Pais> listadoPaises = servicioGeoref.listadoDePaises();

        System.out.println("PRUEBA 0");

        for(int i = 0 ; i < listadoPaises.size(); i++){
            System.out.println(listadoPaises.get(i).id + ") " + listadoPaises.get(i).name);
/*
            System.out.println("PRUEBA 1");
            System.out.println(listadoPaises.get(i).provincias.);
            System.out.println(listadoPaises.get(i).provincias.size());
            System.out.println("PRUEBA 2");

            for(int j = 0 ; j < listadoPaises.get(i).provincias.size();j++){
                System.out.println(listadoPaises.get(i).provincias.get(j).id+ ") " + listadoPaises.get(i).provincias.get(j).name);
            }
            */

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

