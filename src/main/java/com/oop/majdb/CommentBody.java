package com.oop.majdb;

public class CommentBody {
    private String commentBody;
    private int userID;
    private int postID;

    public CommentBody(String commentBody, int userID, int postID) {
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

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getPostID() {
        return postID;
    }

    public void setPostID(int postID) {
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
