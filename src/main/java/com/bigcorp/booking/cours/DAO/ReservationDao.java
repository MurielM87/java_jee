package com.bigcorp.booking.cours.DAO;

import com.bigcorp.booking.cours.model.Reservation;
import jakarta.ejb.Stateless;
import jakarta.ejb.TransactionAttribute;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.util.List;

@Stateless
public class ReservationDao {

    @PersistenceContext
    private EntityManager entityManager;

    @TransactionAttribute
    public Reservation save(Reservation reservation) {
        return entityManager.merge(reservation);
    }

    public Reservation findReservationById(Integer id) {
        return entityManager.find(Reservation.class, id);
    }

    @TransactionAttribute
    public void deleteReservationById(Integer id) {
        Reservation reservation = entityManager.find(Reservation.class, id);
        if (reservation != null) {
            entityManager.remove(reservation);
        }
    }
    //methode pour trouver par email dans la Class Reservation
    public List<Reservation> findByEmail(String email) {
        TypedQuery<Reservation> query = entityManager.createQuery("""
                    select resa from Reservation resa 
                    where UPPER(r.email) = UPPER(:email)
                """, Reservation.class);
        query.setParameter("email", email);
        return query.getResultList();
    }

    public List<Reservation> findByEmailLike(String email) {
        TypedQuery<Reservation> query = entityManager.createQuery("""
                    select resa from Reservation resa 
                    where LOWER(r.email) like LOWER(:email)
                """, Reservation.class);
        query.setParameter("email", "%" + email + "%");
        return query.getResultList();
    }

    //methode pour trouver toutes les reservations
    public List<Reservation> findAll() {
        return this.entityManager.createQuery("""
                    select resa from Reservation resa
                """, Reservation.class).getResultList();
    }

    //methode pour trouver reservation par nom à partir de la Class Client
    public List<Reservation> findClientByName(String lastName) {
        TypedQuery<Reservation> query = entityManager.createQuery("""
                    select resa from Reservation resa
                    inner join fetch resa.client
                    where upper(client.lastName) = upper(:lastName)
                """, Reservation.class);
        query.setParameter("lastName", lastName);
        return query.getResultList();
    }

    //methode pour trouver reservation à partir de l'id de la Class Client
    public List<Reservation> findClientById(Integer id) {
        TypedQuery<Reservation> query = entityManager.createQuery("""
                    select resa from Reservation resa
                    inner join fetch resa.client
                    where client.id = :id
                """, Reservation.class);
        query.setParameter("id", id);
        return query.getResultList();
    }

    //methode pour trouver toutes les reservations d'un client (et afficher les mails de contact d'un client)
    public List<Reservation> findAllReservationsByClientId(Integer id) {
        TypedQuery<Reservation> query = entityManager.createQuery("""
                select resa from Reservation resa
                left join fetch resa.client
                where client.id = :id
            """, Reservation.class);
        query.setParameter("id", id);
        return query.getResultList();
    }


}