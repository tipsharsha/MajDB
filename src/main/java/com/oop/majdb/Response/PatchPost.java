package com.oop.majdb.Response;

public class PatchPost {
    private String postBody;
    private int postID;

    public PatchPost(String postBody, int postID) {
        this.postBody = postBody;
        this.postID = postID;
    }

    public String getPostBody() {
        return postBody;
    }

    public void setPostBody(String postBody) {
        this.postBody = postBody;
    }

    public int getPostID() {
        return postID;
    }

    public void setPostID(int postID) {
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
