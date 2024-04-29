package com.oop.majdb.Response;

import com.oop.majdb.Entities.Comment;

import java.util.List;

public class PostRes {
    private final int postID;
    private final String postBody;
    private final String date;
    private final List<Comment> comments;

    public PostRes(int postID, String postBody, String date, List<Comment> comments) {
        this.postID = postID;
        this.postBody = postBody;
        this.date = date;
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
}
