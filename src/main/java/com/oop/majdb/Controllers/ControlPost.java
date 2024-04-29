package com.oop.majdb.Controllers;
import com.oop.majdb.Entities.Person;
import com.oop.majdb.Entities.Post;
import com.oop.majdb.Repos.PersonRepo;
import com.oop.majdb.Repos.PostRepo;
import com.oop.majdb.Response.DelPost;
import com.oop.majdb.Response.PatchPost;
import com.oop.majdb.Response.PostBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import org.springframework.http.HttpStatus;

@Controller
@RequestMapping("/post")
public class ControlPost {
    @Autowired
    PersonRepo personRepo;
    @Autowired
    PostRepo postRepo;

    @PostMapping
    public ResponseEntity<Object> createPost(@RequestBody PostBody postBody) {
        Optional<Person> personMaybe = Optional.ofNullable(personRepo.findByUserID(postBody.getUserID()));
        if(personMaybe.isEmpty()) {
            return new ResponseEntity<>(Map.of("Error","User does not exist"), HttpStatus.NOT_FOUND);
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
            return new ResponseEntity<>(Map.of("Error","Post does not exist"), HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.status(HttpStatus.OK).body(post);
    }


    @PatchMapping
    public ResponseEntity<Object> editPost(@RequestBody PatchPost postBody) {
        Post post = postRepo.findByPostID(postBody.getPostID());
        if(post == null) {
            return new ResponseEntity<>(Map.of("Error","Post does not exist"), HttpStatus.NOT_FOUND);
        }
        post.setPostBody(postBody.getPostBody());
        postRepo.save(post);
        return new ResponseEntity<>("Post edited successfully", HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<Object> deletePost(@RequestBody DelPost postID) {
        Post post = postRepo.findByPostID(postID.getPostID());
        if(post != null) {
            postRepo.deleteById(postID.getPostID());
            return ResponseEntity.status(HttpStatus.OK).body("Post deleted");
        }
        return new ResponseEntity<>(Map.of("Error","Post does not exist"), HttpStatus.NOT_FOUND);
    }

}
