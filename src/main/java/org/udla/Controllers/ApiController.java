package org.udla.Controllers;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.udla.Models.Contacto;
import org.udla.Utils.Util;


@Path("/hello")
public class ApiController {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response hello() {
        return Response.ok(Util.listaContacto).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{cedula}")
    public Response getContacto(@PathParam(value="cedula") String cedula ) {
        Contacto contacto = Util.listaContacto.stream().filter(x-> x.getCedula().equals(cedula)).findAny().orElse(null);
        if(contacto==null){
            return Response.ok(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(contacto).build();
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{cedula}")
    public Response deleteContacto(@PathParam(value="cedula") String cedula ) {
        Contacto contacto = Util.listaContacto.stream().filter(x-> x.getCedula().equals(cedula)).findAny().orElse(null);
        if(contacto==null){
            return Response.ok(Response.Status.NOT_FOUND).build();
        }
        Util.listaContacto.remove(contacto);
        return Response.ok("Contacto was successfully removed").build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response createContacto(Contacto contacto) {
        if(contacto==null){
            return Response.ok(Response.Status.BAD_REQUEST).build();
        }
        Util.listaContacto.add(contacto);
        return Response.ok(Response.Status.ACCEPTED).build();
    }
    //test
}