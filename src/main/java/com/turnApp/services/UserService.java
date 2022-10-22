package com.turnApp.services;

import com.turnApp.Exceptions.WebExceptions;
import com.turnApp.entities.User;
import com.turnApp.repositories.UserRepository;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;
    
    @Transactional
    public User save(String username, String password, String password2) throws WebExceptions {
        
        User user = new User();
        if (username == null || username.isEmpty()) {
            throw new WebExceptions("El nombre de usuario no puede estar vacío");
        }
        if (password == null || password.isEmpty()) {
            throw new WebExceptions("El password no puede estar vacío");
        }
        if (password2 == null || password2.isEmpty()) {
            throw new WebExceptions("El password2 no puede estar vacío");
        }
        if (!password.equals(password2)) {
            throw new WebExceptions("Las contraseñas deben ser iguales");
        }
        
        user.setUsername(username);
        user.setPassword(password);
        
        return userRepository.save(user);
    }
    
}
