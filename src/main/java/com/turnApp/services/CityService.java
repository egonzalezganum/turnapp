package com.turnApp.services;

import com.turnApp.Exceptions.WebExceptions;
import com.turnApp.entities.City;
import com.turnApp.entities.Person;
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
    private PersonService personService;

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

    //    public City saveById(City city) throws WebExceptions {
    //        if (city.getId() == null) {
    //            throw new WebExceptions("Ocurrio un problema");
    //        } else {
    //            Optional<City> optional = cityRepository.findById(city.getId());
    //            if (optional.isPresent()) {
    //                city = optional.get();
    //            }
    //        }
    //        return save(city);
    //    }
    public List<City> listAll() {
        return cityRepository.findAll();
    }

    public List<City> listAll(String q) {
        return cityRepository.findAll("%" + q + "%");

    }

    public City findById(City city) {
        Optional<City> optional = cityRepository.findById(city.getId());
        if (optional.isPresent()) {
            city = optional.get();
        }
        return city;
    }

    public List<City> listByCityName(String city) {
        return cityRepository.findAll(city);
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
            personService.deleteCityField(city);
            cityRepository.delete(city);
        }
    }

}
