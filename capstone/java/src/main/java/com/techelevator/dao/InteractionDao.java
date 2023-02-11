package com.techelevator.dao;

import com.techelevator.model.Interaction;

import java.util.List;

public interface InteractionDao {

    List<Interaction> getInteractionsByUserId(int id);
    void createInteraction(Interaction interaction);
    void deleteInteraction (Interaction interaction);

}
