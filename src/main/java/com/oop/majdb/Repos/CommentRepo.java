package com.oop.majdb.Repos;

import com.oop.majdb.Entities.Comment;
import org.springframework.data.repository.CrudRepository;

public interface CommentRepo extends CrudRepository<Comment, Integer>{
    Comment findByCommentID(int commentID);
    //Delete post
}
