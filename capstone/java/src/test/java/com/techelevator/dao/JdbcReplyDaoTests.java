package com.techelevator.dao;

import com.techelevator.model.Reply;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

public class JdbcReplyDaoTests extends BaseDaoTests{
    protected final Reply REPLY_1 = new Reply(1,1,1,"test1");
    protected final Reply REPLY_2 = new Reply(2, 2, 1, "test2");

    private JdbcReplyDao sut;

    @Before
    public void setup(){
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        sut = new JdbcReplyDao(jdbcTemplate);
    }

    @Test
    public void assert_correct_reply_retrieved_by_post(){
        List<Reply> expected = new ArrayList<>();
        expected.add(REPLY_1);
        expected.add(REPLY_2);
        List<Reply> actual = sut.getRepliesByPost(1);
        Assert.assertEquals(expected.size(), actual.size());
        for (int i = 0; i < expected.size(); i++) {
            assertRepliesMatch(expected.get(i), actual.get(i));
        }
    }



    private void assertRepliesMatch(Reply expected, Reply actual){
        Assert.assertEquals(expected.getCommentId(), actual.getCommentId());
        Assert.assertEquals(expected.getUserId(), actual.getUserId());
        Assert.assertEquals(expected.getPostId(), actual.getPostId());
        Assert.assertEquals(expected.getBody(), actual.getBody());
    }
}
