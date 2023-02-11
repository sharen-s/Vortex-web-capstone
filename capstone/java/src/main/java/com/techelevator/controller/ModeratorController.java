package com.techelevator.controller;

import com.techelevator.dao.ModeratorDao;
import com.techelevator.model.Moderator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class ModeratorController {

    @Autowired
    ModeratorDao moderatorDao;

    @GetMapping("/moderators/forum/{id}")
    public List<Moderator> getModeratorsByForum(@PathVariable int id){
        return moderatorDao.getModeratorsByForumId(id);
    }

    @GetMapping("/moderators/user/{id}")
    public List<Moderator> getModeratorsByUser(@PathVariable int id){
        return moderatorDao.getForumsByModeratorId(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path="/moderators", method = RequestMethod.POST)
    public void addModerator(@RequestBody Moderator moderator){
        moderatorDao.addModerator(moderator);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(path= "/moderators", method = RequestMethod.DELETE)
    public void removeModerator(@RequestBody Moderator moderator){
        moderatorDao.removeModerator(moderator);
    }


}
