package com.techelevator.model;

public class Favorite {

    private int userId;
    private int favoriteId;

    public Favorite() {
    }

    public Favorite(int userId, int favoriteId) {
        this.userId = userId;
        this.favoriteId = favoriteId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getFavoriteId() {
        return favoriteId;
    }

    public void setFavoriteId(int favoriteId) {
        this.favoriteId = favoriteId;
    }
}
