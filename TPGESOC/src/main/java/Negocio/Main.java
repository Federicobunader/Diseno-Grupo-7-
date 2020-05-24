package Negocio;//package Negocio;


import Negocio.Entidad.Empresa.Empresa;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) {

        Empresa unaEmpresa = new Empresa(10,10,"Construccion");
        unaEmpresa.contratarPersonal(100);
        unaEmpresa.despedirPersonal(500);

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
}

