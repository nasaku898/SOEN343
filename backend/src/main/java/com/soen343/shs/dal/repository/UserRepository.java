package com.soen343.shs.dal.repository;

import com.soen343.shs.dal.model.RealUser;
import com.soen343.shs.dal.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    <Entity> Optional<Entity> findByUsername(Class<Entity> entityClass, String username);

    @Query(value = "SELECT * FROM USER WHERE EMAIL = ?1", nativeQuery = true)
    Optional<RealUser> findByEmail(String email);

    <Entity> Optional<Entity> findById(Class<Entity> classType, final long id);
}