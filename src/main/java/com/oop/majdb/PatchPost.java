package com.oop.majdb;

public class PatchPost {
    private String postBody;
    private String postID;

    public PatchPost(String postBody, String postID) {
        this.postBody = postBody;
        this.postID = postID;
    }

    public String getPostBody() {
        return postBody;
    }

    public void setPostBody(String postBody) {
        this.postBody = postBody;
    }

    public String getPostID() {
        return postID;
    }

    public void setPostID(String postID) {
        this.postID = postID;
    }

    @Override
    public String toString() {
        return "PatchPost{" +
                "postBody='" + postBody + '\'' +
                ", postID='" + postID + '\'' +
                '}';
    }
}
