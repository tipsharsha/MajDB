package com.oop.majdb.Entities;


import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.time.LocalDateTime;



@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int postID;
    private String postBody;
    private String date;
    public LocalDateTime createdAt = LocalDateTime.now();


    public Post(String postBody) {
        this.postBody = postBody;
        this.date = new SimpleDateFormat("yyyy-MM-dd").format(new Date(System.currentTimeMillis()));
        comments = new ArrayList<>();
    }


    @ManyToOne
    @JoinColumn(name = "person", referencedColumnName = "userID")
    private Person person;


    @OnDelete(action = OnDeleteAction.CASCADE)
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Comment> comments;


    public Post() {
        comments = new ArrayList<>();
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

    public void setPerson(Person person) {
        this.person = person;
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

