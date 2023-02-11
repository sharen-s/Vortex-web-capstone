package com.techelevator.dao;

import com.techelevator.model.Moderator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

public class JdbcModeratorDaoTests extends BaseDaoTests{
    protected final Moderator MODERATOR_1 = new Moderator(1,1);
    protected final Moderator MODERATOR_2 = new Moderator(1,2);
    protected final Moderator MODERATOR_3 = new Moderator(2,2);

    private JdbcModeratorDao sut;

    @Before
    public void setup(){
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        sut = new JdbcModeratorDao(jdbcTemplate);
    }

    @Test
    public void assert_moderators_retrieved_by_user_id(){
        List<Moderator> expected = new ArrayList<>();
        expected.add(MODERATOR_1);
        expected.add(MODERATOR_2);
        List<Moderator> actual = sut.getForumsByModeratorId(1);
        Assert.assertEquals(expected.size(), actual.size());
        for (int i = 0; i < expected.size(); i++) {
            assertModeratorsMatch(expected.get(i), actual.get(i));
        }
    }

    @Test
    public void assert_moderators_retrieved_by_forum_id(){
        List<Moderator> expected = new ArrayList<>();
        expected.add(MODERATOR_2);
        expected.add(MODERATOR_3);
        List<Moderator> actual = sut.getModeratorsByForumId(2);
        Assert.assertEquals(expected.size(), actual.size());
        for (int i = 0; i < expected.size(); i++) {
            assertModeratorsMatch(expected.get(i), actual.get(i));
        }
    }

    private void assertModeratorsMatch(Moderator expected, Moderator actual){
        Assert.assertEquals(expected.getModeratorId(), actual.getModeratorId());
        Assert.assertEquals(expected.getForumId(), actual.getForumId());
    }
}
