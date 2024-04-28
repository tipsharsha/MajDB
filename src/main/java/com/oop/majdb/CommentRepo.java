package com.oop.majdb;

import org.springframework.data.repository.CrudRepository;

public interface CommentRepo extends CrudRepository<Comment, Integer>{
    Comment findByCommentID(int commentID);
    //Delete post
}