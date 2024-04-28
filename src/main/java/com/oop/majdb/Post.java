package com.oop.majdb;


import jakarta.persistence.*;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;


@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int postID;
    private String postBody;
    private Date date;


    public Post(String postBody, Date date) {
        this.postBody = postBody;
        this.date = date;
        comments = new ArrayList<>();

    }

    // Constructors, getters, setters, and other methods...

    public Post(int postID, String postBody, Date date, List<Comment> comments) {
        this.postID = postID;
        this.postBody = postBody;
        this.date = this.date;
    }

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Person person;



    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL,orphanRemoval = true)
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

    public Date getDate() {
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

    public void setDate(Date date) {
        this.date = date;
    }

    public Person getPerson() {
        return person;
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

