package com.techelevator.controller;

import com.techelevator.dao.ForumDao;
import com.techelevator.model.Forum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class ForumController {

    @Autowired
    ForumDao forumDao;

    @GetMapping("/forum/top5")
    public List<Forum> findTopFive(){ return forumDao.findTopFive();}

    @GetMapping("/forum")
    public List<Forum> findAllForums() {return forumDao.findAllForums();}

    @GetMapping("/forum/{id}")
    public Forum getForumById(@PathVariable int id){return forumDao.findForumById(id);}

    @PreAuthorize("isAuthenticated()")
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "/forum", method = RequestMethod.POST)
    public Forum createForum(@RequestBody Forum forum){

        Forum newForum = forumDao.createForum(forum);
        return newForum;
    }

    @GetMapping("/forum/user/{id}")
    public List<Forum> favoriteForumsByUserId(@PathVariable int id){return forumDao.favoriteForumsByUserId(id);}

}
