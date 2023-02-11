package com.techelevator.dao;


import com.techelevator.model.Favorite;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcFavoriteDao implements FavoriteDao{

    private final JdbcTemplate jdbcTemplate;

    public JdbcFavoriteDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public List<Favorite> getFavoritesByUserId(int id) {
        List<Favorite> favoriteForums = new ArrayList<>();
        String sql = "SELECT user_id, favorite_id  FROM favorite WHERE user_id =?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
        while (results.next()){
            favoriteForums.add(mapRowToFavorite(results));
        }
        return favoriteForums;
    }

    @Override
    public void createFavorite(Favorite favorite) {
        String sql = "INSERT INTO favorite (user_id, favorite_id) VALUES (?, ?);";
        jdbcTemplate.update(sql, favorite.getUserId(), favorite.getFavoriteId());
    }

    @Override
    public void deleteFavorite(Favorite favorite) {
        String sql = "DELETE FROM favorite WHERE favorite_id =? and user_id =?;";
        jdbcTemplate.update(sql, favorite.getFavoriteId(), favorite.getUserId());
    }


    private Favorite mapRowToFavorite(SqlRowSet rowSet){
        Favorite favorite = new Favorite();
        favorite.setUserId(rowSet.getInt("user_id"));
        favorite.setFavoriteId(rowSet.getInt("favorite_id"));
        return favorite;
    }

}
