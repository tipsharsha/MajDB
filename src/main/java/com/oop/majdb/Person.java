package com.oop.majdb;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int userID;

    private String name;
    private String email;
    private String password;

    @OnDelete(action = OnDeleteAction.CASCADE)
    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Post> posts;

    public Person() {
        this.posts = new ArrayList<>();
    }

    public Person(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.posts = new ArrayList<>();
    }

    public void addPost(Post post) {
        posts.add(post);
    }

    public List<Post> getPosts() {
        return posts;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public int getUserID() {
        return userID;
    }

    public String getName() {
        return name;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }
}
