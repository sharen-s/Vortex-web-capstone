package com.techelevator.dao;

import com.techelevator.model.Reply;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcReplyDao implements ReplyDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcReplyDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Reply> getRepliesByPost(int postId) {
        List<Reply> repliesByPost = new ArrayList<>();
        String sql = "SELECT comment_id, comment.user_id, post_id, body, posted_at, users.username FROM comment " +
                "JOIN users ON comment.user_id = users.user_id " +
                "WHERE post_id = ? " +
                "ORDER BY posted_at DESC";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, postId);
        while (results.next()){
            repliesByPost.add(mapRowToReply(results));
        }
        return repliesByPost;
    }

    @Override
    public Reply getReplyById(int id) {
        Reply reply = null;
        String sql = "SELECT comment_id, user_id, post_id, body, posted_at FROM comment " +
                "WHERE comment_id = ?;";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, id);
        if(result.next()){
            reply = mapRowToReply(result);
        }
        return reply;
    }

    @Override
    public void createReply(Reply reply) {
        String sql = "INSERT INTO comment (user_id, post_id, body) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, reply.getUserId(), reply.getPostId(), reply.getBody());

    }

    @Override
    public void deleteReply(int id) {
        String sql = "DELETE FROM comment WHERE comment_id = ?";
        jdbcTemplate.update(sql, id);
    }

    private Reply mapRowToReply(SqlRowSet rowSet){
        Reply reply = new Reply();
        reply.setCommentId(rowSet.getInt("comment_id"));
        reply.setUserId(rowSet.getInt("user_id"));
        reply.setPostId(rowSet.getInt("post_id"));
        reply.setBody(rowSet.getString("body"));
        reply.setPostedAt(rowSet.getTimestamp("posted_at").toLocalDateTime());
        reply.setUsername(rowSet.getString("username"));
        return reply;
    }




}
