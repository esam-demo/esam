package com.esam.repository;

import com.esam.model.Request;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author trutao
 * @create 2020-11-24 16:02
 */

public interface RequestRepository extends CrudRepository<Request, Long> {

    List<Request> findByResourceId(String resouceId);

    Request findByIdAndResourceId(Long Id, String resouceId);

    Optional<Request> findByRequestedForAndResourceId(String requestedFor, String resouceId);

    @Modifying
    @Query("update Request r set r.status=?2 where  r.id=?1")
    void updateStatusById(Long id, String status);

}
