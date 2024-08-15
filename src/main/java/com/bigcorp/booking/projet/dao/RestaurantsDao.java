package com.bigcorp.booking.projet.dao;

import com.bigcorp.booking.projet.model.Restaurants;
import jakarta.ejb.Stateless;
import jakarta.ejb.TransactionAttribute;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.util.List;

@Stateless
public class RestaurantsDao {
    @PersistenceContext
    private EntityManager entityManager;

    public Restaurants findRestaurantById(Integer id) {
        Restaurants restaurant = entityManager.find(Restaurants.class, id);
        return restaurant;
    }

    //create a restaurant
    @TransactionAttribute
    public Restaurants save(Restaurants restaurant) {
        return entityManager.merge(restaurant);
    }

    //methode delete a restaurant by Id
    @TransactionAttribute
    public void deleteRestaurantById(Integer id) {
        Restaurants restaurant = entityManager.find(Restaurants.class, id);
        if(restaurant == null) {
            return;
        }
        entityManager.remove(restaurant);
    }

    //methode pour trouver un restaurant par le nom
    public List<Restaurants> findRestaurantByName(String name) {
        TypedQuery<Restaurants> query = entityManager.createQuery("""
            select resto from Restaurants resto
            where upper(resto.name) = upper(:name)
            """, Restaurants.class);
        query.setParameter("name", name);
        List<Restaurants> result = query.getResultList();
        return result;
    }

    //methode pour trouver un restaurant like name
    public List<Restaurants> findRestaurantByNameLike(String name) {
        TypedQuery<Restaurants> query = entityManager.createQuery("""
                select resto from Restaurants resto
                where upper(resto.name) like upper(:name)
                """, Restaurants.class);
        query.setParameter("name", "%" + name + "%");
        List<Restaurants> result = query.getResultList();
        return result;
    }


}
