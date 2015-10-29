package com.mavrov.repository;

import com.mavrov.entity.StandardEntity;
import org.jboss.logging.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
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

    @PostConstruct
    public void initQueryTools() {
        builder = em.getCriteriaBuilder();
    }

    public abstract Class<ENTITY> getEntityClass();

    public EntityManager getEm() {
        return em;
    }

    public CriteriaBuilder getBuilder() {
        return builder;
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
        CriteriaQuery<ENTITY> selectCriteria = builder.createQuery(getEntityClass());
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
