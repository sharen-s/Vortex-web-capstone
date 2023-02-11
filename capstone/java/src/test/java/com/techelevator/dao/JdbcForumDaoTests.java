package com.techelevator.dao;

import com.techelevator.model.Forum;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

public class JdbcForumDaoTests extends BaseDaoTests{
    protected final Forum FORUM_1 = new Forum(1,"name1","description1","rules1");
    protected final Forum FORUM_2 = new Forum(2, "name2", "description2", "rules2");
    protected final Forum FORUM_3 = new Forum(3,"name3","description3","rules3");
    protected final Forum FORUM_4 = new Forum(4, "name4", "description4", "rules4");


    private JdbcForumDao sut;

    @Before
    public void setup(){
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        sut = new JdbcForumDao(jdbcTemplate);
    }

    @Test
    public void assert_all_forums_retrieved_in_find_all_method(){
        List<Forum> expected = new ArrayList<>();
        expected.add(FORUM_1);
        expected.add(FORUM_2);
        expected.add(FORUM_3);
        expected.add(FORUM_4);
        List<Forum> actual = sut.findAllForums();
        Assert.assertEquals(4, actual.size());
        for (int i = 0; i < expected.size(); i++) {
            assertForumsMatch(expected.get(i), actual.get(i));
        }
    }

    @Test
    public void assert_correct_forum_is_retrieved_by_id(){
        assertForumsMatch(FORUM_1, sut.findForumById(1));
    }

    @Test public void assert_correct_favorite_forum_retrieved_by_user_id(){
        List<Forum> expected = new ArrayList<>();
        expected.add(FORUM_1);
        expected.add(FORUM_2);
        List<Forum> actual = sut.favoriteForumsByUserId(1);
        Assert.assertEquals(2, actual.size());
        for (int i = 0; i < expected.size(); i++) {
            assertForumsMatch(expected.get(i), actual.get(i));
        }

    }

    private void assertForumsMatch(Forum expected, Forum actual){
        Assert.assertEquals(expected.getId(), actual.getId());
        Assert.assertEquals(expected.getForumName(), actual.getForumName());
        Assert.assertEquals(expected.getDescription(), actual.getDescription());
        Assert.assertEquals(expected.getRules(), actual.getRules());
    }

}
