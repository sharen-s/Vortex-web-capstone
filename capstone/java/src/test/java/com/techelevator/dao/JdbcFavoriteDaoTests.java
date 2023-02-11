package com.techelevator.dao;

import com.techelevator.model.Favorite;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

public class JdbcFavoriteDaoTests extends BaseDaoTests {
    protected final Favorite FAVORITE_1 = new Favorite(1,1);
    protected final Favorite FAVORITE_2 = new Favorite(1,2);

    private JdbcFavoriteDao sut;

    @Before
    public void setup(){
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        sut = new JdbcFavoriteDao(jdbcTemplate);
    }

    @Test
    public void assert_correct_favorites_retrieved_by_id(){
        List<Favorite> expected = new ArrayList<>();
        expected.add(FAVORITE_1);
        expected.add(FAVORITE_2);
        List<Favorite> actual = sut.getFavoritesByUserId(1);
        Assert.assertEquals(expected.size(), actual.size());
        Assert.assertEquals(expected.get(0).getUserId(), actual.get(0).getUserId());
        Assert.assertEquals(expected.get(1).getFavoriteId(), actual.get(1).getFavoriteId());
    }



}
