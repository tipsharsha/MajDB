package com.oop.majdb;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import java.util.List;
import org.springframework.data.jpa.repository.Query;


public interface PostRepo extends CrudRepository<Post, Integer>,JpaRepository<Post, Integer>{
    Post findByPostID(int postID);
    //Find all Posts by time of creation
//    List<Post> findAllByOrderByCreatedAtDesc();
    //Delete post
//    void deleteByPostID(int postID);
//    @Query("SELECT p FROM Post p ORDER BY p.createdAt DESC")
//    List<Post> findAllOrderByCreatedAtDesc();
    //Order Posts
}
