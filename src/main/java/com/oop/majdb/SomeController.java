package com.oop.majdb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import org.springframework.http.HttpStatus;

@Controller
@RequestMapping("/post")
public class SomeController {
    //URL: /post
    //
    //To create a new post. Return relevant error if the user does not exist
    //Method: POST
    //Request Body: postBody<string>
    //userID<int>
    //Response: One of:
    //Post created successfully
    //User does not exist

    @Autowired
    PersonRepo personRepo;

    @PostMapping
    public ResponseEntity<String> createPost(@RequestBody PostBody postBody) {
        System.out.println(postBody.getUserID());
        if(personRepo.findByUserID(postBody.getUserID()) == null) {
            return ResponseEntity.status(404).body("User does not exist");
        }
        Person person = personRepo.findByUserID(postBody.getUserID());
        person.addPost(new Post(postBody.getPostBody(), "date"));
        return ResponseEntity.status(HttpStatus.CREATED).body("Post created successfully");
    }

    //To retrieve an existing post. Relevant error needs to be returned if the post does not exist
    //Method: GET
    //Query Parameter: postID
    //        (All the details about a specific post)
    //Response Body: One of:
    //<objects>
    //                                -postID <int>
    //                                -postBody <string>
    //                                -date<date>
    //                                -comments <object>
    //                                         -commentID<int>
    //                                         -commentBody<string>
    //                                         -commentCreator<Object>
    //                                                 -userID<int>
    //                                                 -name<str>
    //Post does not exist

    @GetMapping
    public Post getPost(@RequestParam int postID) {
        //return postService.getPost(postID);
        List <Comment> comments = new ArrayList<>();
        comments.add(new Comment( "commentBody", new CommentCreator(1, "name")));
        return new Post(1, "postBody", "date", comments);
    }

    //To edit an existing post. Relevant error needs to be returned if the post does not exist
    //Method: PATCH
    //Request Body: One of:
    //postBody<str>
    //postID<int>
    //Post does not exist

    @PatchMapping
    public String editPost(@RequestBody PatchPost postBody) {
        //return postService.editPost(postBody);
        return "Post edited successfully";
    }

    //To delete a post. Relevant error needs to be returned if the post does not exist
    //Method: DELETE
    //Request Body: postID<int>
    //Response: One of:
    //Post deleted
    //Post does not exist

    @DeleteMapping
    public String deletePost(@RequestParam int postID) {
        //return postService.deletePost(postID);
        return "Post deleted";
    }

}
