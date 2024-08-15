package com.bigcorp.booking.projet.dao;

import com.bigcorp.booking.projet.model.Clients;
import jakarta.ejb.Stateless;
import jakarta.ejb.TransactionAttribute;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.util.List;

@Stateless
public class ClientsDao {

    @PersistenceContext
    private EntityManager entityManager;

    // Method to create or update a client
    @TransactionAttribute
    public Clients save(Clients client) {
        return entityManager.merge(client);
    }

    // Method to find a client by Id
    public Clients findClientById(Integer id) {
        return entityManager.find(Clients.class, id);
    }

    //Method to find all the clients
    public List<Clients> findAll() {
        return this.entityManager.createQuery("""
                select distinct c from Clients c
                """, Clients.class).getResultList();
    }

    //delete Client by Id
    @TransactionAttribute
    public void deleteClientById(Integer id) {
        Clients client = entityManager.find(Clients.class, id);
        if(client == null) {
            return;
        }
        entityManager.remove(client);
    }

    //methode pour recuperer client à partir du lastName
    public List<Clients> findClientByName(String lastName) {
        TypedQuery<Clients> query = entityManager.createQuery("""
                select c from Clients c
                where upper(c.lastName) = upper(:lastName)
                """, Clients.class);
        query.setParameter("lastName", lastName);
        return query.getResultList();
    }

    //methode pour retrouver client à partir like lastName
    public List<Clients> findClientByNameLike(String lastName) {
        TypedQuery<Clients> query = entityManager.createQuery("""
                select c from Clients c
                where upper(c.lastName) like upper(:lastName)
                """, Clients.class);
        query.setParameter("lastName", "%" + lastName + "%");
        return query.getResultList();
    }

    //methode pour trouver la reservation par email à partir de la Class reservation
    public List<Clients> findReservationByEmail(String emailConfirmation) {
        TypedQuery<Clients> query = entityManager.createQuery("""
                select c from Clients c
                inner join fetch c.reservation resa
                where lower(resa.emailConfirmation) = lower(:emailConfirmation)
                """, Clients.class);
                query.setParameter("emailConfirmation", emailConfirmation);
        return query.getResultList();
    }

}