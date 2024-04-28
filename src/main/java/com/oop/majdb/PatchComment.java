package com.oop.majdb;

public class PatchComment {
    private String commentBody;
    private int commentID;

    public PatchComment(String commentBody, int commentID) {
        this.commentBody = commentBody;
        this.commentID = commentID;
    }

    public String getCommentBody() {
        return commentBody;
    }

    public void setCommentBody(String commentBody) {
        this.commentBody = commentBody;
    }

    public int getCommentID() {
        return commentID;
    }

    public void setCommentID(int commentID) {
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
