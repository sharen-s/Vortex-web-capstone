package com.techelevator.dao;

import com.techelevator.model.Forum;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcForumDao implements ForumDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcForumDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Forum> findAllForums() {
        String sql = "SELECT forum.forum_id, forum.name, forum.description, forum.rules FROM forum;";
               // "JOIN post ON forum.forum_id = post.forum_id\n" +
              //  " GROUP BY forum.forum_id, post.posted_at ORDER BY post.posted_at;";

        List<Forum> allForums = new ArrayList<>();
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()){
            Forum forum = mapRowToForum(results);
            int forumId = forum.getId();
            String sqlId = "SELECT moderator_id FROM moderator WHERE forum_id=?";
            SqlRowSet resultId = jdbcTemplate.queryForRowSet(sqlId, forumId);
            List<Integer> moderatorList = new ArrayList<>();
            while (resultId.next()){
                moderatorList.add(mapRowToModeratorList(resultId));
            }
            forum.setModeratorList(moderatorList);
            allForums.add(forum);
        }
        return allForums;
    }

    @Override
    public List<Forum> favoriteForumsByUserId(int id) {
        String sql = "SELECT forum_id, name, description, rules FROM forum " +
                "JOIN favorite on favorite.favorite_id = forum.forum_id WHERE user_id = ?;";

        List<Forum> favoriteForums = new ArrayList<>();
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
        while (results.next()){
            favoriteForums.add(mapRowToForum(results));
        }

        return favoriteForums;
    }

    @Override
    public Forum findForumById(int id) {

        String sql = "SELECT forum_id, name, description, rules FROM forum WHERE forum_id = ?";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, id);

        Forum forum = new Forum();
        if (result.next()){
            forum = mapRowToForum(result);

            String sqlModeratorId = "SELECT moderator_id FROM moderator WHERE forum_id=?";
            SqlRowSet resultMod = jdbcTemplate.queryForRowSet(sqlModeratorId, id);
            List<Integer> moderatorList = new ArrayList<>();
            while (resultMod.next()){
                moderatorList.add(mapRowToModeratorList(resultMod));
            }
            forum.setModeratorList(moderatorList);
        }

        return forum;
    }


    @Override
    public List<Forum> findTopFive() {

        String sql = "SELECT MAX(post.posted_at), forum.forum_id, name, description, rules FROM forum " +
                "JOIN post ON post.forum_id = forum.forum_id " +
                "GROUP BY forum.forum_id " +
                "ORDER BY MAX(post.posted_at) DESC " +
                "LIMIT 5;";
        List<Forum> forumList = new ArrayList<>();
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);

        while (results.next()){
            Forum forum = mapRowToForum(results);
            int forumId = forum.getId();
            String sqlId = "SELECT moderator_id FROM moderator WHERE forum_id=?";
            SqlRowSet resultId = jdbcTemplate.queryForRowSet(sqlId, forumId);
            List<Integer> moderatorList = new ArrayList<>();
            while (resultId.next()){
                moderatorList.add(mapRowToModeratorList(resultId));
            }
            forum.setModeratorList(moderatorList);
            forumList.add(forum);
        }
        return forumList;
    }

    @Override
    public Forum createForum(Forum forum) {
        String sql = "INSERT INTO forum (name, description, rules) VALUES (?, ?, ?) RETURNING forum_id;";
        int id = jdbcTemplate.queryForObject(sql, int.class, forum.getForumName(), forum.getDescription(), forum.getRules());
        forum.setId(id);
        String sqlModerator = "INSERT INTO moderator (moderator_id, forum_id) VALUES (?, ?);";
        jdbcTemplate.update(sqlModerator, forum.getModeratorList().get(0), forum.getId());
        return forum;
    }



    //    @Override
//    public List<Forum> findForumByKeyword(String keyword) {
//        List<Forum> forumKeywordList = new ArrayList<>();
//        String sql = "SELECT forum_id, name, description, rules FROM forum WHERE name ILIKE = %?%;";
//        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
//        while (results.next()){
//            forumKeywordList.add(mapRowToForum(results));
//        }
//        return forumKeywordList;
//    }




    private Forum mapRowToForum(SqlRowSet result){
        Forum forum = new Forum();
        forum.setId(result.getInt("forum_id"));
        forum.setForumName(result.getString("name"));
        forum.setDescription(result.getString("description"));
        forum.setRules(result.getString("rules"));

        return forum;
    }

    private int mapRowToModeratorList(SqlRowSet result){
        int moderatorId = result.getInt("moderator_id");
        return moderatorId;
    }



}
