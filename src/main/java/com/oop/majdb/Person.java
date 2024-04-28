package com.oop.majdb;

import java.util.ArrayList;
import java.util.List;


import jakarta.persistence.OneToMany;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.hibernate.annotations.OnDelete;


@Entity
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int userID;

    private String name;
    private String email;
    private String password;

    public Person(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.posts = new ArrayList<>();
    }

//    @OnDelete(action = OnDeleteAction.CASCADE)
    @OneToMany( mappedBy = "person", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Post> posts;

    public void addPost(Post post) {
        posts.add(post);
    }

    public Person() {
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
}
