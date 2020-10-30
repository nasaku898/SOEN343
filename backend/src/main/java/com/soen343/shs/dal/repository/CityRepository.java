package com.soen343.shs.dal.repository;

import com.soen343.shs.dal.model.City;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CityRepository extends CrudRepository<City, Long> {
    Optional<City> findByName(String name);
}
