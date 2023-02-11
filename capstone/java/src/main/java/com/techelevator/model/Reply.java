package com.techelevator.model;

import java.time.LocalDateTime;

public class Reply {
    private int commentId;
    private int userId;
    private int postId;
    private String body;
    private LocalDateTime postedAt;
    private String username;

    public Reply() {
    }

    public Reply(int commentId, int userId, int postId, String body) {
        this.commentId = commentId;
        this.userId = userId;
        this.postId = postId;
        this.body = body;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public LocalDateTime getPostedAt() {
        return postedAt;
    }

    public void setPostedAt(LocalDateTime postedAt) {
        this.postedAt = postedAt;
    }
}
