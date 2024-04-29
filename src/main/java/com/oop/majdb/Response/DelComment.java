package com.oop.majdb.Response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DelComment
{
    private final int commentID;

    public DelComment(@JsonProperty("commentID") int commentID) {
        this.commentID = commentID;
    }

    public int getCommentID() {
        return commentID;
    }
}
