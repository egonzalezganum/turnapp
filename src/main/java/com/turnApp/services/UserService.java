
package com.turnApp.services;

import com.turnApp.entities.User;
import com.turnApp.repositories.UserRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;
    
    public User save(User user) {
        
        return userRepository.save(user);
    }
    
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
    
}
