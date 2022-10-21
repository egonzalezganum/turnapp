package com.turnApp.repositories;

import com.turnApp.entities.City;
import com.turnApp.entities.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository<City, String> {

    @Query("select c from City c where c.cityName LIKE :q")
    List<User> findAll(@Param("q") String q);

}
