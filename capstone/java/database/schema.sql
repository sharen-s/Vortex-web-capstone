BEGIN TRANSACTION;

DROP TABLE IF EXISTS users, forum, moderator, post, comment, favorite, interaction;

CREATE TABLE users (
	user_id SERIAL,
	username varchar(50) NOT NULL UNIQUE,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);

CREATE TABLE forum (
    forum_id SERIAL,
    name varchar(200) NOT NULL,
    description text NOT NULL,
    rules text NOT NULL,
    created timestamp DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT PK_forum PRIMARY KEY (forum_id),
    CONSTRAINT UQ_name UNIQUE (name)
);

CREATE TABLE moderator (
    moderator_id int NOT NULL,
    forum_id int NOT NULL,
    CONSTRAINT FK_moderator_user FOREIGN KEY (moderator_id) REFERENCES users (user_id),
    CONSTRAINT FK_moderator_forum FOREIGN KEY (forum_id) REFERENCES forum (forum_id)
);

CREATE TABLE post (
    post_id SERIAL,
    user_id int NOT NULL,
    forum_id int NOT NULL,
    title varchar(200) NOT NULL,
    body text,
    img_url text,
    posted_at timestamp DEFAULT CURRENT_TIMESTAMP,
    up_votes int DEFAULT 0,
    down_votes int DEFAULT 0,
    CONSTRAINT PK_post PRIMARY KEY (post_id),
    CONSTRAINT FK_post_user FOREIGN KEY (user_id) REFERENCES users (user_id),
    CONSTRAINT FK_post_forum FOREIGN KEY (forum_id) REFERENCES forum (forum_id),
    CONSTRAINT UQ_title UNIQUE (title),
    CONSTRAINT CHK_body_or_img CHECK ((body IS NOT NULL AND body != '') OR (img_url IS NOT NULL AND img_url != ''))
);


CREATE TABLE comment (
    comment_id SERIAL,
    user_id int NOT NULL,
    post_id int NOT NULL,
    body text NOT NULL,
    posted_at timestamp DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT PK_comment PRIMARY KEY (comment_id),
    CONSTRAINT FK_comment_user FOREIGN KEY (user_id) REFERENCES users (user_id),
    CONSTRAINT FK_comment_post FOREIGN KEY (post_id) REFERENCES post (post_id)
);

CREATE TABLE favorite (
    user_id int NOT NULL,
    favorite_id int NOT NULL,
    CONSTRAINT FK_favorite_user FOREIGN KEY (user_id) REFERENCES users (user_id),
    CONSTRAINT FK_favorite_forum FOREIGN KEY (favorite_id) REFERENCES forum (forum_id)
);

CREATE TABLE interaction (
    user_id int NOT NULL,
    post_id int NOT NULL,
    up_down boolean NOT NULL,
    CONSTRAINT FK_interaction_user FOREIGN KEY (user_id) REFERENCES users (user_id),
    CONSTRAINT FK_interaction_post FOREIGN KEY (post_id) REFERENCES post (post_id)
);

COMMIT TRANSACTION;
