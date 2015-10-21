package com.mavrov.repository;

import com.mavrov.entity.StandardEntity;

import javax.persistence.criteria.Predicate;
import java.math.BigInteger;
import java.util.List;

/**
 * @author serg.mavrov@gmail.com
 */
public interface Repository<DB_OBJECT extends StandardEntity> {

    DB_OBJECT findBy(Predicate... restrictions);

    List<DB_OBJECT> findAllBy(Predicate... restrictions);

    DB_OBJECT create(DB_OBJECT t);

    DB_OBJECT find(BigInteger id);

    DB_OBJECT update(DB_OBJECT t);

    void delete(DB_OBJECT t);

    void delete(BigInteger id) ;

    List<DB_OBJECT> findAll();

}
