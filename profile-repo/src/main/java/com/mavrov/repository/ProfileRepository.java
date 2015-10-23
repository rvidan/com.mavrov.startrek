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
        return new ProfileEntity("Sergii", "Mavrov", "JavaDev", "Amadeus");
    }

}
