BEGIN TRANSACTION;

INSERT INTO users (username,password_hash,role) VALUES ('user1','user1','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('user2','user2','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('user3','user3','ROLE_USER');

INSERT INTO forum (name, description, rules) VALUES ('name1', 'description1', 'rules1');
INSERT INTO forum (name, description, rules) VALUES ('name2', 'description2', 'rules2');
INSERT INTO forum (name, description, rules) VALUES ('name3', 'description3', 'rules3');
INSERT INTO forum (name, description, rules) VALUES ('name4', 'description4', 'rules4');

INSERT INTO post (user_id, forum_id, title, body, up_votes, down_votes) VALUES (1, 1,'title1','body1', 5, 10);
INSERT INTO post (user_id, forum_id, title, body, up_votes, down_votes) VALUES (2, 1,'title2','body2', 6, 8);
INSERT INTO post (user_id, forum_id, title, body, up_votes, down_votes) VALUES (3, 2,'title3','body3', 12, 0);

INSERT INTO favorite (user_id, favorite_id) VALUES (1,1);
INSERT INTO favorite (user_id, favorite_id) VALUES (1,2);

INSERT INTO interaction (user_id, post_id, up_down) VALUES (1, 1, true);
INSERT INTO interaction (user_id, post_id, up_down) VALUES (1, 2, false);
INSERT INTO interaction (user_id, post_id, up_down) VALUES (2, 1, true);
INSERT INTO interaction (user_id, post_id, up_down) VALUES (2, 2, false);

INSERT INTO moderator (moderator_id, forum_id) VALUES (1,1);
INSERT INTO moderator (moderator_id, forum_id) VALUES (1,2);
INSERT INTO moderator (moderator_id, forum_id) VALUES (2,2);

INSERT INTO comment (user_id, post_id, body) VALUES (1,1,'test1');
INSERT INTO comment (user_id, post_id, body) VALUES (2,1,'test2');
INSERT INTO comment (user_id, post_id, body) VALUES (2,2,'test3');



COMMIT TRANSACTION;
