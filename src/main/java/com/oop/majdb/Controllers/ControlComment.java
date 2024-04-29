package com.oop.majdb.Controllers;
import com.oop.majdb.Entities.Comment;
import com.oop.majdb.Entities.Person;
import com.oop.majdb.Entities.Post;
import com.oop.majdb.Repos.CommentRepo;
import com.oop.majdb.Repos.PersonRepo;
import com.oop.majdb.Repos.PostRepo;
import com.oop.majdb.Response.CommentBody;
import com.oop.majdb.Response.DelComment;
import com.oop.majdb.Response.PatchComment;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/comment")
public class ControlComment {

    @Autowired
    private PersonRepo personRepo;
    @Autowired
    private PostRepo postRepo;
    @Autowired
    private CommentRepo commentRepo;

    @Transactional
    @PostMapping
    public ResponseEntity<Object> createComment(@RequestBody CommentBody commentBody) {
        Optional<Post> postMaybe= Optional.ofNullable(postRepo.findByPostID(commentBody.getPostID()));
        Optional<Person> personMaybe = Optional.ofNullable(personRepo.findByUserID(commentBody.getUserID()));
        if(postMaybe.isEmpty()) {
            return new ResponseEntity<>(Map.of("Error","Post does not exist"), HttpStatus.NOT_FOUND);
        }
        if(personMaybe.isEmpty()) {
            return new ResponseEntity<>(Map.of("Error","User does not exist"), HttpStatus.NOT_FOUND);
        }
        Person person = personMaybe.get();
        Post post = postMaybe.get();

        Comment comment = new Comment(commentBody.getCommentBody(), new Comment.CommentCreator(person.getUserID(), person.getName()));
        comment.setPost(post);
        post.addComment(comment);
        postRepo.save(post);
        return new ResponseEntity<>("Comment created successfully", HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Object> getComment(@RequestParam int commentID) {
       Optional<Comment> commentMaybe = Optional.ofNullable(commentRepo.findByCommentID(commentID));
        return commentMaybe.<ResponseEntity<Object>>map(comment -> new ResponseEntity<>(comment, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(Map.of("Error", "Comment does not Exist"), HttpStatus.NOT_FOUND));

    }

    @PatchMapping
    public ResponseEntity<Object> editComment(@RequestBody PatchComment patchComment) {
        Comment comment = commentRepo.findByCommentID(patchComment.getCommentID());
        if(comment == null) {
            return new ResponseEntity<>(Map.of("Error", "Comment does not Exist"), HttpStatus.NOT_FOUND);
        }
        comment.setCommentBody(patchComment.getCommentBody());
        commentRepo.save(comment);
        return new ResponseEntity<>("Comment edited successfully", HttpStatus.OK);
    }
    @DeleteMapping
    public ResponseEntity<Object> deleteComment(@RequestBody DelComment delcomment) {
        Comment comment = commentRepo.findByCommentID(delcomment.getCommentID());
        if(comment != null) {
            commentRepo.deleteById(delcomment.getCommentID());
            return new ResponseEntity<>("Comment deleted", HttpStatus.OK);
        }
        //Send JSON with Error: Comment does not exist
        String s = "Comment does not exist";
        return new ResponseEntity<>(Map.of("Error", "Comment does not Exist"), HttpStatus.NOT_FOUND);
    }
}
