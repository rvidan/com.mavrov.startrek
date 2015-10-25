package com.mavrov.repository;

import com.mavrov.entity.StandardEntity;
import org.jboss.logging.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.math.BigInteger;
import java.util.List;

/**
 * @author serg.mavrov@gmail.com
 */
public abstract class AbstractRepository<ENTITY extends StandardEntity> implements Repository<ENTITY> {

    private static final String HIBERNATE_CACHE = "org.hibernate.cacheable";

    @Inject
    private transient Logger logger;

    @Inject
    private EntityManager em;

    private CriteriaBuilder builder;
    private CriteriaQuery<ENTITY> selectCriteria;


    @PostConstruct
    public void initQueryTools() {
        builder = em.getCriteriaBuilder();
        selectCriteria = builder.createQuery(getEntityClass());
    }

    public abstract Class<ENTITY> getEntityClass();

    public CriteriaBuilder getBuilder() {
        return builder;
    }

    protected Predicate createPredicate(String fieldName, Object parameter) {
        Root<ENTITY> from = selectCriteria.from(getEntityClass());
        return builder.equal(from.get(fieldName), parameter);
    }

    @Override
    public ENTITY findBy(Predicate... restrictions) {
        try {
            selectCriteria.where(restrictions);
            TypedQuery<ENTITY> typedQuery = em.createQuery(selectCriteria);
            typedQuery.setHint(HIBERNATE_CACHE, true);
            return typedQuery.getSingleResult();
        } catch (NoResultException nre) {
            logger.warn("there is no object by provided predicate");
            return null;
        }
    }


    @Override
    public List<ENTITY> findAllBy(Predicate... restrictions) {
        selectCriteria.where(restrictions);
        TypedQuery<ENTITY> typedQuery = em.createQuery(selectCriteria);
        typedQuery.setHint(HIBERNATE_CACHE, true);
        return typedQuery.getResultList();
    }

    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public ENTITY create(ENTITY t) {
        em.persist(t);
        logger.debug("The " + t + " created.");
        return t;
    }

    @Override
    public ENTITY find(BigInteger id) {
        ENTITY t = em.find(getEntityClass(), id);
//        if (t == null) {
//            throw new NoResultException("There is no " + getEntityClass().getSimpleName() +
//                    " with id = " + id);
//        }
        return t;
    }

    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public ENTITY update(ENTITY t) {
        return em.merge(t);
    }

    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public void delete(ENTITY t) {
        ENTITY tUp = update(t);
        em.remove(tUp);
    }

    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public void delete(BigInteger id) {
        ENTITY t = find(id);
        delete(t);
    }

    @Override
    public List<ENTITY> findAll() {
        Root<ENTITY> from = selectCriteria.from(getEntityClass());
        selectCriteria.select(from);
        TypedQuery<ENTITY> typedQuery = em.createQuery(selectCriteria);
        typedQuery.setHint(HIBERNATE_CACHE, true);
        return typedQuery.getResultList();
    }

    @PreDestroy
    public void destroy() {
        if (em.isOpen()) {
            em.close();
        }
    }

}
