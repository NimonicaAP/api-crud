package com.api.crud.controllers;

import com.api.crud.models.UserModel;
import com.api.crud.repositories.IUserRepository;
import com.api.crud.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public List<UserModel> getUsers(){
        return userService.getUsers();
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<UserModel> getUserById(@PathVariable Long id){
        try {
            UserModel modelo = userService.getUserById(id);
            return new ResponseEntity<UserModel>(modelo,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<UserModel>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/user")
    public UserModel saveUser(@RequestBody UserModel user ){
        return userService.saveUser(user);
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<?> updateUserById(@PathVariable Long id, @RequestBody UserModel user){
        try{
            UserModel modelo = userService.updateById(id, user);
            userService.saveUser(modelo);
            return new ResponseEntity<UserModel>(HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<UserModel>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/user/{id}")
    public void deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
    }
}
