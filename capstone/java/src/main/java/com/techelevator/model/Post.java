package com.techelevator.model;

import java.time.LocalDateTime;

public class Post {
    private int postId;
    private int userId;
    private String username;
    private int forumId;
    private String title;
    private String body;
    private String img_url;
    private LocalDateTime postedAt;
    private int upVotes;
    private int downVotes;

    public Post() {
    }

    public Post(int postId, int userId, int forumId, int upVotes, int downVotes) {
        this.postId = postId;
        this.userId = userId;
        this.forumId = forumId;
        this.upVotes = upVotes;
        this.downVotes = downVotes;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public String getUsername() {return username;}

    public int getForumId() {
        return forumId;
    }

    public void setForumId(int forumId) {
        this.forumId = forumId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public LocalDateTime getPostedAt() {
        return postedAt;
    }

    public void setPostedAt(LocalDateTime postedAt) {
        this.postedAt = postedAt;
    }

    public int getUpVotes() {
        return upVotes;
    }

    public void setUpVotes(int upVotes) {
        this.upVotes = upVotes;
    }

    public int getDownVotes() {
        return downVotes;
    }

    public void setDownVotes(int downVotes) {
        this.downVotes = downVotes;
    }
}
