package com.turnApp.repositories;


import com.turnApp.entities.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    @Query("select u from User u where u.name LIKE :q or u.lastName LIKE :q or u.age LIKE :q or u.city.cityName LIKE :q")
    List<User> findAllByQ(@Param("q") String q);
    
    @Query("select u from User u where u.city.cityName = :q")
    List<User> findAllByCity(@Param("q") String q);
            
    
    
    
}
