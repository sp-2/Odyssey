package com.example.odyssey.services;


import java.util.List;
import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.example.odyssey.models.User;
import com.example.odyssey.repositories.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;
    
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    // register user and hash their password
    public User registerUser(User user) {
        String hashed = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        user.setPassword(hashed);
        return userRepository.save(user);
    }
    
    // find user by email
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    
    // find user by id
    public User findUserById(Long id) {
    	Optional<User> user = userRepository.findById(id);
    	
    	if(user.isPresent()) {
            return user.get();
    	} else {
    	    return null;
    	}
    }
    
    // authenticate user
    public boolean authenticateUser(String email, String password) {
        // first find the user by email
        User user = userRepository.findByEmail(email);
        // if user cannot be found by email, return false
        if(user == null) {
            return false;
        } else {
            // if the passwords match, return true, else, return false
            if(BCrypt.checkpw(password, user.getPassword())) {
                return true;
            } else {
                return false;
            }
        }
    }
    
    // returns all the users
    public List<User> allUsers() {
        return userRepository.findAll();
    }
    
    // creates a user
    public User createUser(User b) {
        return userRepository.save(b);
    }
    
    // retrieves a user
    public User findUser(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if(optionalUser.isPresent()) {
            return optionalUser.get();
        } else {
            return null;
        }
    }
    
    // retrieves a user by name   
    public User findUser(String name) {
        User user = userRepository.findByUserName(name);
        if(user != null) {
            return user;
        } else {
            return null;
        }       
    }
    
    // updates a user    
    public User updateUser(Long id, String name, String email) {
        
        Optional<User> optionalUser = userRepository.findById(id);

        if(optionalUser.isPresent()) {
        	User b = optionalUser.get();       	
        	b.setUserName(name);
        	b.setEmail(email);
        	
        	return userRepository.save(b);
        } else {
            return null;
        }    	    	
    }
    
    // updates a user
    public User updateUser(User user) {
        return userRepository.save(user);
    }
    
    // deletes a user
    public void deleteUser(Long id) {       
        Optional<User> optionalUser = userRepository.findById(id);
        if(optionalUser.isPresent()) {
            userRepository.deleteById(id);
        } 
    }
}