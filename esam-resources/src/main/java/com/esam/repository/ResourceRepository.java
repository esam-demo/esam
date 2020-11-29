package com.esam.repository;

import com.esam.model.Resource;
import org.springframework.data.repository.CrudRepository;

/**
 * @author trutao
 * @create 2020-11-26 16:54
 */

public interface ResourceRepository extends CrudRepository<Resource, Long> {
}
