package com.oop.majdb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import java.util.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


@Controller
@RequestMapping("")
public class ControlPerson {

    @Autowired
    private PersonRepo personRepo;
    @Autowired
    private PostRepo postRepo;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginUser user) {
        // Process login logic here
        if(personRepo.findByEmailAndPassword(user.getEmail(), user.getPassword()) == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login Failed");
        }
        return ResponseEntity.status(HttpStatus.OK).body("Login Successful");
    }
    @PostMapping("/signup")
    public ResponseEntity<String>  signup(@RequestBody SignupUser user) {
//        if(personRepo.findByEmail(user.getEmail())) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User Exists");
//        }
        personRepo.save(new Person(user.getUsername(), user.getEmail(), user.getPassword()));
        return ResponseEntity.status(HttpStatus.CREATED).body("Account Creation Successful");
    }


    @GetMapping("/user")
    @ResponseBody
    public Person getUser(@RequestParam int userID) {
        return personRepo.findByUserID(userID);
    }


    @GetMapping("/")
    @ResponseBody
    public List<Post> getPosts()
    {
        return postRepo.findAll();
    }

    @GetMapping("/users")
    @ResponseBody
    public List<Person> getUsers()
    {
        return (List<Person>) personRepo.findAll();
    }

}
