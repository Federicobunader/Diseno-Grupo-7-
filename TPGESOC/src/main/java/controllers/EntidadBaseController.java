package controllers;

import Negocio.Entidad.Empresa.Empresa;
import Negocio.Entidad.Entidad;
import Negocio.Entidad.EntidadBase;
import Negocio.Usuario.DireccionPostal;
import Negocio.Usuario.Usuario;
import repositories.Repositorio;
import repositories.factories.FactoryRepositorio;
import spark.Request;
import spark.Response;

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

            EntidadBase entidadBase = new EntidadBase();
            Usuario unUsuario = new Usuario();
            DireccionPostal direccionPostal = new DireccionPostal();

            DireccionPostalController direccionPostalController = new DireccionPostalController();
            direccionPostalController.asignarAtributosA(direccionPostal,request);

            Repositorio<DireccionPostal> repoDireccion = FactoryRepositorio.get(DireccionPostal.class);
            repoDireccion.agregar(direccionPostal);

            UsuarioController usuarioController = new UsuarioController();
            usuarioController.asignarAtributosA(unUsuario,request);

            Repositorio<Usuario> repoUsuario = FactoryRepositorio.get(Usuario.class);
            repoUsuario.agregar(unUsuario);

            unUsuario.setDireccionPostal(direccionPostal);
            entidadBase.setUsuario(unUsuario);

            asignarAtributosA(entidadBase,request);
            this.repo.agregar(entidadBase);


            response.redirect("/menu_logueado");
            return response;
        }


}
