package com.maintenanceportal.user_service.controller;


import com.maintenanceportal.user_service.model.User;
import com.maintenanceportal.user_service.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/users")

public class UserController {

    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }




    @GetMapping
    public Page<User> getUsers(@RequestParam(defaultValue = "0") int page , @RequestParam(defaultValue = "10")int size ){
        Pageable pageable = PageRequest.of(page, size);
        return userService.getUserwithPagination(pageable);

    }
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return userService.getUsersById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable  Long id){
        return userService.deleteUser(id)
                ? ResponseEntity.ok("deleted successfully")
                :ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
    }
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id,@RequestBody User updatedUser){
        User user = userService.updateUser(id,updatedUser);
        return ResponseEntity.ok(user);


    }
    @PostMapping
    public ResponseEntity<User> addNewUser(@RequestBody User newUser){
        User createdUser = userService.addNewUser(newUser);
        System.out.println("Received user: " + newUser);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }



}

