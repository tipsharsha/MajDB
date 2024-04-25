package com.oop.majdb;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import java.util.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;





@Controller
@RequestMapping("")
public class UserController {

    @Autowired
    private PersonRepo personRepo;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginUser user) {
        // Process login logic here
        if(personRepo.findByEmailAndPassword(user.getEmail(), user.getPassword()) == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login Failed");
        }
        return ResponseEntity.status(HttpStatus.OK).body("Login Successful");
    }
    @Transactional
    @PostMapping("/signup")
    public ResponseEntity<String>  signup(@RequestBody SignupUser user) {
        personRepo.save(new Person(user.getUsername(), user.getEmail(), user.getPassword()));
        //Create a post for debugging purposes
        Optional<Person> personmaybe = Optional.ofNullable(personRepo.findByEmail(user.getEmail()));
        if(personmaybe.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Account Creation Failed");
        }
        personmaybe.get().addPost(new Post("This is a post", "date"));
        //Add comment
        personmaybe.get().getPosts().get(0).addComment(new Comment("This is a comment", new CommentCreator(1, "name")));
        // Redirect to a signup success page
        return ResponseEntity.status(HttpStatus.CREATED).body("Account Creation Successful");
    }


    @GetMapping("/user")
    public Person getUser(@RequestParam int UserID) {
        return personRepo.findByUserID(UserID);
    }


    @GetMapping("/")
    @ResponseBody
    public List<Post> getPosts(@RequestParam int UserID) {
        Optional<Person> per = Optional.ofNullable(personRepo.findByUserID(UserID));
        if(per.isEmpty()) {
            return new ArrayList<>();
        }
        Person p = (Person)per.get();
        return p.getPosts();
    }


}
