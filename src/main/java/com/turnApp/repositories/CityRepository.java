package com.turnApp.repositories;

import com.turnApp.entities.City;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository<City, String> {

    @Query("select q from City q where q.cityName LIKE :q")
    List<City> findAll(@Param("q") String q);

}
