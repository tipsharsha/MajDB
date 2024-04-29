package com.oop.majdb.Controllers;
import com.oop.majdb.Entities.Person;
import com.oop.majdb.Entities.Post;
import com.oop.majdb.Repos.PersonRepo;
import com.oop.majdb.Repos.PostRepo;
import com.oop.majdb.Response.DelPost;
import com.oop.majdb.Response.PatchPost;
import com.oop.majdb.Response.PostBody;
import com.oop.majdb.Services.ServicePost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import org.springframework.http.HttpStatus;

@Controller
@RequestMapping("/post")
public class ControlPost {
    private final ServicePost servicePost;

    public ControlPost(ServicePost servicePost) {
        this.servicePost = servicePost;
    }

    @PostMapping
    public ResponseEntity<Object> createPost(@RequestBody PostBody postBody) {
        return servicePost.createPost(postBody);
    }

    @GetMapping
    public ResponseEntity<Object> getPost(@RequestParam int postID) {
        return servicePost.getPost(postID);
    }


    @PatchMapping
    public ResponseEntity<Object> editPost(@RequestBody PatchPost postBody) {
        return servicePost.editPost(postBody);
    }

    @DeleteMapping
    public ResponseEntity<Object> deletePost(@RequestBody DelPost postID) {
        return servicePost.deletePost(postID);
    }

}
