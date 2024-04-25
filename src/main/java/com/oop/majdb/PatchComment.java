package com.oop.majdb;

public class PatchComment {
    private String commentBody;
    private String commentID;

    public PatchComment(String commentBody, String commentID) {
        this.commentBody = commentBody;
        this.commentID = commentID;
    }

    public String getCommentBody() {
        return commentBody;
    }

    public void setCommentBody(String commentBody) {
        this.commentBody = commentBody;
    }

    public String getCommentID() {
        return commentID;
    }

    public void setCommentID(String commentID) {
        this.commentID = commentID;
    }

    @Override
    public String toString() {
        return "PatchComment{" +
                "commentBody='" + commentBody + '\'' +
                ", commentID='" + commentID + '\'' +
                '}';
    }
}
