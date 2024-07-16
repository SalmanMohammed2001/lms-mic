package com.lms.user_api.user_api.api;


import com.lms.user_api.user_api.dto.User;
import com.lms.user_api.user_api.service.UserService;
import org.json.JSONObject;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/create")
    public void createUser(@RequestBody User user){
        System.out.println("test");
        userService.createUser(user.getUsername(),user.getPassword(),user.getFirstName(),user.getLastName(),user.getEmail());
    }

    @PostMapping("/login")
    public Object login(@RequestParam String username, @RequestParam String password){
        System.out.println(username);
        System.out.println(password);
      return userService.login(username,password);
    }


    @GetMapping("/list")
    public List<UserRepresentation> finAllUser(){
      return   userService.findAllUsers();
    }

}
