package com.turnApp.services;

import com.turnApp.Exceptions.WebExceptions;
import com.turnApp.entities.User;
import com.turnApp.repositories.UserRepository;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public User save(User user) throws Exception {
        if (user.getName().isEmpty() || user.getName() == null || user.getName().length()<3) {
            throw new WebExceptions("El campo nombre no puede estar vacío o tener menos de 3 caracteres");
        }
        if (user.getLastName().isEmpty() || user.getLastName() == null) {
            throw new WebExceptions("El campo apellido no puede estar vacío");
        }
        if (user.getAge() == null || user.getAge()<3) {
            throw new WebExceptions("El campo edad no puede estar vacío o ser menor a 3");
        }
        return userRepository.save(user);
    }

    @Transactional
    public User save(String name, String lastName, Integer age) {

        User user = new User();
        user.setName(name);
        user.setLastName(lastName);
        user.setAge(age);
        return userRepository.save(user);
    }

    public List<User> listAll() {
        return userRepository.findAll();
    }

    public Optional<User> findById(String id) {
        return userRepository.findById(id);
    }

    @Transactional
    public void delete(User user) {
        userRepository.delete(user);
    }

    @Transactional
    public void deleteById(String id) {
        Optional<User> optional = userRepository.findById(id);
        if (optional.isPresent()) {
            userRepository.delete(optional.get());
        }
    }

}
