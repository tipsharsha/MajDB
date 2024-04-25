package com.oop.majdb;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import java.util.*;



@RestController
@RequestMapping("/comment")
public class CommentController {


    //    @Autowired
    //    private CommentService commentService;


//    Endpoints related to comments on a post
//    URL: /comment
//
//    To create a new comment. Relevant error needs to be returned if the post does not exist
//    Method: POST
//    Request Body: commentBody<string>
//    postID<int>
//    userID<int>
//    Response: One of:
//    Comment created successfully
//    Comment does not exist
    @PostMapping
    public String createComment(@RequestBody CommentBody commentBody) {
        //return commentService.createComment(commentBody);
        return "Comment created successfully";
    }
//    To retrieve an existing comment. Relevant error needs to be returned if the comment does not exist
//    Method: GET
//    Response Body: One of:
//<object>
//                                -commentID<int>
//                                -commentBody<string>
//		        -commentCreator<Object>
//                                                 -userID<int>
//                                                 -name<str>
//    Comment does not exist
    @GetMapping
    public Comment getComment(@RequestParam int commentID) {
        //return commentService.getComment(commentID);
        return new Comment( "commentBody", new CommentCreator(1, "name"));
    }
//
//    To edit an existing comment. Relevant error needs to be returned if the comment does not exist
//    Method: PATCH
//    Body: One of:
//    commentBody<str>
//    commentID<int>
//    Comment does not exist
//
    @PatchMapping
    public String editComment(@RequestBody PatchComment commentBody) {
        //return commentService.editComment(commentBody);
        return "Comment edited successfully";
    }
//    To delete an existing comment.  Relevant error needs to be returned if the comment does not exist
//    Method: DELETE
//    Request Body: commentID<int>
//    Response Body: One of
//    Comment deleted
//    Comment does not exist
    @DeleteMapping
    public String deleteComment(@RequestBody int commentID) {
        //return commentService.deleteComment(commentID);
        return "Comment deleted";
    }




}
