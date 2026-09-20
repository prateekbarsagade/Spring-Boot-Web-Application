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
//    public List<User> getAllUser(){
//
//        List<User> all = userService.getAll();
//        if(all != null && !all.isEmpty()){
////            return new ResponseEntity<>(HttpStatus.OK);
//            return userService.getAll();
//        }
//
//        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }
    public List<User> getAllUser(){
        return userService.getAll();
    }


    @PostMapping("/create-admin")
    public ResponseEntity<?> createAdmin(@RequestBody User user){
        userService.createAdmin(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }




}
