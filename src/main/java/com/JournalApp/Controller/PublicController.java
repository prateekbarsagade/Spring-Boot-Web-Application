package com.JournalApp.Controller;

import com.JournalApp.Entity.User;
import com.JournalApp.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    private UserService userService;

    @GetMapping("/health-check")
    public String healthCheck(){
        return "OK";
    }

    @PostMapping("/createUser")
    public User createUser(@RequestBody User user){
        return userService.saveNewEntry(user);
    }
}
