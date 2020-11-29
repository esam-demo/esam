package com.esam.repository;

import com.esam.model.User;
import org.springframework.data.repository.CrudRepository;

/**
 * @author trutao
 * @create 2020-11-24 16:02
 */

public interface UserRepository extends CrudRepository<User, Integer> {
}
