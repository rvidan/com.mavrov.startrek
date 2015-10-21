package com.mavrov.repository;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Default;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author serg.mavrov@gmail.com
 */
@ApplicationScoped
public class EntityManagerProvider {

    @PersistenceContext(unitName = "STARTREK_PU")
    private EntityManager entityManager;


    @Produces
    @RequestScoped
    @Default
    public EntityManager produceEntityManager() {
        return entityManager;
    }

}