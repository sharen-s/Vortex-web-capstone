package com.techelevator.dao;

import com.techelevator.model.Post;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcPostDao implements PostDao{

    private final JdbcTemplate jdbcTemplate;

    public JdbcPostDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Post> getPostsByForum(int forumId) {
        List<Post> postsByForum = new ArrayList<>();
        String sql = "SELECT post.post_id, post.user_id, forum_id, title, body, img_url, posted_at, up_votes, down_votes, username " +
                     "FROM post " +
                     "JOIN users ON post.user_id = users.user_id " +
                    "WHERE forum_id = ? ORDER BY posted_at DESC;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, forumId);
        while (results.next()){
            postsByForum.add(mapRowToPost(results));
        }
        return postsByForum;
    }

    @Override
    public List<Post> getPostsByKeyword(String keyword){
        List<Post> postsByTitleKeyword = new ArrayList<>();
        String searchWord1;
        String searchWord2;
        String searchWord3;
        searchWord1 = "% " + keyword + " %";
        searchWord2 = "% " + keyword;
        searchWord3 = "" + keyword + " %";
        String sql = "SELECT post.post_id, post.user_id, forum_id, title, body, img_url, posted_at, up_votes, down_votes, up_votes - down_votes AS score, username " +
                "FROM post " +
                "JOIN users ON post.user_id = users.user_id " +
                " WHERE title ILIKE ? OR title ILIKE ? OR title ILIKE ? OR body ILIKE ? OR body ILIKE ? OR body ILIKE ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, searchWord1, searchWord2, searchWord3, searchWord1, searchWord2, searchWord3);
        while (results.next()){
            postsByTitleKeyword.add(mapRowToPost(results));
        }
        return postsByTitleKeyword;
    }

    @Override
    public List<Post> getTop10Posts() {
        List<Post> top10 = new ArrayList<>();
        String sql = "SELECT post.post_id, post.user_id, forum_id, title, body, img_url, posted_at, up_votes, down_votes, up_votes - down_votes AS score, username " +
                "FROM post " +
                "JOIN users ON post.user_id = users.user_id " +
                "WHERE posted_at >= NOW() - INTERVAL '24 HOURS' " +
                "ORDER BY score DESC " +
                "LIMIT 10;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()){
            top10.add(mapRowToPost(results));
        }

        return top10;
    }

    @Override
    public Post getPostById(int id) {
        Post post = null;
        String sql = "select post.post_id, post.user_id, post.forum_id, post.title, post.body, post.img_url, post.posted_at, post.up_votes, post.down_votes, users.username from post " +
                "join users on users.user_id = post.user_id " +
                "where post.post_id = ?";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, id);
        if(result.next()){
            post = mapRowToPost(result);
        }
        return post;
    }

    @Override
    public void createPost(Post post) {
        String sql = "INSERT INTO post (user_id, forum_id, title, body, img_url) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, post.getUserId(), post.getForumId(), post.getTitle(), post.getBody(), post.getImg_url());
    }

    @Override
    public void updatePost(int id, Post post) {
        String sql = "UPDATE post SET body = ?, img_url = ?, up_votes = ?, down_votes = ? WHERE post_id = ?;";
        jdbcTemplate.update(sql, post.getBody(), post.getImg_url(), post.getUpVotes(), post.getDownVotes(), id);
    }

    @Override
    public void deletePost(int postId) {
        String sql = "BEGIN TRANSACTION; " +
                    "DELETE FROM comment WHERE post_id = ?; " +
                    "DELETE FROM interaction WHERE post_id = ?; " +
                    "DELETE FROM post WHERE post_id = ?; " +
                    "COMMIT;";
        jdbcTemplate.update(sql, postId, postId, postId);
    }

    private Post mapRowToPost(SqlRowSet rowSet){
        Post post = new Post();
        post.setPostId(rowSet.getInt("post_id"));
        post.setUserId(rowSet.getInt("user_id"));
        post.setForumId(rowSet.getInt("forum_id"));
        post.setTitle(rowSet.getString("title"));
        post.setBody(rowSet.getString("body"));
        post.setImg_url(rowSet.getString("img_url"));
        post.setUpVotes(rowSet.getInt("up_votes"));
        post.setDownVotes(rowSet.getInt("down_votes"));
        post.setPostedAt(rowSet.getTimestamp("posted_at").toLocalDateTime());
        post.setUsername(rowSet.getString("username"));
        return post;
    }
}
