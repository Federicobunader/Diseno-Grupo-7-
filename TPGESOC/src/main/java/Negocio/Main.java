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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class Main {
    public static void main(String[] args) throws IOException {
        Sistema sistema = Sistema.GetInstance();
        sistema.arrancar();
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

