package com.turnApp.services;

import com.turnApp.Exceptions.WebExceptions;
import com.turnApp.entities.City;
import com.turnApp.entities.Person;
import com.turnApp.repositories.PersonRepository;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private CityService cityService;

    @Transactional
    public Person save(Person person) throws Exception {
        if (person.getName().isEmpty() || person.getName() == null || person.getName().length() < 3) {
            throw new WebExceptions("El campo nombre no puede estar vacío o tener menos de 3 caracteres");
        }
        if (person.getLastName().isEmpty() || person.getLastName() == null) {
            throw new WebExceptions("El campo apellido no puede estar vacío");
        }
        if (person.getAge() == null || person.getAge() < 3) {
            throw new WebExceptions("El campo edad no puede estar vacío o ser menor a 3");
        }
        if (person.getCity() == null) {
            throw new WebExceptions("El campo ciudad no puede estar vacío");
        } else {
            person.setCity(cityService.findById(person.getCity()));
        }
        return personRepository.save(person);
    }

//    @Transactional
//    public Person save(String name, String lastName, Integer age) {
//
//        Person person = new Person();
//        person.setName(name);
//        person.setLastName(lastName);
//        person.setAge(age);
//        return personRepository.save(person);
//    }
    public List<Person> listAll() {
        return personRepository.findAll();
    }

    public List<Person> listByQ(String q) {
        return personRepository.findAllByQ("%" + q + "%");
    }

    public List<Person> listByCityName(String city) {
        return personRepository.findAllByCity(city);
    }

    public Optional<Person> findById(String id) {
        return personRepository.findById(id);
    }

    @Transactional
    public void delete(Person person) {
        personRepository.delete(person);
    }

    @Transactional
    public void deleteCityField(City city) {
        List<Person> person = listByCityName(city.getCityName());
        for (Person person1 : person) {
            person1.setCity(null);
        }
        personRepository.saveAll(person);
    }

    @Transactional
    public void deleteById(String id) {
        Optional<Person> optional = personRepository.findById(id);
        if (optional.isPresent()) {
            personRepository.delete(optional.get());
        }
    }

}
