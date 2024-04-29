package com.oop.majdb.Controllers;
import com.oop.majdb.Response.CommentBody;
import com.oop.majdb.Response.DelComment;
import com.oop.majdb.Response.PatchComment;
import com.oop.majdb.Services.ServiceComment;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.RestController;
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

    private final ServiceComment serviceComment;

    public ControlComment(ServiceComment serviceComment) {
        this.serviceComment = serviceComment;
    }

    @Transactional
    @PostMapping
    public ResponseEntity<Object> createComment(@RequestBody CommentBody commentBody) {
        return serviceComment.createComment(commentBody);
    }

    @GetMapping
    public ResponseEntity<Object> getComment(@RequestParam int commentID) {
        return serviceComment.getComment(commentID);
    }

    @PatchMapping
    public ResponseEntity<Object> editComment(@RequestBody PatchComment patchComment) {
        return serviceComment.editComment(patchComment);
    }
    @DeleteMapping
    public ResponseEntity<Object> deleteComment(@RequestBody DelComment delcomment) {
        return serviceComment.deleteComment(delcomment);
    }
}
