package com.springrest.rest.controller;

import com.springrest.rest.entities.User;
import com.springrest.rest.service.User_Detail;
import com.springrest.rest.sign.Signup;
import com.springrest.rest.sign_up.Sign_up_Detail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class MyController {

    @Autowired
    private User_Detail user_detail;

    @Autowired
    private Sign_up_Detail signup_detail;

    @GetMapping("/home")
    public String home() {
        return "Welcome to the API page";
    }

    @GetMapping("/sign_in")
    public List<User> getdata1() {
        // Assuming you have a specific user object to pass to getdata method
        // Modify accordingly based on your requirements
        User user = new User("samplePassword", "sample@email.com");
        return user_detail.getdata(user);
    }

    @PostMapping("/sign_in")
    public ResponseEntity<Map<String, String>> login(@RequestBody User user) {
        List<User> users = user_detail.getdata(user);
        Map<String, String> response = new HashMap<>();

        if (!users.isEmpty()) {
            response.put("message", "Login Successful");
            return ResponseEntity.ok(response);
        } else {
            response.put("error", "Invalid Email or Password");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }


    @GetMapping("/sign_up")
    public List<Signup> setdata() {
        return signup_detail.setdata();
    }

    @PostMapping("/sign_up")
    public Signup adddetail(@RequestBody Signup signup) {
        return signup_detail.adddetail(signup);
    }
}
