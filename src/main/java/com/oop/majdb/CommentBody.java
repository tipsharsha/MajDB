package com.oop.majdb;

public class CommentBody {
    private String commentBody;
    private String userID;
    private String postID;

    public CommentBody(String commentBody, String userID, String postID) {
        this.commentBody = commentBody;
        this.userID = userID;
        this.postID = postID;
    }

    public String getCommentBody() {
        return commentBody;
    }

    public void setCommentBody(String commentBody) {
        this.commentBody = commentBody;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getPostID() {
        return postID;
    }

    public void setPostID(String postID) {
        this.postID = postID;
    }

    @Override
    public String toString() {
        return "com.oop.majdb.CommentBody{" +
                "commentBody='" + commentBody + '\'' +
                ", userID='" + userID + '\'' +
                ", postID='" + postID + '\'' +
                '}';
    }
}
