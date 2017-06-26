package org.ijovic.demo.neo4j.repository;

import org.ijovic.demo.neo4j.entities.PersonEntity;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.neo4j.repository.query.GraphRepositoryQuery;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by ivanj on 11.06.2017.
 */
@Repository
public interface PersonRepository extends PagingAndSortingRepository<PersonEntity, Long> {

    PersonEntity findByFirstName(@Param("firstName") String firstName);


}
