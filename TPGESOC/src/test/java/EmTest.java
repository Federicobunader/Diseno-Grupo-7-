import API.APIGeografia.*;
import API.Servicios.ServicioGeoref;
import Negocio.Usuario.DireccionPostal;
import Negocio.Usuario.GestorDePasswords;
import Negocio.Usuario.Usuario;
import BaseDeDatos.EntityManagerHelper;
import org.junit.*;

import java.io.IOException;
import java.util.List;

public class EmTest {

    @Test
    public void persistirUsuario() {
        Usuario usuario = new Usuario();

        DireccionPostal direccion = new DireccionPostal();
        direccion.setAltura(100);
        direccion.setCalle("Gonzalo Miele");
        direccion.setCiudad("CABA");
        direccion.setDepartamento("A");
        direccion.setPais("Pais");
        direccion.setProvincia("Buenos Aires");
        direccion.setPiso(10);

        usuario.setUsuario("Gonza");
        String password = GestorDePasswords.GetInstance().hashearPassword("Miele");
        usuario.setPassword(password);
        usuario.setDireccionPostal(direccion);
        usuario.setHabilitado(true);
        usuario.setIntentos(5);

        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().persist(usuario);
        EntityManagerHelper.commit();

    }

    @Test
    public void select(){
        Usuario usuario = (Usuario) EntityManagerHelper.createQuery("from Usuario where id = '12'").getSingleResult();
        System.out.println("ID = "+ usuario.getId() + " ,Nombre = " + usuario.getUsuario());
        //Assert.assertEquals("Eze", eze.getNombre());
    }

    /*
    @Test
    public void persistirGeoRef() throws IOException {
        ServicioGeoref servicioGeoref = ServicioGeoref.instancia();

        List<Pais> listadoPaises = servicioGeoref.listadoDePaises();
        ListadoDeProvincias listadoProvincias;
        ListadoDeCiudades listadoDeCiudades;

        for(int i = 0; i < listadoPaises.size(); i++){

            listadoProvincias = servicioGeoref.listadoDeProvinciasDelPais(listadoPaises.get(i));

            for(int j = 0; j < listadoProvincias.states.size(); j++){

                listadoDeCiudades = servicioGeoref.listadoDeCiudadesDeLaProvincia(listadoProvincias.states.get(j));

                for(int k = 0; k < listadoDeCiudades.cities.size(); k++){

                    listadoDeCiudades.cities.get(k).setIdProvincia(listadoProvincias.states.get(j).id);
                    listadoDeCiudades.cities.get(k).setIdPais(listadoPaises.get(i).id);

                    EntityManagerHelper.beginTransaction();
                    EntityManagerHelper.getEntityManager().persist(listadoDeCiudades.cities.get(k));
                    EntityManagerHelper.commit();
                }

                listadoProvincias.states.get(j).setId_pais(listadoPaises.get(i).id);

                EntityManagerHelper.beginTransaction();
                EntityManagerHelper.getEntityManager().persist(listadoProvincias.states.get(j));
                EntityManagerHelper.commit();

            }


            EntityManagerHelper.beginTransaction();
            EntityManagerHelper.getEntityManager().persist(listadoPaises.get(i));
            EntityManagerHelper.commit();
        }
    }
/*
    @Test
    public void persistirProvincia() {
        ServicioGeoref servicioGeoref = ServicioGeoref.instancia();

        List<Pais> listadoPaises = servicioGeoref.listadoDeProvinciasDelPais();

        for(int i = 0; i < listadoPaises.size(); i++){
            EntityManagerHelper.beginTransaction();
            EntityManagerHelper.getEntityManager().persist(Pais);
            EntityManagerHelper.commit();
        }
    }

    @Test
    public void persistirCiudad() {
        ServicioGeoref servicioGeoref = ServicioGeoref.instancia();

        List<Pais> listadoPaises = servicioGeoref.listadoDeProvinciasDelPais();

        for(int i = 0; i < listadoPaises.size(); i++){
            EntityManagerHelper.beginTransaction();
            EntityManagerHelper.getEntityManager().persist(Pais);
            EntityManagerHelper.commit();
        }
    }
*/
}