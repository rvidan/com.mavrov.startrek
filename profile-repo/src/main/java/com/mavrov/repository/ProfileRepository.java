package com.mavrov.repository;

import com.mavrov.entity.ProfileEntity;
import org.jboss.logging.Logger;

import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 * @author serg.mavrov@gmail.com
 */
public class ProfileRepository extends AbstractRepository<ProfileEntity>
        implements Repository<ProfileEntity> {

    @Inject
    private transient Logger logger;

    @Override
    public Class<ProfileEntity> getEntityClass() {
        return ProfileEntity.class;
    }

    public ProfileEntity findByEmail(String email) {
        try {
            CriteriaQuery<ProfileEntity> criteria = getBuilder().createQuery(ProfileEntity.class);
            Root<ProfileEntity> root = criteria.from(ProfileEntity.class);
            criteria.select(root);
            criteria.where(getBuilder().equal(root.get("email"), email));
            return getEm().createQuery(criteria).getSingleResult();
        } catch (NoResultException nre) {
            logger.warn("there is no user with email " + email + " in the database.", nre);
            return null;
        }

//        return new ProfileEntity("serg.mavrov@gmail.com", "Sergii", "Mavrov", "JavaDev", "Amadeus");
    }
}
