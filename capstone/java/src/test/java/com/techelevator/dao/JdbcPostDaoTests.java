package com.techelevator.dao;

import com.techelevator.model.Post;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

public class JdbcPostDaoTests extends BaseDaoTests{
    protected static final Post POST_1 = new Post(1,1,1,5,10);
    protected static final Post POST_2 = new Post(2,2,1,6,8);
    protected static final Post POST_3 = new Post(3, 3, 2, 12, 0);

    private JdbcPostDao sut;

    @Before
    public void setup(){
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        sut = new JdbcPostDao(jdbcTemplate);
    }

    @Test
    public void assert_correct_post_retrieved_by_id(){
        assertPostsMatch(POST_1, sut.getPostById(1));
    }

    @Test
    public void assert_correct_list_of_posts_retrieved_for_forum(){
        List<Post> expected = new ArrayList<>();
        expected.add(POST_1);
        expected.add(POST_2);
        List<Post> actual = sut.getPostsByForum(1);
        Assert.assertEquals(2, actual.size());
        for (int i = 0; i < expected.size(); i++) {
            assertPostsMatch(expected.get(i), actual.get(i));
        }
    }

    @Test
    public void assert_get_top_10_post_orders_by_score(){
        List<Post> expected = new ArrayList<>();
        expected.add(POST_3);
        expected.add(POST_2);
        expected.add(POST_1);
        List<Post> actual = sut.getTop10Posts();
        Assert.assertEquals(3, actual.size());
        for (int i = 0; i < expected.size(); i++) {
            assertPostsMatch(expected.get(i), actual.get(i));
        }
    }

    private void assertPostsMatch(Post expected, Post actual){
        Assert.assertEquals(expected.getPostId(), actual.getPostId());
        Assert.assertEquals(expected.getUserId(), actual.getUserId());
        Assert.assertEquals(expected.getForumId(), actual.getForumId());
        Assert.assertEquals(expected.getUpVotes(), actual.getUpVotes());
        Assert.assertEquals(expected.getDownVotes(), actual.getDownVotes());
    }


}
