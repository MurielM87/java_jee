package com.bigcorp.booking.projet.dao;

import com.bigcorp.booking.projet.model.Reservations;
import jakarta.ejb.Stateless;
import jakarta.ejb.TransactionAttribute;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.time.LocalDate;
import java.util.List;

@Stateless
public class ReservationsDao {
    @PersistenceContext
    private EntityManager entityManager;

    public Reservations findReservationById(Integer id) {
        return entityManager.find(Reservations.class, id);
    }

    //create a reservation
    @TransactionAttribute
    public Reservations save(Reservations reservation) {
        return entityManager.merge(reservation);
    }

    //delete a reservation by id
    @TransactionAttribute
    public void deleteReservationById(Integer id) {
        Reservations reservation = entityManager.find(Reservations.class, id);
        if(reservation != null) {
            entityManager.remove(reservation);
        }
    }

    //methode find a reservation by Email
    public List<Reservations> findReservationByEmail(String emailConfirmation) {
        TypedQuery<Reservations> query = entityManager.createQuery("""
                select resa from Reservations resa
                where lower(resa.emailConfirmation) = lower(:emailConfirmation)
                """, Reservations.class);
        query.setParameter("emailConfirmation", emailConfirmation);
        return query.getResultList();
    }

    //method find a reservation by email like
    public List<Reservations> findReservationByEmailLike(String emailConfirmation) {
        TypedQuery<Reservations> query = entityManager.createQuery("""
                select resa from Reservations resa
                where lower(resa.emailConfirmation) like lower(:emailConfirmation)
                """, Reservations.class);
        query.setParameter("emailConfirmation", "%" + emailConfirmation + "%");
        return query.getResultList();
    }


}
