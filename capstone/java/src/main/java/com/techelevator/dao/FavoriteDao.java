package com.techelevator.dao;

import com.techelevator.model.Favorite;

import java.util.List;

public interface FavoriteDao {

    List<Favorite> getFavoritesByUserId(int id);
    void createFavorite (Favorite favorite);
    void deleteFavorite (Favorite favorite);

}
