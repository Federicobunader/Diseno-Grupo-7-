package Negocio.Compras;


import InterfazDeUsuario.InterfazUsuarios;

import javax.print.DocFlavor;
import javax.security.auth.login.CredentialException;
import java.util.ArrayList;
import Negocio.Compras.CategoriaItem;
import Negocio.Compras.CriterioDeItem;

public class GestorDeCriterios {

    private ArrayList<CriterioDeItem> criterios = new ArrayList<CriterioDeItem>();
    private InterfazUsuarios interfaz = InterfazUsuarios.GetInstance();
    private static GestorDeCriterios instance = null;

    public static GestorDeCriterios GetInstance() {
        if (instance == null)
            instance = new GestorDeCriterios();
        return instance;
    }

    public void agregarCriterio(String nombreCriterio) {
        if(nombreCriterio.isEmpty()) {
            nombreCriterio = interfaz.pedirString("Indique el nombre del nuevo criterio");
        }
        CriterioDeItem nuevoCriterio = new CriterioDeItem(nombreCriterio);
        criterios.add(nuevoCriterio);
    }

    public CriterioDeItem devolverCriterioParaEsaCategoria(CategoriaItem unaCategoria){
        for(int i = 0; i < criterios.size(); i++){
            if(criterios.get(i).getCategorias().contains(unaCategoria)) {
                return criterios.get(i);
            }
        }
        return null;
    }

    public void crearCategoria(){

        mostrarCriterios();
        String nombreDelCriterio = interfaz.pedirString("Ingrese el nombre del criterio al que desea agregar la categoria");
        String nombreDeLaCategoria = interfaz.pedirString("Ingrese el nombre de la Categoria");

        CategoriaItem unaCategoria = new CategoriaItem (nombreDelCriterio,nombreDeLaCategoria);

        boolean seEncontroLaCategoria = false;

        for(int i = 0;i< criterios.size();i++){
            if(criterios.get(i).getNombreCriterio().equalsIgnoreCase(nombreDelCriterio)) {
                if (!criterios.get(i).getCategorias().contains(unaCategoria)) {
                    criterios.get(i).agregarCategoriaAlCriterio(unaCategoria);
                    seEncontroLaCategoria = true;
                }
            }
        }

        if(seEncontroLaCategoria == false){
            interfaz.mostrarError("No existe el criterio ingresado");
        }
    }

    public void agregarCategoria() {
        int opcion = 1;
        while(opcion != 2){
            crearCategoria();
            interfaz.menuDeIngresarCategoria();
            opcion = interfaz.pedirInt("Ingrese la Opcion que desee");
        }
    }

    private void mostrarCriterios(){
        interfaz.mostrarInformacion("Estos son los criterios:");

        for (int i = 0; i < criterios.size(); i++) {
            interfaz.mostrarInformacion((i + 1) + "- " + criterios.get(i).getNombreCriterio());
        }
    }

    public void mostrarCategorias(){
        interfaz.mostrarInformacion("Estas son las categorias segun cada Criterio");
        for(int i = 0 ; i< criterios.size(); i++){
            interfaz.mostrarInformacion("Criterio " + (i+1) + ": " + criterios.get(i).getNombreCriterio());
            for(int j = 0 ; j< criterios.get(i).getCategorias().size();j++){
                interfaz.mostrarInformacion("   Categoria " + (j+1) + ": " + criterios.get(i).getCategorias().get(j).getNombreCategoria());
            }
        }
    }


}

