package com.techelevator.dao;


import com.techelevator.model.Interaction;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

public class JdbcInteractionDaoTests extends BaseDaoTests{
    protected final Interaction INTERACTION_1 = new Interaction(1,1);
    protected final Interaction INTERACTION_2 = new Interaction(1,2);

    private JdbcInteractionDao sut;

    @Before
    public void setup(){
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        sut = new JdbcInteractionDao(jdbcTemplate);
    }

    @Test
    public void assert_interactions_retrieved_by_user_id(){
        List<Interaction> expected = new ArrayList<>();
        expected.add(INTERACTION_1);
        expected.add(INTERACTION_2);
        List<Interaction> actual = sut.getInteractionsByUserId(1);
        Assert.assertEquals(expected.size(), actual.size());
        for (int i = 0; i < expected.size(); i++) {
            assertInteractionsMatch(expected.get(i), actual.get(i));
        }

    }

    private void assertInteractionsMatch(Interaction expected, Interaction actual){
        Assert.assertEquals(expected.getUserId(), actual.getUserId());
        Assert.assertEquals(expected.getPostId(), actual.getPostId());
    }
}
