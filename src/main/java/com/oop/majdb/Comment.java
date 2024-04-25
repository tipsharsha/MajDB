package com.oop.majdb;
import jakarta.persistence.*;
import java.util.ArrayList;

import java.util.List;

@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int commentID;
    private String commentBody;

    @Embedded
    private CommentCreator commentCreator;

    public Comment() {
    }

    public Comment(String commentBody, CommentCreator commentCreator) {
        this.commentBody = commentBody;
        this.commentCreator = commentCreator;
    }

    public int getCommentID() {
        return commentID;
    }

    public String getCommentBody() {
        return commentBody;
    }

    public CommentCreator getCommentCreator() {
        return commentCreator;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "commentID=" + commentID +
                ", commentBody='" + commentBody + '\'' +
                ", commentCreator=" + commentCreator +
                '}';
    }

}

@Embeddable
class CommentCreator {

    private int userID;
    private String name;

    public CommentCreator() {
    }

    public CommentCreator(int userID, String name) {
        this.userID = userID;
        this.name = name;
    }

    public int getUserID() {
        return userID;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "CommentCreator{" +
                "userID=" + userID +
                ", name='" + name + '\'' +
                '}';
    }
}
