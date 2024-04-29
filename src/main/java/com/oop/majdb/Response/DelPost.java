package com.oop.majdb.Response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DelPost {
    int postID;

    public DelPost(@JsonProperty("postID") int postID) {
        this.postID = postID;
    }


    public int getPostID() {
        return postID;
    }
}
