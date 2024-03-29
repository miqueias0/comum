package br.com.mike.comum.serviceInterface;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/seguranca")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface IAutenticationService {

    @GET()
    @Path("/validarToken")
    Response validarToken(@QueryParam("token") String token) throws Exception;

    @POST
    @Path("/criarToken")
    Response criarToken(@QueryParam("id") String id, @QueryParam("tempoToken") Integer tempoToken) throws Exception;

    @POST
    @Path("/atualizarToken")
    Response atualizarToken(@QueryParam("token") String token) throws Exception;
}
