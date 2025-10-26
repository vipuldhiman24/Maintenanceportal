package com.maintenanceportal.user_service.service;

import com.maintenanceportal.user_service.model.User;
import com.maintenanceportal.user_service.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UserService {

    private final UserRepository userRepository;// finaly makes userepo immutable .ie once assigned construct it cant bechanges

    public UserService(UserRepository userRepository){ // constructor injection which is preffered as
        this.userRepository = userRepository;
    }

    public User createUser(User user){ //Create
        return userRepository.save(user);
    }
    public  boolean deleteUser(Long id){ //delete
        if(!userRepository.existsById(id)){
            return false;

        }
        userRepository.deleteById(id);
        return  true;
    }
    public Optional<User> getUsersById(Long id){
        return userRepository.findById(id);
    }
    public Page<User> getUserwithPagination(Pageable pageable){ //get uesres using pagination (Read)
        return userRepository.findAll(pageable);
    }
    public User updateUser(Long id, User updatedUser){ // update the user
        return userRepository.findById(id).
                map(user -> {
                    user.setName(updatedUser.getName());
                    user.setEmail(updatedUser.getEmail());
                    user.setPhone(updatedUser.getPhone());
                    user.setFlatNumber(updatedUser.getFlatNumber());
                    user.setRole(updatedUser.getRole());
                    return userRepository.save(user);
                }).orElseThrow(()-> new RuntimeException("User not found for id " + id));
    }

    public User addNewUser(User newUser) {
        return userRepository.save(newUser);
    }

}
