package com.techelevator.controller;

import com.techelevator.dao.InteractionDao;
import com.techelevator.model.Interaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class InteractionController {

    @Autowired
    InteractionDao interactionDao;

    @GetMapping("/interactions/{id}")
    public List<Interaction> getInteractionsByUser(@PathVariable int id){
        return interactionDao.getInteractionsByUserId(id);
    }

    @PreAuthorize("isAuthenticated()")
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path="/interactions", method = RequestMethod.POST)
    public void createInteraction(@RequestBody Interaction interaction){
        interactionDao.createInteraction(interaction);
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(path = "/deleteInteractions",method = RequestMethod.DELETE)
    public void deleteInteraction(@RequestBody Interaction interaction){
        interactionDao.deleteInteraction(interaction);
    }

}
