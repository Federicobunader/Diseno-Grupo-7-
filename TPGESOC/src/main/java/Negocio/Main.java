package Negocio;//package Negocio;


import API.APIGeografia.ListadoDePaises;
import API.APIGeografia.Pais;
import API.Servicios.ServicioGeoref;
import Negocio.Compras.Compra;
import Negocio.Compras.Item;
import Negocio.Compras.Producto;
import Negocio.Entidad.Empresa.Empresa;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {

        ServicioGeoref servicioGeoref = ServicioGeoref.instancia();

        System.out.println("Seleccione una de las siguientes provincias, ingresando su id:");

        ListadoDePaises listadoPaises = servicioGeoref.listadoDePaises();

        System.out.println("PRUEBA 0");

        for(Pais unPais:listadoPaises.paises){
            System.out.println(unPais.id + ") " + unPais.name);
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

