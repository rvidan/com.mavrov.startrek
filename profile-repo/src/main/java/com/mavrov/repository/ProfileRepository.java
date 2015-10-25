package com.mavrov.repository;

import com.mavrov.entity.ProfileEntity;

/**
 * @author serg.mavrov@gmail.com
 */
public class ProfileRepository extends AbstractRepository<ProfileEntity>
        implements Repository<ProfileEntity> {

    @Override
    public Class<ProfileEntity> getEntityClass() {
        return ProfileEntity.class;
    }

    public ProfileEntity findByEmail(String email) {
        return findBy(createPredicate("email", email));
//        return new ProfileEntity("serg.mavrov@gmail.com", "Sergii", "Mavrov", "JavaDev", "Amadeus");
    }

}
