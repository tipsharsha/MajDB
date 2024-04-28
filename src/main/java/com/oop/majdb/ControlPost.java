package com.oop.majdb;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import org.springframework.http.HttpStatus;
import java.util.Date;
//Import Dateformatter to string
import java.text.SimpleDateFormat;



@Controller
@RequestMapping("/post")
public class ControlPost {
    @Autowired
    PersonRepo personRepo;
    @Autowired
    PostRepo postRepo;

    @PostMapping
    public ResponseEntity<String> createPost(@RequestBody PostBody postBody) {
        Optional<Person> personMaybe = Optional.ofNullable(personRepo.findByUserID(postBody.getUserID()));
        if(personMaybe.isEmpty()) {
            return ResponseEntity.status(404).body("User does not exist");
        }
        Person person = personMaybe.get();
        Post post = new Post(postBody.getPostBody());
        post.setPerson(person);
        person.addPost(post);
        personRepo.save(person);
        return ResponseEntity.status(HttpStatus.CREATED).body("Post created successfully");
    }

    @GetMapping
    public ResponseEntity<Object> getPost(@RequestParam int postID) {
        Post post  = postRepo.findByPostID(postID);
        if(post == null) {
            return new ResponseEntity<>("Post does not exist", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.status(HttpStatus.OK).body(post);
    }


    @PatchMapping
    public ResponseEntity<String> editPost(@RequestBody PatchPost postBody) {
        Post post = postRepo.findByPostID(postBody.getPostID());
        if(post == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Post does not exist");
        }
        post.setPostBody(postBody.getPostBody());
        postRepo.save(post);
        return new ResponseEntity<>("Post edited successfully", HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<String> deletePost(@RequestBody DelPost postID) {
        Post post = postRepo.findByPostID(postID.getPostID());
        if(post != null) {
            postRepo.deleteById(postID.getPostID());
            return ResponseEntity.status(HttpStatus.OK).body("Post deleted");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Post does not exist");
    }

}
