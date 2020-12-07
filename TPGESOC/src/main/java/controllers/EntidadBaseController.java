package controllers;

import Negocio.Entidad.EntidadBase;
import Negocio.Usuario.DireccionPostal;
import Negocio.Usuario.Usuario;
import repositories.Repositorio;
import repositories.factories.FactoryRepositorio;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;

public class EntidadBaseController {

        private Repositorio<EntidadBase> repo;

        public EntidadBaseController() {
            this.repo = FactoryRepositorio.get(EntidadBase.class);
        }

        private void asignarAtributosA(EntidadBase entidadBase, Request request) {
            if (request.queryParams("NombreFicticio") != null) {
                entidadBase.setNombre(request.queryParams("NombreFicticio"));
            }

            if (request.queryParams("EntidadJuridica") != null) {
                entidadBase.setEntidadJuridica(request.queryParams("EntidadJuridica"));
            }

            if (request.queryParams("Descripcion") != null) {
                entidadBase.setDescripcion(request.queryParams("Descripcion"));
            }

        }

        public Response guardar(Request request, Response response){

            UsuarioController usuarioController = new UsuarioController();
            Usuario unUsuario = new Usuario();
            Map<String, Object> parametros = new HashMap<>();

            if(usuarioController.elUsuarioSePuedeRegistrarCorrectamente(unUsuario,request)){
                EntidadBase entidadBase = new EntidadBase();
                DireccionPostal direccionPostal = new DireccionPostal();

                DireccionPostalController direccionPostalController = new DireccionPostalController();
                direccionPostalController.asignarAtributosA(direccionPostal,request);

                Repositorio<DireccionPostal> repoDireccion = FactoryRepositorio.get(DireccionPostal.class);
                repoDireccion.agregar(direccionPostal);


                usuarioController.asignarAtributosA(unUsuario,request);

                Repositorio<Usuario> repoUsuario = FactoryRepositorio.get(Usuario.class);
                repoUsuario.agregar(unUsuario);

                unUsuario.setDireccionPostal(direccionPostal);
                entidadBase.setUsuario(unUsuario);

                asignarAtributosA(entidadBase,request);
                this.repo.agregar(entidadBase);


            }
            else {
                parametros.put("falloAlRegistrarse",true);
               // return new ModelAndView (parametros,"GESOC_Login.hbs");

            }
            response.redirect("/menu_login");
            return response;

        }



}
