package io.klutter.data;

import io.klutter.models.Kdoc;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KdocRepository extends CrudRepository<Kdoc, Long> {

    // Additional non-CRUD related methods.
    @NotNull
    List<Kdoc> findAll();


}