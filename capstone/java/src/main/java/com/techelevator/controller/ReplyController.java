package com.techelevator.controller;

import com.techelevator.dao.ReplyDao;
import com.techelevator.model.Reply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class ReplyController {

    @Autowired
    ReplyDao replyDao;


    @GetMapping("/posts/{postId}/replies")
    public List<Reply> getRepliesByPost(@PathVariable int postId){
        return replyDao.getRepliesByPost(postId);
    }

//    @GetMapping("/replies/{id}")
//    public Reply getReplyById(@PathVariable int id){return replyDao.getReplyById(id);}

    @PreAuthorize("isAuthenticated()")
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "/replies", method = RequestMethod.POST)
    public void createReply(@RequestBody Reply reply){replyDao.createReply(reply);}

    @PreAuthorize("isAuthenticated()")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(path = "/replies/{id}", method = RequestMethod.DELETE)
    public void deleteReply(@PathVariable int id){
        replyDao.deleteReply(id);
    }


}
