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
        if(personRepo.findByEmail(user.getEmail())==null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User does not exist");
        }
        if(personRepo.findByEmailAndPassword(user.getEmail(), user.getPassword()) == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Username/Password Incorrect");
        }
        return ResponseEntity.status(HttpStatus.OK).body("Login Successful");
    }
    @PostMapping("/signup")
    public ResponseEntity<String>  signup(@RequestBody SignupUser user) {
        if(personRepo.findByEmail(user.getEmail())!=null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Forbidden, Account already exists");
        }
        personRepo.save(new Person(user.getUsername(), user.getEmail(), user.getPassword()));
        return ResponseEntity.status(HttpStatus.CREATED).body("Account Creation Successful");
    }


    @GetMapping("/user")
    @ResponseBody
    public ResponseEntity<Object> getUser(@RequestParam int userID) {
        Person person = personRepo.findByUserID(userID);
        if(person == null) {
            return new ResponseEntity<>("User does not exist", HttpStatus.NOT_FOUND);
        }
        PersonGet personGet = new PersonGet(person.getUserID(), person.getName(), person.getEmail());
        return new ResponseEntity<>(personGet, HttpStatus.OK);
    }


    @GetMapping("/")
    @ResponseBody
    public List<Post> getPosts()
    {
        List<Post> posts = (List<Post>) postRepo.findAll();
        for(int i  =0; i < posts.size(); i++) {
            for(int j = i+1; j < posts.size(); j++) {
                if(posts.get(i).createdAt.isBefore(posts.get(j).createdAt)) {
                    Post temp = posts.get(i);
                    posts.set(i, posts.get(j));
                    posts.set(j, temp);
                }
            }
        }
        return posts;


    }


    @GetMapping("/users")
    public @ResponseBody List<PersonGet> getUsers()
    {
        //Remove posts from all people
        List<Person> people = (List<Person>) personRepo.findAll();
        List<PersonGet> peopleGet = new ArrayList<>();
        for(Person person: people) {
            peopleGet.add(new PersonGet(person.getUserID(), person.getName(), person.getEmail()));
        }
        return peopleGet;
    }

}
