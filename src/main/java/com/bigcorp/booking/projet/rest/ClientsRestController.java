package com.bigcorp.booking.projet.rest;

import com.bigcorp.booking.projet.rest.ClientsDto;
import com.bigcorp.booking.projet.service.ClientsService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/newclients")
@RequestScoped
public class ClientsRestController {
    @Inject
    private ClientsService clientsService;

    //requete GET
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<ClientsDto> listAll() {
        return this.clientsService.findAll();
    }

    //requete GET par {id}
//    @GET
//    @Path("/{id:[0-9][0-9]*}")
//    @Produces(MediaType.APPLICATION_JSON)
//    public ClientsDto getClientById(@PathParam("id") Integer id) {
//        return this.clientsService.findClientById(id);
//    }
    //requete GET par {id} avec message
    @GET
    @Path("/{id:[0-9][0-9]*}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getClientById(@PathParam("id") Integer id) {
        ClientsDto dto = this.clientsService.findClientById(id);
        if(dto == null) {
            Response maReponse = Response
                    .status(Response.Status.NOT_FOUND)
                    .entity("oups, il n'y a aucun client avec l'id " + id)
                    .build();
            return maReponse;
        }
        return Response.status(Response.Status.OK).entity(dto).build();
    }

    //requete DELETE by {id}
    @DELETE
    @Path("/{id:[0-9][0-9]*}")
    public void deleteById(@PathParam("id") Integer id) {
        this.clientsService.deleteById(id);
    }

    //requete POST
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ClientsDto save(@Valid ClientsDto clientsDto) {
        return this.clientsService.save(clientsDto);
    }
}
