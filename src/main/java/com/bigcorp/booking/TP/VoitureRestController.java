package com.bigcorp.booking.TP;

import jakarta.enterprise.context.RequestScoped;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;


@Path("/voitures")
@RequestScoped
public class VoitureRestController {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Voiture listAll() {
        System.out.println("methode listAll");
        return new Voiture();
    }

    @GET
    @Path("/{id:[0-9]-[0-9]*")
    @Produces(MediaType.APPLICATION_JSON)
    public Voiture getById(@PathParam("id") Long id) {
        System.out.println("methode getById " + id);
        return new Voiture();
    }

    @DELETE
    @Path("/{id:[0-9]-[0-9]*")
    public void deleteById(@PathParam("id") Long id) {
        System.out.println("methode delete " + id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Voiture save(Voiture voiture) {
        System.out.println("methode post");
        return voiture;
    }

}


/*
TP : création d'une nouvelle ressource HTTP :
Créer des webservices REST pour créer et mettre à jour, récupérer par son identifiant et
supprimer par son identifiant une ressource suivante :
Voiture :
    id : clé primaire
    numeroImmatriculation : chaîne de caractères
     actif : booléen
    dateImmatriculation : chaîne de caractères
Ne créer qu'une classe de POJO et une classe de RestController. LEs méthodes qui renvoient des
objets renvoient des objets 'bidons'
Toute méthode du RestController affiche dans la console qu'elle est appelée, et affiche les
arguments avec lesquels elle est appelée.
 */