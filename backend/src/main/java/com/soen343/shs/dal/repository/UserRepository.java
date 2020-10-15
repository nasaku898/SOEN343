package com.soen343.shs.dal.repository;

import com.soen343.shs.dal.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
