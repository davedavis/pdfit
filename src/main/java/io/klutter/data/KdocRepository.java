package io.klutter.data;

import io.klutter.models.Kdoc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KdocRepository extends JpaRepository<Kdoc, Long> {

    // Additional non-CRUD related methods.

}