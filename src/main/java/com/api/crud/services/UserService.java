package com.api.crud.services;

import com.api.crud.models.UserModel;
import com.api.crud.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    //Sirve para la inyeccion de dependencias
    @Autowired
    IUserRepository userRepository;

    public List<UserModel> getUsers(){
        return userRepository.findAll();
    }

    public UserModel saveUser(UserModel user){
        return userRepository.save(user);
    }

    public UserModel getUserById(Long id){
        return userRepository.findById(id).get();
    }

    public UserModel updateById( Long id, UserModel user){
        //Crear variable para llamar el id del registro
        UserModel model = userRepository.findById(id).get();
        model.setFirstName(user.getFirstName());
        model.setLastName(user.getLastName());
        model.setEmail(user.getEmail());
        return userRepository.save(model);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
