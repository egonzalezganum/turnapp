package com.turnApp.services;

import com.turnApp.Exceptions.WebExceptions;
import com.turnApp.entities.City;
import com.turnApp.entities.User;
import com.turnApp.repositories.CityRepository;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CityService {

    @Autowired
    private CityRepository cityRepository;
    
    @Autowired
    private UserService userService;

    
    
    public City save(String name) {
        City city = new City();
        city.setCityName(name);
        return cityRepository.save(city);
    }

    public City save(City city) throws WebExceptions {

        if (city.getCityName() == null) {
            throw new WebExceptions("El nombre de la ciudad no puede ser nula");
        }
        return cityRepository.save(city);
    }

    public List<City> listAll() {
        return cityRepository.findAll();
    }

    public List<User> listAll(String q) {
        return cityRepository.findAll("%" + q + "%");

    }

    public Optional<City> listByCityName(String city) {
        return cityRepository.findById(city);
    }

    public Optional<City> findById(String id) {
        return cityRepository.findById(id);
    }

    @Transactional
    public void delete(City city) {
    
        cityRepository.delete(city);
    }

    @Transactional
    public void deleteById(String id) {
        Optional<City> optional = cityRepository.findById(id);
        if (optional.isPresent()) {
            City city = optional.get(); 
            userService.deleteCityField(city);
            cityRepository.delete(city);
        }
    }

}
