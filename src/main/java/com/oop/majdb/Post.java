package com.oop.majdb;


import jakarta.persistence.*;

import java.util.List;
import java.util.ArrayList;


@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int postID;
    private String postBody;
    private String date;


    public Post(String postBody, String date) {
        this.postBody = postBody;
        this.date = date;
        comments = new ArrayList<>();
    }

    // Constructors, getters, setters, and other methods...

    public Post(int postID, String postBody, String date, List<Comment> comments) {
        this.postID = postID;
        this.postBody = postBody;
        this.date = date;
    }



    @OneToMany(cascade = CascadeType.ALL)
    private List<Comment> comments;


    public Post() {
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public int getPostID() {
        return postID;
    }

    public String getPostBody() {
        return postBody;
    }

    public String getDate() {
        return date;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setPostID(int postID) {
        this.postID = postID;
    }

    public void setPostBody(String postBody) {
        this.postBody = postBody;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void addComment(Comment comment) {
        comments.add(comment);
    }



    @Override
    public String toString() {
        return "Post{" +
                "postID=" + postID +
                ", postBody='" + postBody + '\'' +
                ", date='" + date + '\'' +
                ", comments=" + comments +
                '}';
    }
}

