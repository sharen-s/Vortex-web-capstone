package com.techelevator.controller;

import com.techelevator.dao.PostDao;
import com.techelevator.model.Forum;
import com.techelevator.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
public class PostController {

    @Autowired
    PostDao postDao;

    @GetMapping("/forum/{forumId}/posts")
    public List<Post> getPostsByForumId(@PathVariable int forumId){
        return postDao.getPostsByForum(forumId);
    }
    

    // consider ? param for narrowing search results
    @GetMapping("/posts/search/{keyword}")
    public List<Post> searchByKeyword(@PathVariable String keyword){
        return postDao.getPostsByKeyword(keyword);
    }

    @GetMapping("/posts/top10")
    public List<Post> getTop10Posts(){
        return postDao.getTop10Posts();
    }

    @GetMapping("/posts/{id}")
    public List<Post> getPost(@PathVariable int id){
        List<Post> result = new ArrayList<>();
        result.add(postDao.getPostById(id));
        return result;
    }

    @PreAuthorize("isAuthenticated()")
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "/posts", method = RequestMethod.POST)
    public void createPost(@RequestBody Post post){
        postDao.createPost(post);
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping( path = "/posts/{id}", method = RequestMethod.PUT)
    public void updatePost(@PathVariable int id,
                           @RequestBody Post post){
        postDao.updatePost(id, post);
    }

    @PreAuthorize("isAuthenticated()")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping( path = "/posts/{id}", method = RequestMethod.DELETE)
    public void deletePost(@PathVariable int id){
        postDao.deletePost(id);
    }
}
