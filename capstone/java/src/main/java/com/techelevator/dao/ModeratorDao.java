package com.techelevator.dao;

import com.techelevator.model.Moderator;


import java.util.List;

public interface ModeratorDao {

    List<Moderator> getModeratorsByForumId(int id);
    List<Moderator> getForumsByModeratorId(int id);
    void addModerator(Moderator moderator);
    void removeModerator(Moderator moderator);
}
