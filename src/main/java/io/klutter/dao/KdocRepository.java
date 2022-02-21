package io.klutter.dao;

import io.klutter.entity.Kdoc;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KdocRepository extends CrudRepository<Kdoc, Long> {

    // Additional non-CRUD related methods.

}