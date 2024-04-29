package com.oop.majdb.Repos;
import com.oop.majdb.Entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;


public interface PostRepo extends CrudRepository<Post, Integer>,JpaRepository<Post, Integer>{
    Post findByPostID(int postID);
}
