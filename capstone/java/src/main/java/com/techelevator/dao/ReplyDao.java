package com.techelevator.dao;

import com.techelevator.model.Reply;

import java.util.List;

public interface ReplyDao {
    List<Reply> getRepliesByPost(int postId);
    Reply getReplyById(int id);
    void createReply(Reply reply);
    void deleteReply(int id);
}
