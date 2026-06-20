package com.JournalApp.Controller;


import com.JournalApp.Entity.User;
import com.JournalApp.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    UserService userService;


    @GetMapping("/all-user")
    public ResponseEntity<?> getAllUser(){

        List<User> all = userService.getAll();
        if(all != null && all.isEmpty()){
            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @PostMapping("/create-admin")
    public ResponseEntity<?> createAdmin(@RequestBody User user){
        userService.createAdmin(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }




}
