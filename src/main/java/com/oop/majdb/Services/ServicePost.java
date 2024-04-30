package com.oop.majdb.Services;
import com.oop.majdb.Entities.Person;
import com.oop.majdb.Entities.Post;
import com.oop.majdb.Repos.PersonRepo;
import com.oop.majdb.Repos.PostRepo;
import com.oop.majdb.Response.DelPost;
import com.oop.majdb.Response.PatchPost;
import com.oop.majdb.Response.PostBody;
import com.oop.majdb.Response.PostRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.*;
import org.springframework.http.HttpStatus;

@Service
public class ServicePost {
    @Autowired
    PersonRepo personRepo;
    @Autowired
    PostRepo postRepo;


    public ResponseEntity<Object> createPost(PostBody postBody) {
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


    public ResponseEntity<Object> getPost(int postID) {
        Post post  = postRepo.findByPostID(postID);
        if(post == null) {
            return new ResponseEntity<>(Map.of("Error","Post does not exist"), HttpStatus.NOT_FOUND);
        }
        PostRes pos = new PostRes(post.getPostID(), post.getPostBody(),post.getDate(), post.getComments());
        return ResponseEntity.status(HttpStatus.OK).body(pos);
    }



    public ResponseEntity<Object> editPost(PatchPost postBody) {
        Post post = postRepo.findByPostID(postBody.getPostID());
        if(post == null) {
            return new ResponseEntity<>(Map.of("Error","Post does not exist"), HttpStatus.NOT_FOUND);
        }
        post.setPostBody(postBody.getPostBody());
        postRepo.save(post);
        return new ResponseEntity<>("Post edited successfully", HttpStatus.OK);
    }

    public ResponseEntity<Object> deletePost(DelPost postID) {
        Post post = postRepo.findByPostID(postID.getPostID());
        if(post != null) {
            postRepo.deleteById(postID.getPostID());
            return ResponseEntity.status(HttpStatus.OK).body("Post deleted");
        }
        return new ResponseEntity<>(Map.of("Error","Post does not exist"), HttpStatus.NOT_FOUND);
    }

}
