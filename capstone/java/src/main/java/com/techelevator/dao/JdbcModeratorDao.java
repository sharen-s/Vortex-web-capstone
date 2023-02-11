package com.techelevator.dao;

import com.techelevator.model.Moderator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcModeratorDao implements ModeratorDao{

    private final JdbcTemplate jdbcTemplate;

    public JdbcModeratorDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Moderator> getModeratorsByForumId(int id) {
        List<Moderator> moderators = new ArrayList<>();
        String sql = "SELECT moderator_id, moderator.forum_id, username, forum.name AS forum_name FROM moderator " +
                "JOIN users ON users.user_id = moderator.moderator_id " +
                "JOIN forum ON forum.forum_id = moderator.forum_id " +
                "WHERE moderator.forum_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
        while (results.next()){
            moderators.add(mapRowToModerator(results));
        }
        return moderators;
    }

    @Override
    public List<Moderator> getForumsByModeratorId(int id) {
        List<Moderator> moderators = new ArrayList<>();
        String sql = "SELECT moderator_id, moderator.forum_id, username, forum.name AS forum_name FROM moderator " +
                "JOIN users ON users.user_id = moderator.moderator_id " +
                "JOIN forum ON forum.forum_id = moderator.forum_id " +
                "WHERE moderator_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
        while(results.next()){
            moderators.add(mapRowToModerator(results));
        }
        return moderators;
    }

    @Override
    public void addModerator(Moderator moderator) {
        String sql = "INSERT INTO moderator (moderator_id, forum_id) VALUES (?, ?);";
        jdbcTemplate.update(sql, moderator.getModeratorId(), moderator.getForumId());
    }

    @Override
    public void removeModerator(Moderator moderator) {
        String sql = "DELETE FROM moderator WHERE moderator_id = ? AND forum_id = ?";
        jdbcTemplate.update(sql, moderator.getModeratorId(), moderator.getForumId());
    }

    private Moderator mapRowToModerator(SqlRowSet rowSet){
        Moderator moderator = new Moderator();
        moderator.setModeratorId(rowSet.getInt("moderator_id"));
        moderator.setForumId(rowSet.getInt("forum_id"));
        moderator.setUsername(rowSet.getString("username"));
        moderator.setForumName(rowSet.getString("forum_name"));
        return moderator;
    }
}
